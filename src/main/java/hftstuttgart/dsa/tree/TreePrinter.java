package hftstuttgart.dsa.tree;

// Taken from: https://www.baeldung.com/java-print-binary-tree-diagram

// Modified to print both children even if null

public class TreePrinter {

    public static String print(Node root) {
        return traversePreOrder(root);
    }

    private static String traversePreOrder(Node root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.value);

        String pointerRight = "└──";
        // String pointerLeft = (root.right != null) ? "├──" : "└──";
        String pointerLeft = "├──";

        // traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
        if ((root.left != null) || (root.right != null)) {
            traverseNodes(sb, "", pointerLeft, root.left, true);
            traverseNodes(sb, "", pointerRight, root.right, false);
        }
        return sb.toString();
    }

    private static void traverseNodes(StringBuilder sb, String padding, String pointer, Node node,
            boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            // String pointerLeft = (node.right != null) ? "├──" : "└──";
            String pointerLeft = "├──";

            // traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right !=
            // null);
            if ((node.left != null) || (node.right != null)) {
                traverseNodes(sb, paddingForBoth, pointerLeft, node.left, true);
                traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);
            }
        } else {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
        }
    }
}
