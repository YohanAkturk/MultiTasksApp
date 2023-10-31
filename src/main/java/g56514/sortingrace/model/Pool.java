package g56514.sortingrace.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yohan
 */
public class Pool {

    private int capacity;
    private List<Thread> threads;
    private int nextIndex;
    private boolean isFinsih;

    public Pool(int capacity) {
        this.capacity = capacity;
        System.out.println("capacity-val : " + capacity);
        this.threads = new ArrayList<>();
        this.nextIndex = 0;
        this.isFinsih = false;
    }

    public void addThread(Thread thread) {
        threads.add(thread);
    }

    public synchronized void start() {
        for (int i = 0; i < capacity; i++) {
            System.out.println("########## START numéro : " + i);
            threads.get(nextIndex++).start();
        }
    }

    public synchronized void startNext() {
        if (nextIndex < threads.size() - 1) { // -1 ?
            threads.get(nextIndex).start();
            nextIndex++;
        }
    }

}
