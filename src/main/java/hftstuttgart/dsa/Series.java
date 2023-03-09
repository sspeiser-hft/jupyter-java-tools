package hftstuttgart.dsa;

import java.util.ArrayList;
import java.util.List;

public class Series {
    private String name;
    private List<Double> sizes = new ArrayList<>();
    private List<Long> eops = new ArrayList<>();
    private List<Long> millis = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Double> getSizes() {
        return sizes;
    }

    public List<Long> getEOPs() {
        return eops;
    }

    public List<Long> getMillis() {
        return millis;
    }

    public Series(String name) {
        this.name = name;
    }

    public void add(double size, long eops, long millis) {
        sizes.add(size);
        this.eops.add(eops);
        this.millis.add(millis);
    }

    public int findIndexBySize10(int size) {
        for(int i = 0 ; i < sizes.size(); i++) {
            if((int) (sizes.get(i) * 10) == size) {
                return i;
            }
        }
        return -1;
    }

    public long findTimeBySize10(int size) {
        int index = findIndexBySize10(size);
        if(index < 0) {
            return index;
        }
        return millis.get(index);
    }

    public long findEOPsBySize10(int size) {
        int index = findIndexBySize10(size);
        if(index < 0) {
            return index;
        }
        return eops.get(index);
    }


}
