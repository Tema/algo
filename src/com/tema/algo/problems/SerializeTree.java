package com.tema.algo.problems;

//TODO: multiple char node value
//TODO: escape brackets in node values
//DONE: not normalized form - no brackets around null child
public class SerializeTree {

    public static void main(String[] args) {
        System.out.println(toString(parse("((A)B(C))D(E(F))")));
    }

    public static String toString(Node root){
        StringBuilder sb = new StringBuilder();
        traverse(root, sb);
        return sb.toString();
    }

    public static void traverse(Node node, StringBuilder sb){
        if (node == null){
            return;
        }

        sb.append("(");
        traverse(node.left, sb);
        sb.append(node.value);
        traverse(node.right, sb);
        sb.append(")");
    }

    public static Node parse(String str){
        return parse(str.toCharArray(), new int[]{0});
    }

    public static Node parse(char[] ch, int[] i){
        Node left = parseChild(ch, i);
        Node node = new Node(pop(ch, i)+ "");
        Node right = parseChild(ch, i);

        node.left = left;
        node.right = right;
        return node;
    }

    private static Node parseChild(char[] ch, int[] i) {
        Node node = null;
        if (peek(ch, i) == '('){
            pop(ch, i);
            if (peek(ch, i) != ')'){
                node = parse(ch, i);
            }
            if (pop(ch, i) != ')'){
                throw new RuntimeException("Closing bracket is expected: " + error(ch, i, -1));
            }
        }
        return node;
    }

    public static char pop(char[] ch, int[] i){
        return ch[i[0]++];
    }

    public static char peek(char[] ch, int[] i){
        if (ch.length <= i[0]){
            return 0;
        }
        return ch[i[0]];
    }

    public static String error(char[] ch, int[] i, int p){
        StringBuilder sb = new StringBuilder(new String(ch));
        sb.insert(i[0] + p, "^");
        return sb.toString();

    }

}
