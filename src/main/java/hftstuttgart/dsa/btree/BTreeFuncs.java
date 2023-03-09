package hftstuttgart.dsa.btree;

public class BTreeFuncs {
    public static void set(BTree tree, int key, String value) {
        if (tree.root == null) {
            // Leerer Baum - erstelle einzelnes Blatt als Knoten
            tree.root = new DataNode(key, value);
        } else if (tree.root instanceof DataNode) {
            DataNode data = (DataNode) tree.root;
            if (data.key == key) {
                // Key schon vorhanden - Daten überschreiben
                data.value = value;
            } else {
                // Bisher einzelner DataNode, nun brauchen wir einen KeyNode
                KeyNode root = new KeyNode(tree.m);
                // 2 Kinder: das bisherige und ein neues
                root.nChildren = 2;
                // Welcher Knoten ist kleiner?
                if (key < data.key) {
                    root.max[0] = key;
                    root.children[0] = new DataNode(key, value);
                    root.children[1] = data;
                } else {
                    root.max[0] = data.key;
                    root.children[0] = data;
                    root.children[1] = new DataNode(key, value);
                }
                // Setze KeyNode als neue Wurzel
                tree.root = root;
            }
        } else if (tree.root instanceof KeyNode) {
            final KeyNode node = (KeyNode) tree.root;
            //
            tree.root = setIntoKeyNode(node, key, value);
        }
    }

    public static KeyNode setIntoKeyNode(KeyNode node, int key, String value) {
        // Welche Position ist die richtige?
        int i = 0;
        // An die erste Position, wo key kleiner gleich dem max-Wert ist
        // Sonst an die letzte besetzte Position
        while ((i < node.nChildren - 1) && (key > node.max[i]))
            i++;
        // In node.children[i] ist der nächste Knoten aber
        // ggfs. müssen wir einen zusätzlichen einfügen
        BNode toInsert = null;
        // Sind wir schon auf der letzten Ebene?
        if (node.children[i] instanceof KeyNode) {
            KeyNode subtree = (KeyNode) node.children[i];
            // Nein - weiter rekursiv
            toInsert = setIntoKeyNode(subtree, key, value);
        } else if (node.children[i] instanceof DataNode) {
            DataNode data = (DataNode) node.children[i];
            // Ja - existiert der key schon?
            if (data.key == key) {
                // Ja - Wert updaten
                data.value = value;
                return node;
            } else {
                // Nein - erstelle neue DataNode
                toInsert = new DataNode(key, value);
            }
        }
        // Wenn toInsert anders ist als das selektierte Kind, dann müssen wir es
        // einfuegen
        if (toInsert != node.children[i]) {
            // Wir fügen einen neues Element ein - haben wir Platz dafür?
            if (node.nChildren < node.m) {
                // Es ist noch Platz - wir verschieben einfach alles nach rechts
                node.nChildren += 1;
                for (int j = node.nChildren; j > i; j--) {
                    node.children[j] = node.children[j - 1];
                }
                node.children[i] = toInsert;
                return node;
            } else {
                // Kein Platz mehr - splitten
                // Der neue Geschwisterknoten kommt an Position i, d.h.
                // links neben das aktuelle node.children[i]
                KeyNode newSibling = new KeyNode(node.m);

                // Von rechts auffüllen - wir hatten bisher node.m children und nun eins mehr
                // - der Array-index fängt aber bei 0 an zu zählen also erste Zielposition:
                // tree.m
                // - So lange wir über "split" sind, muss es in die neue Node
                int split = node.m / 2;
                for (int j = node.m; j >= 0; j--) {
                    // Welches Element wird an die Position gesetzt?
                    BNode toMove = null;
                    if (j < i) {
                        // Links von i nehmen wir einfach das Element aus dem bisherigen Node
                        toMove = node.children[j];
                    } else if (j > i) {
                        // Rechts von i nehmen wir das Elements eins darunter
                        toMove = node.children[j - 1];
                    } else {
                        // Bei i nehmen wir das toInsert Element
                        toMove = toInsert;
                    }
                    if (j > split) {
                        node.children[j - split] = toMove;
                    } else {
                        newSibling.children[j] = toMove;
                    }
                }
                newSibling.nChildren = node.m - split;
                node.nChildren = split + 1;
                // Der neue Knoten wird zurückgeliefert, d.h. auf höherer Ebene
                // wird das gleiche rekursiv ausgeführt
                return newSibling;
            }
        }
        // Auf dieser Ebene ist alles beim alten geblieben - keine neue Node:
        return node;
    }


    public static String print(BTree tree) {
        return traversePreOrder(tree.root);
    }

    private static String traversePreOrder(BNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        if(root instanceof DataNode) {
            DataNode data = (DataNode) root;
            sb.append(data.key).append(" -> ").append(data.value);
        } else if(root instanceof KeyNode) {
            KeyNode node = (KeyNode) root;
            for(int i = 0; i < node.nChildren; i++) {
                boolean last = i == node.nChildren - 1;
                traverseNodes(sb, "", "|-- " + (last ? "__" : node.max[i]), node.children[i], !last);
            }
        }
        return sb.toString();
    }

    private static void traverseNodes(StringBuilder sb, String padding, String pointer, BNode pnode,
            boolean hasRightSibling) {
        if(pnode instanceof DataNode) {
            DataNode data = (DataNode) pnode;
            sb.append("\n").append(padding).append(pointer).append(data.key).append(" -> ").append(data.value);
        } else if(pnode instanceof KeyNode) {
            KeyNode node = (KeyNode) pnode;
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            // sb.append(node.value);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            
            for(int i = 0; i < node.nChildren; i++) {
                boolean last = i == node.nChildren - 1;
                traverseNodes(sb, paddingForBoth, (last ? "└──" : "├──"), node.children[i], !last);
            }
        } 
    }
}
