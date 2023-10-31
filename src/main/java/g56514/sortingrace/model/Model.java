package g56514.sortingrace.model;

import g56514.sortingrace.gestionDB.config.ConfigManager;
import g56514.sortingrace.gestionDB.dto.SimulationDto;
import g56514.sortingrace.gestionDB.exception.RepositoryException;
import g56514.sortingrace.gestionDB.repository.SimulationRepository;
import g56514.sortingrace.view.Observer;
import g56514.sortingrace.view.TypeSort;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//TT CE QUI EST JAVAFX A METTRE UNQIUEMENT DS LA VUE ET NON DS LE MODEL. A METTRE PRECISEMENT DS LE UPDATE.
/**
 *
 * @author yohan
 */
public class Model implements Observable, Observer {

    private int cpt = 0;
    private Pool pool;
    private List<Observer> observers = new ArrayList<>();
    private static SimulationRepository repo;

    public Model() throws SQLException, IOException, RepositoryException {
        ConfigManager.getInstance().load();
        repo = new SimulationRepository();
    }

    public void startSortingToPopulateTable(TypeSort typeSort, int tabLength, int nbThreads) // startSorting
            throws InterruptedException, ExecutionException {
        // normalement il faut enparam: type de tri, nbThreads, maxValue, step.

        int tab[] = new int[tabLength];
        pool = new Pool(nbThreads);
        for (int i = 0; i < (tabLength / (tabLength / 10)); i++) { // constante ou venant de l'interface utilisateur
            int nbElts = tab.length / 10;
            //rempli les 10 premières cases, puis les 20, puis les 30, ...
            for (int j = 0; j < (nbElts * (i + 1)); j++) {
                tab[j] = 0 + (int) (Math.random() * (1000 + 1));
            }
            Sort sort = null;
            System.out.println(typeSort.toString());
            if (typeSort.toString().equals("BUBBLE_SORT")) { //bubbleSort a le nom du fxml.
                System.out.println("bs");
                sort = new BubbleSort(tab, (nbElts * (i + 1)));
            } else {
                System.out.println("fs");
                sort = new FusionSort(tab, (nbElts * (i + 1)));
            }
            SortingThread st = new SortingThread(sort);
            st.addObserver(this);
            pool.addThread(st);

        }
        System.out.println("appel start ?");
        pool.start();
        System.out.println("appel fait");
    }
    
    public void insertDB(SimulationDto dto) throws RepositoryException{
        repo.add(dto);
    }
    
    public List<SimulationDto> getAllFromDB() throws RepositoryException{
        return repo.getAll();
    }

    /**
     * Getter of observers.
     *
     * @return the observers.
     */
    public List<Observer> getObservers() { // JAMAIS !
        return observers;
    }

    /**
     * Adds observer.
     *
     * @param observer the observer.
     */
    @Override
    public void addObserver(Observer observer) {
        System.out.println("obsssssssssssssserver");
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes the observer.
     *
     * @param observer the observer.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Object obj) {
        System.out.println("NOTIFYYYY");
        for (Observer o : observers) {
            System.out.println("NOIJUHUYTIFYYYY");
            o.update(obj);
        }
    }

    @Override
    public void update(Object obj) {
        this.notifyObservers(obj);
        pool.startNext();
    }
}
