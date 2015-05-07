package com.tema.algo.sort;

public class LinkedListMergeSort {

    public static Node solve(Node list) {
        return sort(list, size(list)).head;
    }

    private static int size(Node list) {
        int count = 0;
        while (list != null) {
            count++;
            list = list.next;
        }
        return count;
    }

    private static Pair sort(Node head, int size) {
        if (size == 0) {
            return new Pair(null, null);
        } else if (size == 1) {
            Node next = head.next;
            head.next = null;
            return new Pair(head, next);
        }

        Pair pair = sort(head, size / 2);
        if (pair.next == null) {
            return pair;
        }
        Pair pair2 = sort(pair.next, size - size / 2);
        return new Pair(merge(pair.head, pair2.head), pair2.next);
    }

    private static Node merge(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }
        Node node = null;
        Node head = null;
        while (head1 != null || head2 != null) {
            if (head1 == null) {
                node.next = head2;
                break;
            } else if (head2 == null) {
                node.next = head1;
                break;
            } else {
                Node nextNode;
                if (head1.value <= head2.value) {
                    nextNode = head1;
                    head1 = head1.next;
                } else {
                    nextNode = head2;
                    head2 = head2.next;
                }
                if (head == null) {
                    head = nextNode;
                } else {
                    node.next = nextNode;
                }
                node = nextNode;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("Result: " + toString(solve(parse(""))));
        System.out.println("Result: " + toString(solve(parse("1"))));
        System.out.println("Result: " + toString(solve(parse("1 2"))));
        System.out.println("Result: " + toString(solve(parse("2 1"))));
        System.out.println("Result: " + toString(solve(parse("1 2 3"))));
        System.out.println("Result: " + toString(solve(parse("1 3 2"))));
        System.out.println("Result: " + toString(solve(parse("2 1 3"))));
        System.out.println("Result: " + toString(solve(parse("2 3 1"))));
        System.out.println("Result: " + toString(solve(parse("3 1 2"))));
        System.out.println("Result: " + toString(solve(parse("3 2 1"))));
        System.out.println("Result: " + toString(solve(parse("1 1 1 1"))));
        System.out.println("Result: " + toString(solve(parse("1 2 1 2"))));
    }

    private static String toString(Node node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.value);
            node = node.next;
            if (node != null) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static Node parse(String list) {
        if (list.isEmpty()) {
            return null;
        }
        Node node = null;
        Node head = null;
        for (String v : list.split(" ")) {
            Node next = new Node(Integer.valueOf(v));
            if (node == null) {
                head = next;
            } else {
                node.next = next;
            }
            node = next;
        }
        return head;
    }

    private static class Node {
        private final int value;
        private Node next;

        public Node(int v) {
            this.value = v;
        }
    }

    private static class Pair {
        private final Node head;
        private final Node next;

        private Pair(Node head, Node next) {
            this.head = head;
            this.next = next;
        }
    }
}
