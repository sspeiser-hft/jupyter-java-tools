package hftstuttgart.dsa.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

import hftstuttgart.dsa.tree.TreePlot;

public class GraphPlot {

    public static String graphToMermaid(Graph graph) {
        String id = UUID.randomUUID().toString().replace("-", "");
        // TODO: Templating Engine

        StringBuilder html = new StringBuilder();
        
        html.append("<div style=\"width=400px;height=400px\" id=\"").append(id).append("\" class=\"mermaid\"></div>");
                
        StringBuilder graphDefinition = new StringBuilder();
        graphDefinition.append("\"graph LR\\n");
        
        Set<Vertex> visited = new HashSet<>();

        for(Edge e : graph.edges) {
            graphDefinition.append("    " + e.start.hashCode() + "((" + e.start.value + ")) --" 
                                    + (graph.directed ? ">" : "-")
                                    + (graph.weighted ? "|" + e.weight + "|" : "")
                                    + " " + e.end.hashCode() + "((" + e.end.value + "))\\n");
            visited.add(e.start);
            visited.add(e.end);
        }

        for(Vertex v : graph.vertices) {
            if(!visited.contains(v)) {
                graphDefinition.append("    " + v.hashCode() + "((" + v.value + "))\\n");    
            }
        }
    
        if(graph.vertices.isEmpty()) {
            graphDefinition.append("    1(Empty graph)\\n");
            
        }
        
        graphDefinition.append("\"");

        html.append("<script type=\"module\">\n"
                + "import mermaid from 'https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs';\n");
                
        html.append(TreePlot.getElementById(id)).append(".innerHTML = ").append(graphDefinition).append(";\n");
       
        html.append("mermaid.init(undefined, ").append(TreePlot.getElementById(id)).append(");\n");

        html.append("</script>\n");
        return html.toString();
    }

}
