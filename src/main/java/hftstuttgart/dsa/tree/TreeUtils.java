package hftstuttgart.dsa.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {

    public static void breadthFirst(Node root) {
        Queue<Node> q = new LinkedList<Node>();
        if (root != null) {
            q.add(root);
        }
        while (!q.isEmpty()) {
            Node n = q.remove();
            System.out.print(n.value + " ");
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
    }

    public static void depthFirst(Node root) {
        Deque<Node> s = new ArrayDeque<Node>();
        if (root != null) {
            s.push(root);
        }
        while (!s.isEmpty()) {
            Node n = s.pop();
            System.out.print(n.value + " ");
            if (n.right != null)
                s.push(n.right);
            if (n.left != null)
                s.push(n.left);
        }
    }

    public static int berechneHoehe(Node node) {
        if (node == null) {
            return -1;
        }
        return Math.max(berechneHoehe(node.left), berechneHoehe(node.right)) + 1;
    }

    public static Node arrayTreeToNodeTree(int[] tree) {
        return arrayTreeToNodeTree(tree, 0);
    }

    public static Node arrayTreeToNodeTree(int[] tree, int pos) {
        if ((pos >= tree.length) || (tree[pos] < 0)) {
            return null;
        }
        Node nodeTree = new Node();
        nodeTree.value = tree[pos];
        nodeTree.left = arrayTreeToNodeTree(tree, pos * 2 + 1);
        nodeTree.right = arrayTreeToNodeTree(tree, pos * 2 + 2);
        return nodeTree;
    }

    public static int zaehleBlaetter(Node node) {
        if (node == null) {
            return 0;
        }
        if ((node.left == null) && (node.right == null)) {
            return 1;
        }
        return zaehleBlaetter(node.left) + zaehleBlaetter(node.right);
    }

    public static int berechneOrdnung(Node node) {
        int ordnung = 0;
        int ordnungLinks = 0;
        int ordnungRechts = 0;
        if (node.left != null) {
            ordnung += 1;
            ordnungLinks = berechneOrdnung(node.left);
        }
        if (node.right != null) {
            ordnung += 1;
            ordnungRechts = berechneOrdnung(node.right);
        }
        return Math.max(ordnung, Math.max(ordnungLinks, ordnungRechts));
    }

    public static Node createMaxDeepTree(int n) {
        if (n == 0)
            return null;
        Node node = new Node();
        node.value = n;
        node.left = createMaxDeepTree(n - 1);
        return node;
    }

    public static Node createMinDeepTree(int n) {
        if (n == 0)
            return null;
        Node node = new Node();
        node.value = n;
        node.left = createMinDeepTree(n / 2);
        node.right = createMinDeepTree(n / 2);
        return node;
    }
}
