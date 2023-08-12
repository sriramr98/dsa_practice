package org.dsa_practise.dsa.graphs;

import lombok.Getter;
import org.dsa_practise.dsa.graphs.models.WeightedVertex;

import java.util.*;

@Getter
public class Graph<T> {

    private final List<WeightedVertex<T>> vertices;
    private final boolean isWeighted;
    private final boolean isDirected;

    public Graph(boolean isWeighted, boolean isDirected) {
        this.isWeighted = isWeighted;
        this.isDirected = isDirected;
        this.vertices = new ArrayList<>();
    }

    public WeightedVertex<T> addVertex(T data) {
        WeightedVertex<T> vertex = new WeightedVertex<>(data);
        this.vertices.add(vertex);

        return vertex;
    }

    public void addEdge(WeightedVertex<T> start, WeightedVertex<T> end, Integer weight) {
        if (!isWeighted) {
            weight = null;
        }

        start.addEdge(end, weight);
        if (!isDirected) {
            end.addEdge(start, weight);
        }
    }

    public void removeEdge(WeightedVertex<T> vertex1, WeightedVertex<T> vertex2) {
        vertex1.removeEdge(vertex2);
        if (!isDirected) {
            vertex2.removeEdge(vertex1);
        }
    }

    public void removeVertex(WeightedVertex<T> vertex) {
        this.vertices.forEach(vertice -> vertice.removeEdge(vertex));
        this.vertices.remove(vertex);
    }

    public List<T> breadthFirstTraversal(WeightedVertex<T> rootVertex) {
        Set<T> visited = new HashSet<>();
        Queue<WeightedVertex<T>> tracker = new LinkedList<>();

        List<T> result = new ArrayList<>();
        tracker.add(rootVertex);

        while (!tracker.isEmpty()) {
            WeightedVertex<T> node = tracker.poll();
            if (visited.contains(node.getData())) {
               continue;
            }

            visited.add(node.getData());
            result.add(node.getData());
            node.getEdges().forEach(edge -> tracker.add(edge.getEnd()));
        }

        return result;
    }

    public List<T> depthFirstTraversal(WeightedVertex<T> rootVertex) {
        Set<T> visited = new HashSet<>();
        List<T> results = new ArrayList<>();

        traverseDepth(rootVertex, visited, results);

        return results;
    }

    private void traverseDepth(WeightedVertex<T> node, Set<T> visited, List<T> result) {
        if (visited.contains(node.getData())) {
            return;
        }

        visited.add(node.getData());
        result.add(node.getData());

        node.getEdges().forEach(edge -> traverseDepth(edge.getEnd(), visited, result));
    }

    public void print() {
        vertices.forEach(vertice -> vertice.print());
    }
}
