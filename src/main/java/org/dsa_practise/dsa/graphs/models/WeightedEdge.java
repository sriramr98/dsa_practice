package org.dsa_practise.dsa.graphs.models;

import lombok.Getter;
import lombok.Setter;
import org.dsa_practise.dsa.graphs.abstracts.BaseEdge;

@Getter
@Setter
public class WeightedEdge<T> extends BaseEdge<T> {

    private Integer weight;

    public WeightedEdge(WeightedVertex<T> start, WeightedVertex<T> end, Integer weight) {
        super(start, end);
        this.weight = weight;
    }

}
