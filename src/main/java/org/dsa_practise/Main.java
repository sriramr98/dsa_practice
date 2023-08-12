package org.dsa_practise;

import org.dsa_practise.dsa.graphs.Graph;
import org.dsa_practise.dsa.graphs.models.WeightedVertex;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false, false);
        WeightedVertex<Integer> one = graph.addVertex(1);
        WeightedVertex<Integer> two = graph.addVertex(2);
        WeightedVertex<Integer> three = graph.addVertex(3);
        WeightedVertex<Integer> four = graph.addVertex(4);
        WeightedVertex<Integer> five = graph.addVertex(5);

        graph.addEdge(one, two, 0);
        graph.addEdge(one, three, 0);
        graph.addEdge(one, four, 0);

        graph.addEdge(three, four, 0);
//        graph.addEdge(two, four, 0);
        graph.addEdge(four, five, 0);

        graph.print();

        List<Integer> traversalResult = graph.breadthFirstTraversal(three);
        System.out.println("Breadth Traversal");
        System.out.println(traversalResult);

        System.out.println("Depth traversal");
        List<Integer> depthTraversalList = graph.depthFirstTraversal(three);
        System.out.println(depthTraversalList);


    }
}