package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<T> implements Graph<T>{

    private Map<T, Set<T>> graph;

    public GraphImpl(){
        graph = new HashMap<>();
    }

    @Override
    public void addNode(T node) {
        graph.put(node, new HashSet<>());
    }

    @Override
    public void addEdge(T source, T target) {
       if(graph.containsKey(source) && graph.containsKey(target)){
        graph.get(source).add(target);
       }
    }

    @Override
    public Set<T> nodeSet() {
        Set<T> result = new HashSet<>();
        Collection<Set<T>> coll = graph.values();
        for (Collection<T> c : coll){
            result.addAll(c);
        }
        return result;
    }

        

    @Override
    public Set<T> linkedNodes(T node) {
        if(graph.containsKey(node)){
            return graph.get(node);
        }
        return null;
    }

    @Override
    public List<T> getPath(T source, T target) {
        List<T> path = new ArrayList<>();
        path.add(source);
        
        while(!path.contains(target)){
            Set<T> coll = graph.get(source);
            var it = coll.iterator();
            int bruteForce = 0;
            while (it.hasNext() && bruteForce != 1){
                T part = it.next();
                path.add(part);
                source = part;
                bruteForce++;
            }
        }
        return path;

    }

    

    

   
    

}
