package hftstuttgart.dsa.btree;

public class DataNode extends BNode {
    public int key;
    public String value;
    
    public DataNode(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
