package hftstuttgart.dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;



import tech.tablesaw.plotly.api.LinePlot;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.TraceBuilder;

import java.awt.image.BufferedImage;
import static io.github.spencerpark.ijava.runtime.Display.*;

public class ExperimentCopy {

//     private static List<String> series;
//     private static List<Double> sizes;
//     private static Map<Double,Map<String,Measurement>> measurements;

//     private static Map<String, Series> series;


//     private static String curSeries = null;
//     private static Measurement curMeasurement = null;
//     private static double curSize = 0;

//     private static String id;
//     private static String idChart;
//     private static String idTable;
//     private static String idJS;
//     private static boolean experimentRunning = false;

//     private static boolean recordEOPs = false;
//     private static boolean recordTime = true;

//     private Experiment() {
            
//     }

//     public static void done() {
//         if(!experimentRunning)
//             return;
//         curMeasurement.finish();

//         Map<String, Measurement> ms = measurements.get(curMeasurement.size);
//         if(ms == null) {
//             ms = new HashMap<>();
//             measurements.put(curMeasurement.size, ms);
//         }
//         ms.put(curMeasurement.series, curMeasurement);
//         curSeries = null;
//         curMeasurement = null;

//         StringBuilder html = new StringBuilder();
//         html.append("<table style=\"text-algin: right; border: 1px\">");
//         html.append("<tr><th></th>");
//         if(recordTime) {
//             html.append("<th colspan=\"").append(Experiment.series.size()).append("\">").append("ms").append("</th>");
//         }
//         if(recordEOPs) {
//             html.append("<th colspan=\"").append(Experiment.series.size()).append("\">").append("EOPs").append("</th>");
//         }
//         html.append("</tr>");
//         html.append("<tr><th>Size</th>");

//         if(recordTime) {
//         for(String series : Experiment.series) {    
//             html.append("<th>").append(series).append("</th>");
//         }    
//     }
//     if(recordEOPs) {
//         for(String series : Experiment.series) {    
//             html.append("<th>").append(series).append("</th>");
//         }    
//     }
//     html.append("</tr>\n");

//         Collections.sort(sizes);

//         for(double size : sizes) {
//             Map<String,Measurement> mForSize = measurements.get(size);
//             if(mForSize == null)
//                 continue;

//             html.append("<tr><td>").append(size).append("</td>");
//             if(recordTime) {
//                 for(String series : Experiment.series) {    
//                     Measurement m = mForSize.get(series);
//                     html.append("<td>");
//                     if(m == null) {
//                         html.append("-");
//                     } else {
//                         html.append(String.format("%,d", m.millis));
//                     }
//                     html.append("</td>");
//             }
//             if(recordEOPs) {
//                 for(String series : Experiment.series) {    
//                     Measurement m = mForSize.get(series);
//                     html.append("<td>");
//                     if(m == null) {
//                         html.append("-");
//                     } else {
//                         html.append(String.format("%,d", m.eops));
//                     }
//                     html.append("</td>");
//             }
//             html.append("</tr>");
//         }
//         html.append("</table>");
//         updateDisplay(idTable, html.toString(), "text/html");

//         StringBuilder js = new StringBuilder();
//         js.append("var target_").append(id).append(" = document.getElementById('").append(id).append("');\n");



//         html.append("var chart_").append(id).append(" = Plotly.newPlot(target_").append(id).append(", data_").append(id).append(", layout);");
//         html.append("</script>\n");
        
//         idJS = display(html.toString(), "text/html");


//         new TraceBuilder().
// LinePlot.create("", "", new double[] { 1.0 }, "", new double[] { 3.0 });

//         // double xData[] = new double[measurements.size()];
//         // double yData[] = new double[xData.length];
//         // int k = 0;
//         // for(Measurement measurement : measurements) {
//         //     xData[k] = measurement.size;
//         //     yData[k] = measurement.eops;
//         //     k++;
//         // }
//         // XYChart chart = QuickChart.getChart("EOPs", "n", "EOPs", " ", xData, yData);
//         // chart.getStyler().setYAxisMin(0.0);
//         // updateDisplay(idChart, BitmapEncoder.getBufferedImage(chart));
//     }

//     public static void start(boolean recordEOPs, boolean recordTime) {
//         Experiment.recordEOPs = recordEOPs;
//         Experiment.recordTime = recordTime;
//         Experiment.experimentRunning = true;
//         Experiment.curMeasurement = null;

//         measurements = new HashMap<>();

//         id = UUID.randomUUID().toString().replace("-", "");
//         // TODO: Templating Engine or plotly-java

//         StringBuilder html = new StringBuilder();
//         html.append("<div style=\"width=400px;height=400px\" id=\"").append(id).append("\"></div>\n");
//         idChart = display(html.toString(), "text/html");
//         idTable = display("<b>Waiting for Measurements</b>","text/html");
        
//         StringBuilder js = new StringBuilder();
//         js.append("<script>var Plotly = require('https://cdn.plot.ly/plotly-1.44.4.min.js');\n");
//         js.append("var target_").append(id).append(" = document.getElementById('").append(id).append("');\n");
//         js.append("var layout_").append(id).append(" = {yaxis: {title: 'EOPs'}, yaxis2: {title: 'ms', side: 'right'}};\n");
//         js.append("var data_").append(id).append(" = [];\n"); 
//         js.append("Plotly.newFigure(target_").append(id).append(", data_").append(id).append(", layouyt_").append(id).append(");\n");
//         js.append("</script>");
//         idJS = display(js.toString(),"text/html");

//     }

//     public static void stop() {
//         experimentRunning = false;
//     }

//     public static void measure(String series, double size) {
//         if(!experimentRunning)
//             return;
//         if(curMeasurement != null) {
//             done();
//         }
//         if(!Experiment.series.contains(series))
//             Experiment.series.add(series);
//         if(!Experiment.sizes.contains(size))
//             Experiment.sizes.add(size);
//         curMeasurement = new Measurement(series, size);
//         curSeries = series;
//         curSize = size;
//         curMeasurement.init();
//     }

//     public static void EOP(String ... args) {
//         if(!experimentRunning)
//             return;

//         if(curMeasurement != null) {
//             curMeasurement.EOP(args);
//         }
//     }
}
