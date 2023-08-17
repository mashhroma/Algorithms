/*
 * 1. Превратить собранное на семинаре дерево поиска в полноценное левостороннее красно-черное дерево.
 * 2. Реализовать метод балансировки, имеет 3 операции: левый малый поворот, правый малый поворот, смена цвета.
 * 3. Реализовать метод добавления новых элементов с балансировкой.
 * 
 * Красно-черное дерево имеет следующие критерии:
 * 1. Корень дерева всегда черный
 * 2. Новая нода всегда красная
 * 3. Красные ноды могут быть только левым ребенком (красные ноды могут находится только слева)
 * 4. У красной ноды все дети черного цвета
 */

import java.util.ArrayList;
import java.util.List;

class BinaryTree {

    Node root;

    class Node {
        int value;
        Node left;
        Node right;
        Color color;
    }

    private enum Color {
        RED, BLACK
    }

    boolean push(int value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            root.color = Color.BLACK;
            return true;
        } else {
            Node node = root;
            while (node != null) {
                if (node.value == value) {
                    return false;
                }
                if (node.value < value) {
                    if (node.right == null) {
                        node.right = new Node();
                        node.right.value = value;
                        node.right.color = Color.RED;
                        node = rebalance(node);
                        return true;
                    } else {
                        node = node.right;
                    }
                } else {
                    if (node.left == null) {
                        node.left = new Node();
                        node.left.value = value;
                        node.left.color = Color.RED;
                        node = rebalance(node);
                        return true;
                    } else {
                        node = node.left;
                    }
                }
            }
            return false;
        }
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null
                    && result.right.color == Color.RED
                    && (result.left == null || result.left.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.left != null
                    && result.left.color == Color.RED
                    && result.left.left != null
                    && result.left.left.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null
                    && result.left.color == Color.RED
                    && result.right != null
                    && result.right.color == Color.RED) {
                needRebalance = true;
                colorSwap(result);
            }
        } while (needRebalance);
        root.color = Color.BLACK;
        return result;
    }

    private void colorSwap(Node node) {
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    private Node leftSwap(Node node) {
        Node leftChild = node.left;
        Node betweenChild = leftChild.right;
        leftChild.right = node;
        node.left = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    private Node rightSwap(Node node) {
        Node rightChild = node.right;
        Node betweenChild = rightChild.left;
        rightChild.left = node;
        node.right = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    boolean find(int value) {
        Node node = root;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            if (node.value < value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return false;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    void printTree() {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            List<Node> line = new ArrayList<>();
            line.add(root);

            while (line.size() > 0) {
                List<Node> nextLine = new ArrayList<>();
                for (Node node : line) {
                    if (node.color == Color.BLACK) {
                        System.out.print(ANSI_GREEN_BACKGROUND + node.value + ANSI_RESET);
                    }
                    if (node.color == Color.RED) {
                        System.out.print(ANSI_RED_BACKGROUND + node.value + ANSI_RESET);
                    }
                    if (node.right != null)
                        nextLine.add(node.right);
                    if (node.left != null)
                        nextLine.add(node.left);
                }
                System.out.println();
                line = nextLine;
            }
        }
    }
}