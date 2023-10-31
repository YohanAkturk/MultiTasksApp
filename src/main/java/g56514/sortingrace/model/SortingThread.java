package g56514.sortingrace.model;

import g56514.sortingrace.view.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yohan
 */
public class SortingThread extends Thread implements Observable {

    private Sort sort;
    private List<Observer> observers;

    public SortingThread(Sort sort) {
        this.sort = sort;
        this.observers = new ArrayList<>();
    }

    /**
     * Adds observer.
     *
     * @param observer the observer.
     */
    @Override
    public void addObserver(Observer observer) {
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
        for (Observer o : observers) {
            o.update(obj);
        }
    }

    public void run() {
        System.out.println("runnn");
        sort.sort();
        this.notifyObservers(sort);
    }

}
