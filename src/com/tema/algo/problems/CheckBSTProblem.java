package com.tema.algo.problems;

public class CheckBSTProblem {

    public boolean isBST(Node root){
        try {
            traverse(root, 0);
            return true;
        } catch (NonBSTException e) {
            return false;
        }
    }

    private Result traverse(Node node, int level){
        level++;
        if (node.left != null && node.right != null){
            Result left = traverse(node.left, level);
            Result right = traverse(node.right, level);
            if (left.max > node.value || right.min < node.value || Math.abs(left.level - right.level) > 1){
                throw new NonBSTException();
            }
            return new Result(left.min, right.max, Math.max(left.level, right.level));
        }

        if (node.left != null){
            Result left = traverse(node.left, level);
            if (left.max > node.value || Math.abs(left.level - level) > 1){
                throw new NonBSTException();
            }
            return new Result(left.min, node.value, left.level);
        }

        if (node.right != null){
            Result right = traverse(node.right, level);
            if (right.min < node.value || Math.abs(right.level - level) > 1){
                throw new NonBSTException();
            }
            return new Result(node.value, right.max, right.level);
        }

        return new Result(node.value, node.value, level);

    }

    static class NonBSTException extends RuntimeException{}

    static class Result{
        int level;
        int min, max;

        Result(int min, int max, int level) {
            this.min = min;
            this.max = max;
            this.level = level;
        }
    }

    static class Node{
        int value;
        Node left;
        Node right;
    }
}
