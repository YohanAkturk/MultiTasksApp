package g56514.sortingrace.view;

import g56514.sortingrace.controller.Controller;
import g56514.sortingrace.gestionDB.dto.SimulationDto;
import g56514.sortingrace.gestionDB.exception.RepositoryException;
import g56514.sortingrace.model.Sort;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author yohan
 */
public class SortViewController implements Observer {

    Controller controller;

    static int cpt = 0;
    long diff = 0;

    @FXML
    private TableColumn TriId;
    @FXML
    private TableColumn TailleId;
    @FXML
    private TableColumn OperationsId;
    @FXML
    private TableColumn DureeId;

    @FXML
    private Spinner threadSpinner;
    @FXML
    private ChoiceBox sortChoice;
    @FXML
    private ChoiceBox configurationChoice;

    @FXML
    private Button startBtn;

    @FXML
    private LineChart chart;

    @FXML
    private ProgressBar progressBar = new ProgressBar();
    @FXML
    private TableView table;

    @FXML
    private MenuItem logs;

    //viewDB
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<DataToDisplay, String> time;
    @FXML
    private TableColumn<DataToDisplay, String> sortType;
    @FXML
    private TableColumn<DataToDisplay, Integer> maxSize;

    XYChart.Series seriesSort;
    private List<SimulationDto> list;

//    @FXML
//    void
    public SortViewController(Controller controller) {
        this.controller = controller;
        System.out.println("");
        System.out.println(LocalDateTime.now()
                + " FXCONTROLLER | CONSTRUCTEUR | DEBUT");
        System.out.println(LocalDateTime.now()
                + " FXCONTROLLER | CONSTRUCTEUR | FIN");
    }

    public void initialize() {
        TriId.setCellValueFactory(new PropertyValueFactory<>("typeSort"));
        TailleId.setCellValueFactory(new PropertyValueFactory<>("Taille"));
        OperationsId.setCellValueFactory(new PropertyValueFactory<>("Cpt"));
        DureeId.setCellValueFactory(new PropertyValueFactory<>("Diff"));

        threadSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10)
        );
        threadSpinner.getValueFactory().setValue(1);
        sortChoice.setValue("select");
        sortChoice.getItems().addAll("Tri à bulles", "Tri fusion");
        configurationChoice.getItems().addAll("Very easy : 0 - 100 - 10",
                "Easy : 0 - 1000 - 100", "Moderate : 0 - 10000 - 1000",
                "Hard : 0 - 100000 - 10000");
        seriesSort = new XYChart.Series();
        seriesSort.getData().add(new XYChart.Data(0, 0));
        maj();
    }

    public void maj() {
        startBtn.setOnAction(e -> {
            seriesSort = new XYChart.Series();
            String tri = sortChoice.getValue().toString();
            System.out.println(tri);
            TypeSort typeSort = equivalentEnum(tri);
            String tabLength = configurationChoice.getValue().toString();
            seriesSort.getData().add(new XYChart.Data(0, 0));
            int nbThreads = Integer.parseInt(threadSpinner.getValue().toString());
            System.out.println("-----------val : " + nbThreads);
            try {
                int tabLengthFinal = getUserEntry(tabLength);
                controller.setArraySize(tabLengthFinal);
                controller.setSortType(typeSort.toString());
                controller.setTime(Timestamp.from(Instant.now()));
                controller.startSortingToPopulateTable(typeSort, tabLengthFinal, nbThreads);
            } catch (NumberFormatException ex) {
                Logger.getLogger(SortViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(SortViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(SortViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            chart.setTitle("complexité");
        });
        logs.setOnAction(e -> {
            System.out.println("LOGS");
            list = new ArrayList<>();
            try {
                list = controller.getAllFromDB();
            } catch (RepositoryException ex) {
                Logger.getLogger(SortViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            showDialog();
        });
    }

    private void showDialog() {
        //data to be displayed in the tableView
        //after selectAll request in the database.
        list.forEach(e -> {
            System.out.print(e.getTimestamp() + " ");
            System.out.print(e.getSort_type() + " ");
            System.out.println(e.getMax_size());
        });

        //convert list to observableList to show it in the tableView.
        ObservableList<DataToDisplay> datas = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            DataToDisplay dataTmp = new DataToDisplay(list.get(i).getTimestamp(), list.get(i).getSort_type(), list.get(i).getMax_size());
            datas.add(dataTmp);
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/viewDB.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("Simulation Logs");
            stage.setScene(scene);
            try {
                table.getItems().clear();
                time.setCellValueFactory(new PropertyValueFactory<>("time"));
                sortType.setCellValueFactory(new PropertyValueFactory<>("sortType"));
                maxSize.setCellValueFactory(new PropertyValueFactory<>("maxSize"));
                table.setItems(datas);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    private int getUserEntry(String tabLength) throws NumberFormatException {
        int debut = 0;
        int fin = 0;
        for (int i = 0; i < tabLength.length(); i++) {
            if (tabLength.charAt(i) == '-') {
                debut = i + 2;
                break;
            }
        }
        for (int i = debut; i < tabLength.length(); i++) {
            if (tabLength.charAt(i) == ' ') {
                fin = i;
                break;
            }
        }
        tabLength = tabLength.substring(debut, fin);
        int tailleFinal = Integer.parseInt(tabLength);
        return tailleFinal;
    }

    private TypeSort equivalentEnum(String tri) {
        switch (tri) {
            case "Tri à bulles":
                return TypeSort.BUBBLE_SORT;
            case "Tri fusion":
                return TypeSort.FUSION_SORT;
        }
        return null;
    }

    /**
     * updates the application.
     *
     * @param obj un objet de type Sort.
     */
    @Override
    public void update(Object obj) {
        Platform.runLater(() -> {
            progressBar.setProgress(0);
            Sort sort = (Sort) obj;
            progressBar.setProgress(progressBar.getProgress() + 1);
            table.getItems().add(obj);
            seriesSort.getData().add(new XYChart.Data(sort.getTaille(), sort.getCpt()));
            seriesSort.setName(sort.getTypeSort());
            if (!chart.getData().contains(seriesSort)) {// POUR éviter error duplicate series ;)
                chart.getData().add(seriesSort);
            }
        });
        controller.increment();
        if (controller.getOperation() == 9) {
            try {
                //to show if we have finished the 10 sort
                controller.createDto();
            } catch (RepositoryException ex) {
                Logger.getLogger(SortViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        maj();
    }
}
