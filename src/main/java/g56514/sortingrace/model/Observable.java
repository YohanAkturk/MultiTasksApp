package g56514.sortingrace.model;

import g56514.sortingrace.view.Observer;
import java.util.List;

/**
 *
 * @author yohan
 */
public interface Observable {

    /**
     * Adds observer.
     *
     * @param observer the observer.
     */
    void addObserver(Observer observer);

    /**
     * Removes observer.
     *
     * @param observer the observer.
     */
    void removeObserver(Observer observer);

}
