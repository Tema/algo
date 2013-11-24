package com.tema.algo.problems;

//TODO: check levels
public class CheckBSTProblem {

    public boolean isBST(Node root){
        try {
            traverse(root);
            return true;
        } catch (NonBSTException e) {
            return false;
        }
    }

    private Result traverse(Node node){
        if (node.left != null && node.right != null){
            Result left = traverse(node.left);
            Result right = traverse(node.right);
            if (left.max > node.value || right.min < node.value){
                throw new NonBSTException();
            }
            return new Result(left.min, right.max);
        }

        if (node.left != null){
            Result left = traverse(node.left);
            if (left.max > node.value){
                throw new NonBSTException();
            }
            return new Result(left.min, node.value);
        }

        if (node.right != null){
            Result right = traverse(node.right);
            if (right.min < node.value){
                throw new NonBSTException();
            }
            return new Result(node.value, right.max);
        }

        return new Result(node.value);

    }

    static class NonBSTException extends RuntimeException{}

    static class Result{
        int min, max;

        Result(int v) {
            this(v, v);
        }

        Result(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    static class Node{
        int value;
        Node left;
        Node right;
    }
}
