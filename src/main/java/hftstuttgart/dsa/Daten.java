package hftstuttgart.dsa;

import static io.github.spencerpark.ijava.runtime.Display.display;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

import hftstuttgart.dsa.graph.Graph;
import hftstuttgart.dsa.graph.GraphPlot;
import hftstuttgart.dsa.tree.Node;
import hftstuttgart.dsa.tree.TreePlot;
import io.github.spencerpark.ijava.IJava;


public class Daten {
    public static void register() {

    }

    private static void renderArrayRow(StringBuilder html, Object[] row) {
        html.append("  <tr>\n");
        for (int j = 0; j < row.length; j++) {
            html.append("    <td>" + row[j] + "<td>\n");
        }
        html.append("  </tr>\n");
    }

    private static void renderArrayRow(StringBuilder html, int[] row) {
        html.append("  <tr>\n");
        for (int j = 0; j < row.length; j++) {
            html.append("    <td>" + String.format("%,d", row[j]) + "<td>\n");
        }
        html.append("  </tr>\n");
    }

    private static void renderArrayRow(StringBuilder html, double[] row) {
        html.append("  <tr>\n");
        for (int j = 0; j < row.length; j++) {
            html.append("    <td>" + String.format("%,.2f", row[j]) + "<td>\n");
        }
        html.append("  </tr>\n");
    }


    static {
        IJava.getKernelInstance().getRenderer()
                .createRegistration(int[][].class)
                .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML)
                .register((array, ctx) -> {
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, () -> {
                        StringBuilder html = new StringBuilder();
                        html.append("<table style=\"text-align: right\">\n");

                        if (array.length <= 15) {
                            for (int i = 0; i < array.length; i++) {
                                renderArrayRow(html, array[i]);
                            }
                        } else {
                            for (int i = 0; i < 7; i++) {
                                renderArrayRow(html, array[i]);
                            }
                            html.append("<tr><td>...</td></tr>\n");
                            for (int i = array.length - 7; i < array.length; i++) {
                                renderArrayRow(html, array[i]);
                            }
                        }
                        html.append("</table>");
                        return html.toString();
                    });
                });
        IJava.getKernelInstance().getRenderer()
                .createRegistration(String[][].class)
                .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML)
                .register((array, ctx) -> {
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, () -> {
                        StringBuilder html = new StringBuilder();
                        html.append("<table style=\"text-align: right\">\n");

                        if (array.length <= 15) {
                            for (int i = 0; i < array.length; i++) {
                                renderArrayRow(html, array[i]);
                            }
                        } else {
                            for (int i = 0; i < 7; i++) {
                                renderArrayRow(html, array[i]);
                            }
                            html.append("<tr><td>...</td></tr>\n");
                            for (int i = array.length - 7; i < array.length; i++) {
                                renderArrayRow(html, array[i]);
                            }
                        }
                        html.append("</table>");
                        return html.toString();
                    });
                });
        IJava.getKernelInstance().getRenderer()
                .createRegistration(int[].class)
                .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_PLAIN)
                .supporting(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.ANY)
                .register((array, ctx) -> {
                    Supplier<Object> f = () -> {
                        StringBuilder text = new StringBuilder();
                        text.append("[");

                        if (array.length <= 15) {
                            for (int i = 0; i < array.length; i++) {
                                text.append(String.format("%,d", array[i])).append(", ");
                            }
                        } else {
                            for (int i = 0; i < 7; i++) {
                                text.append(String.format("%,d", array[i])).append(", ");
                            }
                            text.append("..., ");
                            for (int i = array.length - 7; i < array.length; i++) {
                                text.append(String.format("%,d", array[i])).append(", ");
                            }
                        }
                        text.replace(text.length() - 2, text.length(), "]");
                        return text.toString();
                    };
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, f);
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_PLAIN, f);
                });
                IJava.getKernelInstance().getRenderer()
                .createRegistration(String[].class)
                .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_PLAIN)
                .supporting(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.ANY)
                .register((array, ctx) -> {
                    Supplier<Object> f = () -> {
                        StringBuilder text = new StringBuilder();
                        text.append("[");

                        if (array.length <= 15) {
                            for (int i = 0; i < array.length; i++) {
                                text.append(array[i]).append(", ");
                            }
                        } else {
                            for (int i = 0; i < 7; i++) {
                                text.append(array[i]).append(", ");
                            }
                            text.append("..., ");
                            for (int i = array.length - 7; i < array.length; i++) {
                                text.append(array[i]).append(", ");
                            }
                        }
                        text.replace(text.length() - 2, text.length(), "]");
                        return text.toString();
                    };
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, f);
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_PLAIN, f);
                });

                IJava.getKernelInstance().getRenderer()
                .createRegistration(double[].class)
                .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_PLAIN)
                .supporting(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.ANY)
                .register((array, ctx) -> {
                    Supplier<Object> f = () -> {
                        StringBuilder text = new StringBuilder();
                        text.append("[");

                        if (array.length <= 15) {
                            for (int i = 0; i < array.length; i++) {
                                text.append(String.format("%,.2f", array[i])).append(", ");
                            }
                        } else {
                            for (int i = 0; i < 7; i++) {
                                text.append(String.format("%,.2f", array[i])).append(", ");
                            }
                            text.append("..., ");
                            for (int i = array.length - 7; i < array.length; i++) {
                                text.append(String.format("%,.2f", array[i])).append(", ");
                            }
                        }
                        text.replace(text.length() - 2, text.length(), "]");
                        return text.toString();
                    };
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, f);
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_PLAIN, f);
                });

                IJava.getKernelInstance().getRenderer()
                .createRegistration(double[][].class)
                .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML)
                .register((array, ctx) -> {
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, () -> {
                        StringBuilder html = new StringBuilder();
                        html.append("<table style=\"text-align: right\">\n");

                        if (array.length <= 15) {
                            for (int i = 0; i < array.length; i++) {
                                renderArrayRow(html, array[i]);
                            }
                        } else {
                            for (int i = 0; i < 7; i++) {
                                renderArrayRow(html, array[i]);
                            }
                            html.append("<tr><td>...</td></tr>\n");
                            for (int i = array.length - 7; i < array.length; i++) {
                                renderArrayRow(html, array[i]);
                            }
                        }
                        html.append("</table>");
                        return html.toString();
                    });
                });

                IJava.getKernelInstance().getRenderer()
                .createRegistration(Node.class)
                .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML)
                .register((tree, ctx) -> {
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, () -> {
                        return TreePlot.treeToMermaid(tree);
                    });
                });

                IJava.getKernelInstance().getRenderer()
                .createRegistration(Graph.class)
                .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML)
                .register((graph, ctx) -> {
                    ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, () -> {
                        return GraphPlot.graphToMermaid(graph);
                    });
                });
                

        // IJava.getKernelInstance().getRenderer()
        //         .createRegistration(XYChart.class)
        //         .preferring(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_PLAIN)
        //         .supporting(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.ANY)
        //         .register((chart, ctx) -> {
        //             Supplier<Object> f = () -> {
        //                 return BitmapEncoder.getBufferedImage(chart);
        //             };
        //             ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_HTML, f);
        //             ctx.renderIfRequested(io.github.spencerpark.jupyter.kernel.display.mime.MIMEType.TEXT_PLAIN, f);
        //         });

    }

    



    public static int[][] getStudents() {
        Random r = new Random(42);
        int[][] students = new int[280][2];
        int i = 0;
        for (int semester = 1; semester <= 7; semester++) {
            for (int student = 0; student < 64 - (semester - 1) * 8; student++) {
                students[i][1] = semester;
                students[i][0] = 16 + semester + r.nextInt(7);
                i++;
            }
        }
        return students;
    }

    // public static BufferedImage displayChart(XYChart chart) {
    //     return BitmapEncoder.getBufferedImage(chart);
    // }

    // public static BufferedImage quickChart(String chartTitle, String xTitle,
    // String yTitle, String seriesName,
    // double[] xData, double[] yData) {
    // return displayChart(QuickChart.getChart(chartTitle, xTitle, yTitle,
    // seriesName, xData, yData));
    // }

    public static void quickChart(String chartTitle, String xTitle, String yTitle, String seriesName,
            double[] xData, double[] yData) {
        String id = UUID.randomUUID().toString().replace("-", "");
        StringBuilder html = new StringBuilder();
        html.append("<div id=\"").append(id).append("\"></div>\n");
        html.append("<script>");

        html.append("var target_").append(id).append(" = document.getElementById('").append(id).append("');\n");

        html.append("var layout_").append(id)
        .append(" = {yaxis: {title: '").append(yTitle).append("'} };\n");
        

        html.append("var data_").append(id).append(" = [\n");

        html.append("{x: ").append(Arrays.toString(xData)).append(", y: ").append(Arrays.toString(yData)).append(", name: '").append(seriesName).append("', type: 'scatter'}");
        html.append("];\n");

        html.append("require(['https://cdn.plot.ly/plotly-1.44.4.min.js'], (Plotly) => {\n");
        html.append("Plotly.newPlot(target_").append(id).append(", data_").append(id).append(", layout_").append(id)
                .append(");\n");

        html.append("});");
        html.append("</script>");

        

        display(html.toString(), "text/html");
    }

    public static int[] ascendingArray(int n) {
        int[] s = new int[n];
        for (int i = 0; i < s.length; i++)
            s[i] = i;
        return s;
    }

    public static int[] randomArray(int n) {
        int[] s = ascendingArray(n);
        Random r = new Random();
        for (int i = 0; i < s.length; i++) {
            int k = r.nextInt(s.length);
            int t = s[i];
            s[i] = s[k];
            s[k] = t;
        }
        return s;
    }

    public static int[] descendingArray(int n) {
        int[] s = new int[n];
        for (int i = 0; i < s.length; i++) {
            s[i] = n - i - 1;
        }
        return s;
    }
}
