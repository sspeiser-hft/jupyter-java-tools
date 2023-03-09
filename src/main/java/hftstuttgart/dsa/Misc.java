package hftstuttgart.dsa;

import hftstuttgart.dsa.tree.Node;
import hftstuttgart.dsa.tree.TreePrinter;

public class Misc {


    public static int berechneHoehe(Node node) {
        if(node == null) {
            return -1;
        }
        return Math.max(berechneHoehe(node.left), berechneHoehe(node.right)) + 1;
    }
    public static Node arrayTreeToNodeTree(int[] tree) {
        return arrayTreeToNodeTree(tree, 0);
    }
    
    public static Node arrayTreeToNodeTree(int[] tree, int pos) {
        if((pos >= tree.length) || (tree[pos] < 0)) {
            return null;
        }
        Node nodeTree = new Node();
        nodeTree.value = tree[pos];
        nodeTree.left = arrayTreeToNodeTree(tree, pos * 2 + 1);
        nodeTree.right = arrayTreeToNodeTree(tree, pos * 2 + 2);
        return nodeTree;
    }
    public static void main(String args[]) {
        Node n = new Node();
        n.left = new Node();
        n.right = new Node();

        System.out.println(berechneHoehe(n));

        Node tree2 = arrayTreeToNodeTree(new int[] { 8, 2, 32, 1, 4, 16, 56, 0, -1, -1, -1, -1, -1, 48, 64  });
        TreePrinter.print(tree2);

        System.out.println(berechneHoehe(tree2));

        System.out.println(berechneHoehe(tree2));
    }
}
