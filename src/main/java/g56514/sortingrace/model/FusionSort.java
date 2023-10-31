package g56514.sortingrace.model;

import java.time.Instant;

/**
 * CA VIENT d'OU ? tu l'as écrit toi-même ?
 *
 * @author yohan
 */
public class FusionSort implements Sort {

    private int[] arr;
    private int taille;
    private int cpt;
    private long diff;
    private String TypeSort;

    public FusionSort(int[] arr, int taille) {
        this.arr = arr;
        this.taille = taille;
        this.cpt = 0;
        this.diff = 0;
        this.TypeSort = "fusion sort";
    }

    @Override
    public void sort() {
        mergeSort(arr, taille);
    }

    /**
     * https://www.baeldung.com/java-merge-sort
     *
     * @param a the array.
     * @param n the array size.
     */
    public void mergeSort(int[] a, int n) {
        cpt = 0;
        Instant before = Instant.now();
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            cpt++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            cpt++;
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
        Instant after = Instant.now();
        diff = (after.toEpochMilli() - before.toEpochMilli());
    }

    public void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                cpt++;
            } else {
                a[k++] = r[j++];
                cpt++;
            }
        }
        while (i < left) {
            a[k++] = l[i++];
            cpt++;
        }
        while (j < right) {
            a[k++] = r[j++];
            cpt++;
        }
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
