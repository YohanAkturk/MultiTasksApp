package g56514.sortingrace.controller;

import g56514.sortingrace.gestionDB.dto.SimulationDto;
import g56514.sortingrace.gestionDB.exception.RepositoryException;
import g56514.sortingrace.model.Model;
import g56514.sortingrace.view.TypeSort;
import java.sql.Timestamp;  
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author yohan
 */
public class Controller {

    private Model model;
    private SimulationDto dto;
    private int arraySize;
    private String sortType;
    private int operation;
    private Timestamp time; //represents the duration

    public Controller(Model model) {
        this.model = model;
    }

    public void startSortingToPopulateTable(TypeSort typeSort, int tabLength, int nbThreads) throws NumberFormatException, InterruptedException, ExecutionException {
        model.startSortingToPopulateTable(typeSort, tabLength, nbThreads);
        // transforme les inputs de l'interface en valeur qui ont du sens pour le modèle.
        // sans doute mieux de faire la conversin dans le fxmlcontroller.
    }

    public void createDto() throws RepositoryException {
        dto = new SimulationDto(0, time.toString(), getSortType(), getArraySize());
        System.out.println(dto);
        model.insertDB(dto);
    }
    
    public List<SimulationDto> getAllFromDB() throws RepositoryException{
        return model.getAllFromDB();
    }

    public int getArraySize() {
        return arraySize;
    }

    public String getSortType() {
        return sortType;
    }

    public int getOperation() {
        return operation;
    }

    public Timestamp getTime() {
        return time;
    }

    public SimulationDto getDto() {
        return dto;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public void increment() {
        this.operation++;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
