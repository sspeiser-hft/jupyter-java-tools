package hftstuttgart.dsa;

public class PVL1 {
    
    public static void insertUnsorted(DynamicArray data, int value) {
        Experiment.EOP("[]", "=", "length");
        data.set(data.size(), value);
    }

    public static int linearSearch(DynamicArray data, int value) {
        Experiment.EOP("=", "<", "length");
        for (int i = 0; i < data.size(); i++) {
            Experiment.EOP("[]", "==");
            if(data.get(i) ==value) {
                Experiment.EOP("return", "value");
                return i;
            }
             Experiment.EOP("++", "<", "length");
        }
        Experiment.EOP("return", "value");
        return -1;
    }

}
