package com.htec.service.algorithm;

import com.htec.api.dto.route.RouteDtoRequest;


import java.util.*;

public class GraphShow {
    public static void main(String[] args) {
        List<RouteDtoRequest> rute = new ArrayList<>();
        rute.add(new RouteDtoRequest(10.0, "BG", "BEC"));
        rute.add(new RouteDtoRequest(5.0, "BG", "TIVAT"));
        rute.add(new RouteDtoRequest(7.0, "BG", "PODGORICA"));
        rute.add(new RouteDtoRequest(5.0, "BG", "NIS"));
        rute.add(new RouteDtoRequest(5.0, "NIS", "NEMACKA1"));
        rute.add(new RouteDtoRequest(20.0, "BEC", "TUNIS"));
        rute.add(new RouteDtoRequest(20.0, "TUNIS", "NEMACKA"));

        rute.add(new RouteDtoRequest(15.0, "TIVAT", "BEC"));
        rute.add(new RouteDtoRequest(10.0, "NIS", "BG"));
        GraphWeighted graphWeighted = new GraphWeighted(true);
        Set<NodeWeighted> nodeWeightedSet = new HashSet<>();
        for(RouteDtoRequest r: rute) {
            nodeWeightedSet.add(new NodeWeighted(r.getSourceCode()));
            nodeWeightedSet.add(new NodeWeighted( r.getDestinationCode()));
            Optional<NodeWeighted> nodeWeighted = nodeWeightedSet.stream().filter(a->a.getName().equals(r.getSourceCode())).findFirst();
            Optional<NodeWeighted> nodeWeighted1 = nodeWeightedSet.stream().filter(a->a.getName().equals(r.getDestinationCode())).findFirst();
            graphWeighted.addEdge(nodeWeighted.get(),nodeWeighted1.get(), r.getPrice());
        }
        for(NodeWeighted n :nodeWeightedSet){
            Optional<NodeWeighted> nodeWeighted = nodeWeightedSet.stream().filter(a->a.getName().equals("BG")).findFirst();
            Optional<NodeWeighted> nodeWeighted1 = nodeWeightedSet.stream().filter(a->a.getName().equals("NEMACKA")).findFirst();
            if(nodeWeighted.isPresent() && nodeWeighted1.isPresent()){
                graphWeighted.dijkstraShortestPath(nodeWeighted.get(), nodeWeighted1.get());
                break;
            }
        }

    }
}