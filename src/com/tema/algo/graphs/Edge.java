package com.tema.algo.graphs;

public class Edge {

    public Vertex head;
    public Vertex tail;
    public int value;

    public Edge(Vertex tail, int value) {
        this(null, tail, value);
    }

    public Edge(Vertex head, Vertex tail, int value) {
        this.head = head;
        this.tail = tail;
        this.value = value;
    }
}
