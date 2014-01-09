package com.tema.algo.graphs;

import java.security.PrivateKey;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

    public int shortestPath(Vertex source, Vertex target){
        Set<Vertex> visited = new HashSet<Vertex>();
        PriorityQueue<Struct> queue = new PriorityQueue<Struct>();
        queue.add(new Struct(source));
        while (!queue.isEmpty()){
            Struct best = queue.poll();
            if (!visited.add(best.v)){
                continue;//already visited
            }
            if (best.v == target){
                return best.sum;
            }
            for (Edge e : best.v.edges) {
                queue.add(new Struct(best, e));
            }
        }

        return Integer.MIN_VALUE;

    }

    static class Struct implements Comparable<Struct>{
        Vertex v;
        int sum = 0;

        Struct(Vertex v) {
            this.v = v;
        }

        Struct(Struct parent, Edge e) {
            v = e.tail;
            sum = parent.sum + e.value;
        }

        @Override
        public int compareTo(Struct struct) {
            return sum - struct.sum;
        }
    }
}
