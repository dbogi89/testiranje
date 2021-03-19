package com.htec.service.algorithm;

import java.util.LinkedList;
import java.util.Objects;

public class NodeWeighted {

    String name;
    private boolean visited;
    LinkedList<EdgeWeighted> edges;

    NodeWeighted( String name) {
        this.name = name;
        visited = false;
        edges = new LinkedList<>();
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeWeighted that = (NodeWeighted) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}