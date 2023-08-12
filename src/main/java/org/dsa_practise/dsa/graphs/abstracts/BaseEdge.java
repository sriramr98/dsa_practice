package org.dsa_practise.dsa.graphs.abstracts;

import lombok.Data;
import org.dsa_practise.dsa.graphs.models.WeightedVertex;

@Data
public abstract class BaseEdge<T> {
    private WeightedVertex<T> start;
    private WeightedVertex<T> end;

    public BaseEdge(WeightedVertex<T> start, WeightedVertex<T> end) {
        this.start = start;
        this.end = end;
    }
}
