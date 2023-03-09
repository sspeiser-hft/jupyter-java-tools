package hftstuttgart.dsa;

import java.util.ArrayList;

public class DynamicArray {
    private ArrayList<Integer> daten = new ArrayList<>();

    public int size() {
        return daten.size();
    }

    public int get(int index) {
        if (index >= daten.size()) {
            return 0;
        }
        return daten.get(index);
    }

    public void set(int index, int wert) {
        if (index > daten.size() - 1) {
            for (int i = daten.size(); i < index; i++) {
                daten.add(i, 0);
            }
            daten.add(index, wert);
        } else {
            daten.set(index, wert);
        }
    }

    public String toString() {
        return daten.toString();
    }
}