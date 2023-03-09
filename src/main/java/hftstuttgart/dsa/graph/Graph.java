package hftstuttgart.dsa.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph {
    public List<Vertex> vertices;
    public List<Edge> edges;
    public boolean directed = false;
    public boolean weighted = false;

    public List<Edge> edgesFrom(Vertex v) {
        return edges.stream().filter((e) -> e.start.equals(v) || (!directed && e.end.equals(v)))
                .collect(Collectors.toList());
    }

    public List<Vertex> connectedTo(Vertex v) {
        return edgesFrom(v).stream()
                .map(e -> e.getOther(v))
                .collect(Collectors.toList());
    }

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public boolean hasEdge(String from, String to) {
        Vertex start = getVertex(from);
        Vertex end = getVertex(to);
        if((start == null) || (end == null)) {
            return false;
        }
        return connectedTo(start).contains(end);
    }

    public Vertex getVertex(String value) {
        return vertices.stream().filter(v -> v.value.equals(value))
                .findFirst().orElse(null);
    }

    public Vertex getOrCreateVertex(String value) {
        Vertex vertex = getVertex(value);
        if (vertex == null) {
            vertex = new Vertex(value);
            vertices.add(vertex);
        }
        return vertex;
    }

    public Edge newEdge(String start, String end, int weight) {
        Vertex vStart = getOrCreateVertex(start);
        Vertex vEnd = getOrCreateVertex(end);
        Edge e = new Edge(vStart, vEnd, weight);
        edges.add(e);
        return e;
    }

    public void newEdge(String start, String end) {
        newEdge(start, end, 0);
    }

    public static void main(String args[]) {
        Graph g = new Graph();
        GraphPlot.graphToMermaid(g);
        g.newEdge("a", "b");
        GraphPlot.graphToMermaid(g);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
        result = prime * result + ((edges == null) ? 0 : edges.hashCode());
        result = prime * result + (directed ? 1231 : 1237);
        result = prime * result + (weighted ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Graph other = (Graph) obj;
        if (vertices == null) {
            if (other.vertices != null)
                return false;
        } else if (!vertices.equals(other.vertices))
            return false;
        if (edges == null) {
            if (other.edges != null)
                return false;
        } else if (!edges.equals(other.edges))
            return false;
        if (directed != other.directed)
            return false;
        if (weighted != other.weighted)
            return false;
        return true;
    }
}
