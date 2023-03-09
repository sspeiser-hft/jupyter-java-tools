package hftstuttgart.dsa;

import static io.github.spencerpark.ijava.runtime.Display.display;
import static io.github.spencerpark.ijava.runtime.Display.updateDisplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class Experiment {

    // Must be ordered
    private static TreeMap<String, Series> series;

    private static String curSeries = null;
    private static long curMillis = 0;
    private static long curEOPs = 0;
    private static double curSize = 0.0;

    private static String id;
    private static String idChart;
    private static String idTable;
    private static String idJS;
    private static boolean experimentRunning = false;

    private static boolean recordEOPs = false;
    private static boolean recordTime = true;

    private Experiment() {

    }

    public static void done() {
        if(!experimentRunning)
            return;

        curMillis = System.currentTimeMillis() - curMillis;
        Series s = series.get(curSeries);
        if(s == null) {
            s = new Series(curSeries);
            series.put(curSeries, s);
        }
        s.add(curSize, curEOPs, curMillis);
        
        curSeries = null;
        curMillis = 0;
        curEOPs = 0;
        curSize = 0.0;
        

        StringBuilder html = new StringBuilder();
        html.append("<div class=\"row\">");
        html.append("<div id=\"").append(id).append("\"></div>\n");

        html.append("<table style=\"text-algin: right; border: 1px\">");
        html.append("<tr><th></th>");
        if(recordTime) {
            html.append("<th colspan=\"").append(Experiment.series.size()).append("\">").append("ms").append("</th>");
        }
        if(recordEOPs) {
            html.append("<th colspan=\"").append(Experiment.series.size()).append("\">").append("EOPs").append("</th>");
        }
        html.append("</tr>");
        html.append("<tr><th>Size</th>");

        if(recordTime) {
        for(String series : Experiment.series.keySet()) {    
            html.append("<th>").append(series).append("</th>");
    
            }
            }  
    
    if(recordEOPs) {
        for(String series : Experiment.series.keySet()) {    
            html.append("<th>").append(series).append("</th>");
        }    
    }
    html.append("</tr>\n");

        List<Integer> sizes = new ArrayList<Integer>();
        for(Series s1 : series.values()) {
            for(double size : s1.getSizes()) {
                if(!sizes.contains((int) size * 10)) {
                    sizes.add((int) size * 10);
                }
            }
        }
        Collections.sort(sizes);

        for(int size : sizes) {
            html.append("<tr><td>").append(String.format("%,.1f", (double) size / 10.0)).append("</td>");
            if(recordTime) {
                for(Series series : Experiment.series.values()) {    
                    long time = series.findTimeBySize10(size);
                    html.append("<td>");
                    if(time < 0) {
                        html.append("-");
                    } else {
                        html.append(String.format("%,d", time));
                    }
                    html.append("</td>");
                }
            }
            if(recordEOPs) {
                for(Series series : Experiment.series.values()) {    
                    long eops = series.findEOPsBySize10(size);
                    html.append("<td>");
                    if(eops < 0) {
                        html.append("-");
                    } else {
                        html.append(String.format("%,d", eops));
                    }
                    html.append("</td>");
                }
            }
            html.append("</tr>");
        }
        html.append("</table>");
        html.append("</div>");
        updateDisplay(idTable, html.toString(), "text/html");

        StringBuilder js = new StringBuilder();
        
        js.append("<script>");
        js.append("var target_").append(id).append(" = document.getElementById('").append(id).append("');\n");
        
        js.append("var data_").append(id).append(" = [");
        for(Series series : Experiment.series.values()) {
            if(recordTime) {
                js.append("{x: ").append(series.getSizes().toString()).append(", y: ").append(series.getMillis()).append(", name: 'time ").append(series.getName()).append("', yaxis: 'y2', type: 'scatter'},");
            }
            if(recordEOPs) {
                js.append("{x: ").append(series.getSizes().toString()).append(", y: ").append(series.getEOPs()).append(", name: 'EOPs ").append(series.getName()).append("', type: 'scatter'},");
            }
        }
        js.replace(js.length() - 1, js.length(), "]");
        js.append(";\n");
        
        js.append("require(['https://cdn.plot.ly/plotly-1.44.4.min.js'], (Plotly) => {\n");
        js.append("Plotly.react(target_").append(id).append(", data_").append(id).append(", layout_").append(id)
        .append(");\n");

        js.append("});");
        js.append("</script>");
        
        updateDisplay(idJS, js.toString(), "text/html");


        // double xData[] = new double[measurements.size()];
        // double yData[] = new double[xData.length];
        // int k = 0;
        // for(Measurement measurement : measurements) {
        //     xData[k] = measurement.size;
        //     yData[k] = measurement.eops;
        //     k++;
        // }
        // XYChart chart = QuickChart.getChart("EOPs", "n", "EOPs", " ", xData, yData);
        // chart.getStyler().setYAxisMin(0.0);
        // updateDisplay(idChart, BitmapEncoder.getBufferedImage(chart));
    }

    public static void start(boolean recordEOPs, boolean recordTime) {
        Experiment.recordEOPs = recordEOPs;
        Experiment.recordTime = recordTime;
        Experiment.experimentRunning = true;

        curSeries = null;
        curMillis = 0;
        curEOPs = 0;
        curSize = 0.0;

        series = new TreeMap<>();

        id = UUID.randomUUID().toString().replace("-", "");
        // TODO: Templating Engine or plotly-java

        StringBuilder html = new StringBuilder();
        html.append("<div class=\"row\">");
        html.append("<div id=\"").append(id).append("\"></div>\n");
        // idChart = display(html.toString(), "text/html");
        html.append("<div>Waiting for Measurements</div>");
        html.append("</div>");
        idTable = display(html.toString(), "text/html");
//        idTable = display("<b>Waiting for Measurements</b>", "text/html");

        StringBuilder js = new StringBuilder();
        js.append("<script>");
        
        //js.append("<script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>");
        // js.append("<script>");
        // js.append("var Plotly = require('https://cdn.plot.ly/plotly-1.44.4.min.js');\n");
        js.append("var target_").append(id).append(" = document.getElementById('").append(id).append("');\n");
        
        js.append("var layout_").append(id)
        .append(" = {yaxis: {title: 'EOPs'}, yaxis2: {title: 'ms', side: 'right',  overlaying: 'y'}};\n");
        js.append("var data_").append(id).append(" = [];\n");
        
        js.append("require(['https://cdn.plot.ly/plotly-1.44.4.min.js'], (Plotly) => {\n");
        js.append("Plotly.newPlot(target_").append(id).append(", data_").append(id).append(", layout_").append(id)
                .append(");\n");
        
        js.append("});");
        js.append("</script>");

        idJS = display(js.toString(), "text/html");

    }

    public static void stop() {
        experimentRunning = false;
    }

    public static void measure(String series, double size) {
        if (!experimentRunning)
            return;
        if (curSeries != null) {
            done();
        }

        curSeries = series;
        curSize = size;
        curEOPs = 0;
        curMillis = System.currentTimeMillis();
    }

    public static void EOP(String... args) {
        if (!experimentRunning)
            return;

        if (curSeries != null) {
            curEOPs += args.length;
        }
    }
}
