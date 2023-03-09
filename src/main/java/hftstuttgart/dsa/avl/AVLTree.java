package hftstuttgart.dsa.avl;

import java.util.Deque;
import java.util.LinkedList;

class AVLNode {
    int value;
    int height;
    AVLNode left, right;
    AVLNode parentNode;
    char parentDir;

    public AVLNode(int value) {
        height = 1;
        this.value = value;
    }

    public void removeChild(char dir) {
        if (dir == 'l') {
            this.left = null;
            if (this.right != null) {
                height = this.right.height + 1;
            }
        }
    }

    public int balance() {
        return height(right) - height(left);
    }

    public boolean isBalanced() {
        int balance = balance();
        return (balance >= -1) && (balance <= 1);
    }

    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    private void updateHeight() {
        int heightLeft = height(left);
        int heightRight = height(right);
        height = 1+ ((heightLeft > heightRight) ? heightLeft : heightRight);
        if(parentNode != null) {
            parentNode.updateHeight();
        }
    }

    public void setChild(char dir, AVLNode node) {
        if (dir == 'l') {
            left = node;
        } else if (dir == 'r') {
            right = node;
        }
        node.parentNode = this;
        node.parentDir = dir;
        updateHeight();
    }
}

public class AVLTree {
    AVLNode root;

    public void insert(int value) {
        if (root == null) {
            this.root = new AVLNode(value);
        } else {
            AVLNode node = null;
            AVLNode next = this.root;
            char dir;
            do {
                node = next;
                if (value < node.value) {
                    next = node.left;
                    dir = 'l';
                } else if (value > node.value) {
                    next = node.right;
                    dir = 'r';
                } else {
                    // Value already exists - ignore
                    return;
                }
            } while(next != null);
            
            next = new AVLNode(value);
            node.setChild(dir, next);
            
            // char p
            while((node != null) && (node.isBalanced())) {
                node = node.parentNode;
            }
            if(node != null) {

            }
        }
    }
}
