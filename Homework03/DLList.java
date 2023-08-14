package Homework03;

public class DLList {

    Node head;
    Node tail;

    private class Node {
        int value;
        Node prev;
        Node next;

        private Node() {
        }

        private Node(int value) {
            this.value = value;
        }

        private Node(int value, Node prev) {
            this(value);
            this.prev = prev;
        }

        private Node(int value, Node prev, Node next) {
            this(value, prev);
            this.next = next;
        }
    }

    public void print() {
        Node currNode = head;
        System.out.print("[ ");
        while (currNode != null) {
            if (currNode.next == null) {
                System.out.print(currNode.value);
            } else {
                System.out.printf("%d, ", currNode.value);
            }
            currNode = currNode.next;
        }
        System.out.print(" ]");
    }

    public void addFirst(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    public void reverse() {
        if (head != null && head.next != null) {
            if (head.next.next == null) {
                int temp = head.value;
                head.value = tail.value;
                tail.value = temp;
            } else {
                Node currLeft = head;
                Node currRight = tail;

                Node currNext = currLeft.next;
                Node currPrev = currRight.prev;

                currLeft.prev = currRight.prev;
                currRight.next = currLeft.next;

                currLeft.next = null;
                currRight.prev = null;

                currNext.prev = currRight;
                currPrev.next = currLeft;

                head = currRight;
                tail = currLeft;

                currLeft = head.next;
                currRight = tail.prev;

                while (currLeft != currRight && currLeft.next != currRight) {
                    Node prevLeft = currLeft.prev;
                    Node nextLeft = currLeft.next;
                    Node prevRight = currRight.prev;
                    Node nextRight = currRight.next;

                    currLeft.next = nextRight;
                    currLeft.prev = prevRight;
                    currRight.next = nextLeft;
                    currRight.prev = prevLeft;

                    prevLeft.next = currRight;
                    prevRight.next = currLeft;

                    nextLeft.prev = currRight;
                    nextRight.prev = currLeft;

                    currLeft = nextLeft;
                    currRight = prevRight;
                }
                if (currLeft != currRight) {
                    int temp = currLeft.value;
                    currLeft.value = currRight.value;
                    currRight.value = temp;
                }

            }
        }
    }
}
