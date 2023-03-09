package hftstuttgart.dsa.tree;

import static io.github.spencerpark.ijava.runtime.Display.display;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class TreePlot {

    private static String id = null;

    private static String idDIV = null;
    private static String idJS = null;

    public static void enableMermaid() {
        // String js = "<script type=\"module\" src=\"https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs\"></script>\n" 
            // +  "<script>import mermaid from 'https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs';</script>";

        // String js = "<script>\n mermaidM = await import('https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs');</script>";
        // // + " mermaid = mermaidM.default;\n"
        // + " </script>\n";
        String js = "<script type=\"module\">\n"
                + "import mermaid from 'https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs';\n"
                + "mermaid.initialize({ startOnLoad: true });\n"
                + "</script>\n";
        display(js, "text/html");
    }

    public static void displayMermaid(String id) {
        String js = "<script type=\"module\">\n"
                + "import mermaid from 'https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs';\n"
                + "mermaid.initialize({ startOnLoad: true });\n"
                + "</script>\n";
        display(js, "text/html");
    }

    public static String treeToMermaid(Node tree) {
        id = UUID.randomUUID().toString().replace("-", "");
        // TODO: Templating Engine

        StringBuilder html = new StringBuilder();
        
        html.append("<div style=\"width=400px;height=400px\" id=\"").append(id).append("\" class=\"mermaid\"></div>");
                
        StringBuilder graphDefinition = new StringBuilder();
        graphDefinition.append("\"graph TD\\n");
        
        Queue<Node> nodes = new LinkedList<Node>();
        if(tree != null) {
            nodes.add(tree);
        } 
        while(!nodes.isEmpty()) {
            Node n = nodes.remove();
            if (n.left != null) {
                graphDefinition.append("    " + n.hashCode() + "((" + n.value + ")) --- " + n.left.hashCode() + "((" + n.left.value + "))\\n");
                nodes.add(n.left);
            }
            if (n.right != null) {
                graphDefinition.append("    " + n.hashCode() + "((" + n.value + ")) --- " + n.right.hashCode() + "((" + n.right.value + "))\\n");
                nodes.add(n.right);
            }
        }
        graphDefinition.append("\"");

        html.append("<script type=\"module\">\n"
                + "import mermaid from 'https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs';\n");
                
        html.append(getElementById(id)).append(".innerHTML = ").append(graphDefinition).append(";\n");
       
        html.append("mermaid.init(undefined, ").append(getElementById(id)).append(");\n");

        html.append("</script>\n");
        return html.toString();
    }

    public static void displayTree(Node tree) {
        idDIV = display(treeToMermaid(tree), "text/html");
    }

    public static void displayTree2(Node tree) {
        id = UUID.randomUUID().toString().replace("-", "");
        // TODO: Templating Engine

        StringBuilder html = new StringBuilder();
        // html.append("<pre style=\"width=400px;height=400px\" id=\"").append(id).append("\" class=\"mermaid\"></pre>");
        html.append("<div style=\"width=400px;height=400px\" id=\"").append(id).append("\" class=\"mermaid\"></div>");
        // html.append("<div style=\"width=400px;height=400px\" id=\"").append(id).append("\"></div>");
        // html.append("<script>");
        
        String insertSvg = "function (svgCode, bindFunctions) { " + getElementById(id) + ".innerHTML = svgCode; }";
        
        StringBuilder graphDefinition = new StringBuilder();
        graphDefinition.append("\"graph TD\\n");
        
        Queue<Node> nodes = new LinkedList<Node>();
        if(tree != null) {
            nodes.add(tree);
        } 
        while(!nodes.isEmpty()) {
            Node n = nodes.remove();
            if (n.left != null) {
                graphDefinition.append("    " + n.hashCode() + "((" + n.value + ")) --- " + n.left.hashCode() + "((" + n.left.value + "))\\n");
                nodes.add(n.left);
            }
            if (n.right != null) {
                graphDefinition.append("    " + n.hashCode() + "((" + n.value + ")) --- " + n.right.hashCode() + "((" + n.right.value + "))\\n");
                nodes.add(n.right);
            }
        }
        graphDefinition.append("\"");

        String pars[] = new String[] {
            "'" + id + "'",     // id of elem
            graphDefinition.toString(),
            insertSvg            
        };
        // // html.append(requireCallJS("https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs  ", "mermaid", "mermaidAPI.render", pars));

        // html.append("mermaid = mermaidM.default;\n");
        html.append("<script type=\"module\">\n"
                + "import mermaid from 'https://unpkg.com/mermaid@9/dist/mermaid.esm.min.mjs';\n");
                
        html.append("mermaid.mermaidAPI.render(").append(String.join(",", pars)).append(");\n");

        // html.append(getElementById(id)).append(".innerHTML = ").append(graphDefinition).append(";\n");
        
        html.append("\nalert('T5');</script>\n");
        idDIV = display(html.toString(), "text/html");

        // enableMermaid();
        
    }



    public static String getElementById(String id) {
        return "document.getElementById('" + id + "')";
    }

    public static String var(String var, String id) {
        return var + id;
    }

    public static String requireCallJS(String url, String module, String call, String pars[]) {
        return "require(['" + url + "'], (" + module + ") => {\n"
            + module + "." + call + "(" + String.join(",", pars) + ");\n});";
    }

    
}