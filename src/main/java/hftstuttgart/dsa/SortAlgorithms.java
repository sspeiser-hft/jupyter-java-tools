package hftstuttgart.dsa;

import java.util.concurrent.ThreadLocalRandom;

// Marcus Deiniger Implementations from Slides

public class SortAlgorithms {

    // "MysterySort": hide the name of algorithms
    public static String mysterySort1(int[] s) {
        heapsort(s);
        return "Heap Sort";
    }

    public static String mysterySort2(int[] s) {
        selectionsort(s);
        return "Selection Sort";
    }

    public static String mysterySort3(int[] s) {
        quicksort(s);
        return "Quick Sort";
    }

    public static String mysterySort4(int[] s) {
        bubblesort(s);
        return "Bubble Sort";
    }

    public static String mysterySort5(int[] s) {
        insertionsort(s);
        return "Insertion Sort";
    }

    public static String mysterySort6(int[] s) {
        mergesort(s);
        return "Merge Sort";
    }

    public static void mergesort(int[] s) {
        mergesort(s, 0, s.length - 1);
    }

    public static void mergesort(int[] s, int li, int re){ 
        if (li < re) {
            int mittel = (li + re + 1)/2;
            mergesort(s, li, mittel-1); // rekursiv in Teillisten
            mergesort(s, mittel, re);   // zerlegen
            merge(s, li, mittel, re);   // Zusammenmischen der Teillisten
        }
    }
    
    public static void merge(int[] s, int li, int mi, int re) {
        int[] temp = new int[re - li + 1];
        for (int i = 0, j = li, k = mi; i < temp.length; i++)
            if ((k > re) || ((j < mi) && (s[j] <= s[k]))) {
                temp[i] = s[j]; j++;
            } else {
                temp[i] = s[k]; k++;
            }
        for (int n = 0; n < temp.length; n++) {
            s[li + n] = temp[n];
            SortPlot.updateSortPlot(s);
        }   
    }

    public static void heapsort(int[] s) {
        // Heap-Eigenschaft herstellen
        for (int i = s.length / 2; i >= 0; i--)
            heap(s, i, s.length - 1);
        // Sortieren
        for (int i = s.length - 1; i > 0; i--) {
            // Das kleinste Element ist in der Wurzel 
            // --> nach hinten tauschen
            int t = s[i]; s[i] = s[0]; s[0] = t;
            SortPlot.updateSortPlot(s);
            // Heap-Eigenschaft für Rest-Baum wieder herstellen
            heap(s, 0, i - 1);
        }
        // Feld umkehren
        for (int i = 0, k = s.length - 1; i < s.length / 2; i++) {
            int t = s[i]; s[i] = s[k]; s[k] = t;
            SortPlot.updateSortPlot(s);
            k--;
        }
    }

    public static void heap(int[] s, int vater, int ende) {
        // setze s[i] = min(s[i], s[2*i+1], s[2*i+2])
        // tausche dabei s[i] ggfs. mit s[2*i+1] oder s[2*i+2]
        int links =  2 * vater + 1;
        int rechts = 2 * vater + 2;
        int min = vater;
        if (links <= ende)                // es gibt einen linken Sohn
            if (s[vater] > s[links])    // und der linke Sohn ist
                min = links;            // kleiner als der Vater
    
        if (rechts <= ende)                // es gibt einen rechten Sohn
            if (s[min] > s[rechts])    // und der rechte Sohn ist 
                min = rechts;            // kleiner als Vater oder der linke Sohn
    
        if (vater != min) {             // der Vater ist nicht das kleinste Element
                                        // tausche Vater und Sohn
            int t = s[min]; s[min] = s[vater]; s[vater] = t; 
            heap(s, min, ende);        // mache beim getauschten Sohn weiter
        }
    }

    static ThreadLocalRandom g = ThreadLocalRandom.current();

public static int partition (int[] s, int left, int right){
    int i = g.nextInt(right - left + 1) + left;
    int x = s[i]; s[i] = s[right]; s[right] = x;

    int l = left;
    int r = right - 1;
    while (true){
        while (l <= r && s[l] <= x) l++;
        while (l <= r && s[r] > x) r--;
        if (l < r){
            int t = s[l]; s[l] = s[r]; s[r] = t;
            SortPlot.updateSortPlot(s);
        }else {
            int t = s[l]; s[l] = s[right]; s[right] = t;
            SortPlot.updateSortPlot(s);
            return l; // l ist die Position des Pivot-Elements
        }
    }
}

public static void quicksort(int[] s, int left, int right) {
    if (left < right) {
        // pi Index des Pivotelements
        int pi = partition(s, left, right); 
        quicksort(s, left, pi - 1);
        quicksort(s, pi + 1, right);
    }
}

public static void quicksort(int[] s) {
    quicksort(s, 0, s.length - 1);
}

public static void insertionsort(int[] s){
    for (int i = 1; i < s.length; i++) {
        if (s[i-1] > s[i] ){
            // s[i] ist nicht sortiert
            int x = s[i];
            // verschieben der Zellen nach rechts bis s[k] frei ist
            int k = i;
            for (; k > 0 && s[k-1] > x; k--) {
                s[k] = s[k-1];
                SortPlot.updateSortPlot(s);
            }
            s[k] = x; // s[i] an der freien Stelle einfügen
            SortPlot.updateSortPlot(s);
        }
    }
}

public static void bubblesort(int[] s) {
    int n = s.length;
    boolean tausch = true;
    for (int i = 0; i < n && tausch; i++) {
        tausch = false;
        for (int k = 0; k < n - i - 1; k++) {
            if (s[k] > s[k + 1]) {
                // vertausche Element k und k+1
                int t = s[k]; s[k] = s[k + 1]; s[k + 1] = t;
                tausch = true;
                SortPlot.updateSortPlot(s);
            }
        }
    }
}

public static void selectionsort(int[] s) {
    for (int i = 0; i < s.length; i++) {
        int mini = i;
        for (int k = i + 1; k < s.length; k++)
            if (s[k] < s[mini])
                mini = k;
        int t = s[i]; s[i] = s[mini]; s[mini] = t;
        SortPlot.updateSortPlot(s);
    }
}

    
}
