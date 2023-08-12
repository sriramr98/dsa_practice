package org.dsa_practise.dsa.graphs.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeightedVertex<T> {

    private T data;
    private final List<WeightedEdge<T>> edges;

    public WeightedVertex(T data) {
        this.data = data;
        this.edges = new ArrayList<>();
    }

    public void addEdge(WeightedVertex<T> vertex, Integer weight) {
        this.edges.add(new WeightedEdge<>(this, vertex, weight));
    }

    public void removeEdge(WeightedVertex<T> vertex) {
        this.edges.removeIf(edge -> edge.getEnd().equals(vertex));
    }

    public void print() {
        StringBuilder messageBuilder = new StringBuilder();

        if (this.edges.isEmpty()) {
            System.out.println(this.data + " -->");
            return;
        }

        for (int i=0; i<this.edges.size(); i++) {
            WeightedEdge<T> edge = this.edges.get(i);
            if (i==0) {
                messageBuilder
                        .append(edge.getStart().data)
                        .append(" --> ");
            }

            messageBuilder
                    .append(edge.getEnd().data)
                    .append("(").append(edge.getWeight()).append(")");

            if (i != this.edges.size() - 1) {
                messageBuilder.append(", ");
            }
        }

        System.out.println(messageBuilder);
    }

}
