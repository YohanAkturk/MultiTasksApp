package g56514.sortingrace.model;

import java.time.Instant;
import java.util.stream.IntStream;

/**
 *
 * @author yohan
 */
public class BubbleSort implements Sort {

    private int[] arr;
    private int taille;
    private int cpt;
    private long diff;
    private String TypeSort;

    public BubbleSort(int[] arr, int taille) {
        this.arr = arr;
        this.taille = taille;
        this.cpt = 0;
        this.diff = 0;
        this.TypeSort = "bubble sort";
    }

    /**
     * https://www.javatpoint.com/bubble-sort-in-java
     */
    @Override
    public void sort() {
        cpt = 0;
        int n = taille;
        Instant before = Instant.now();
        IntStream.range(0, n - 1)
                .flatMap(i -> IntStream.range(1, n - i))
                .forEach(j -> {
                    cpt++;
                    if (arr[j - 1] > arr[j]) {
                        int temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                    }
                });
        Instant after = Instant.now();
        diff = (after.toEpochMilli() - before.toEpochMilli());
        System.out.println("DIFFF : " + diff);

    }

    @Override
    public int getTaille() {
        return taille;
    }

    @Override
    public int getCpt() {
        return cpt;
    }

    @Override
    public long getDiff() {
        return diff;
    }

    @Override
    public String getTypeSort() {
        return TypeSort;
    }
}
