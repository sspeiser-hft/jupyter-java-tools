package hftstuttgart.dsa.btree;

public class KeyNode extends BNode {
    public int m;
    public int[] max;
    public BNode[] children;
    public int nChildren;
    
    public KeyNode(int m) {
        this.m = m;
        max = new int[m-1];
        children = new BNode[m];
        nChildren = 0;
    }
}