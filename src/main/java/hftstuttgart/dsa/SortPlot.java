package hftstuttgart.dsa;

import static io.github.spencerpark.ijava.runtime.Display.display;
import static io.github.spencerpark.ijava.runtime.Display.updateDisplay;

import java.util.UUID;

public class SortPlot {

    private static String id = null;

    public static String arrayToJSArray(int[] array) {
        StringBuilder js = new StringBuilder();
        js.append("[");
        for (int i = 0; i < array.length; i++) {
            js.append(array[i]).append(",");
        }
        js.replace(js.length() - 1, js.length(), "]");
        return js.toString();
    }

    public static String arrayToJSArray(double[] array) {
        StringBuilder js = new StringBuilder();
        js.append("[");
        for (int i = 0; i < array.length; i++) {
            js.append(array[i]).append(",");
        }
        js.replace(js.length() - 1, js.length(), "]");
        return js.toString();
    }

    public static String sequenceToJSArray(int length) {
        StringBuilder js = new StringBuilder();
        js.append("[");
        for (int i = 0; i < length; i++) {
            js.append(i).append(",");
        }
        js.replace(js.length() - 1, js.length(), "]");
        return js.toString();
    }

    private static String arrayToPlotlyScatter(int[] array) {
        StringBuilder js = new StringBuilder();
        js.append("{ x: ").append(sequenceToJSArray(array.length));
        js.append(", y: ").append(arrayToJSArray(array));
        js.append(", mode: 'markers'");
        js.append(", type: 'scatter'");
        js.append(", marker: {size: 4}");
        js.append("}");
        return js.toString();
    }

    private static String idDIV = null;
    private static String idJS = null;

    public static void createSortPlot(int[] array) {
        id = UUID.randomUUID().toString().replace("-", "");

        // TODO: Templating Engine

        StringBuilder html = new StringBuilder();
        html.append("<div style=\"width=400px;height=400px\" id=\"").append(id).append("\"></div>\n");
        idDIV = display(html.toString(), "text/html");

        html = new StringBuilder();
        html.append("<script>\n");
        html.append("var target_").append(id).append(" = document.getElementById('").append(id).append("');\n");


        html.append("var data_").append(id).append(" = [").append(arrayToPlotlyScatter(array)).append("];\n");

        int max = array.length;
        for(int v : array) {
            if(v > max) max = v;
        }

        int min = Integer.MAX_VALUE;
        for(int v : array) {
            if(v < min) {
                min = v;
            }
        }
        html.append("var layout_").append(id).append(" = {xaxis: {range: [-1, ").append(array.length + 1).append("], showgrid: false, zeroline: false, visible: false},yaxis: {range: [").append(min-1).append(", ").append(max+2).append("], showgrid: false, zeroline: false, visible: false}};");
            
          

        html.append("require(['https://cdn.plot.ly/plotly-1.44.4.min.js'], (Plotly) => {\n");
        html.append("Plotly.newPlot(target_").append(id).append(", data_").append(id).append(", layout_").append(id).append(");");
        html.append("});");
        
        html.append("</script>\n");
        
        idJS = display(html.toString(), "text/html");
    }

    public static void updateSortPlot(int[] array) {
        if(id == null) {
            return;
        }
        StringBuilder html = new StringBuilder();
        html.append("<script>\n");
        html.append("var target_").append(id).append(" = document.getElementById('").append(id).append("');\n");
        html.append("data_").append(id).append("[0].y = ").append(arrayToJSArray(array)).append(";\n");
        html.append("require(['https://cdn.plot.ly/plotly-1.44.4.min.js'], (Plotly) => {\n");
        html.append("Plotly.react(target_").append(id).append(", data_").append(id).append(", layout_").append(id)
        .append(");\n");
        html.append("});");
        html.append("</script>\n");
        
        updateDisplay(idJS, html.toString(), "text/html");
        try { Thread.sleep(40); } catch(Exception e) { }
    }

    public static void finishSortPlot() {
        id = null;
    }


    public static String createSortPlotHM(int[] array) {

        // var xValues = ['A', 'B', 'C', 'D', 'E'];

        // var yValues = ['W', 'X', 'Y', 'Z'];

        // var zValues = [
        // [0.00, 0.00, 0.75, 0.75, 0.00],
        // [0.00, 0.00, 0.75, 0.75, 0.00],
        // [0.75, 0.75, 0.75, 0.75, 0.75],
        // [0.00, 0.00, 0.00, 0.75, 0.00]
        // ];

        // var colorscaleValue = [
        // [0, '#00000'],
        // [0.5, '#00FF00'],
        // [1, '#FFFFFF']
        // ];

        // var data = [{
        //     x: xValues,
        //     y: yValues,
        //     z: zValues,
        //     type: 'heatmap',
        //     colorscale: colorscaleValue,
        //     showscale: false
        //     }];

        //    var layout = {
        //     title: 'Annotated Heatmap',
        //     annotations: [],
        //     xaxis: {
        //         ticks: '',
        //         side: 'top'
        //     },
        //     yaxis: {
        //         ticks: '',
        //         ticksuffix: ' ',
        //         width: 700,
        //         height: 700,
        //         autosize: false
        //     }
        // };
                
        return null;
    }

}