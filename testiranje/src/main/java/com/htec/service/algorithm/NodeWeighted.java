package com.htec.service.algorithm;

import java.util.LinkedList;
import java.util.Objects;

public class NodeWeighted {

    private String name;
    private boolean visited;
    private LinkedList<EdgeWeighted> edges;

    public NodeWeighted(String name) {
        this.name = name;
        visited = false;
        edges = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public LinkedList<EdgeWeighted> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<EdgeWeighted> edges) {
        this.edges = edges;
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