package hftstuttgart;

import static io.github.spencerpark.ijava.runtime.Display.display;
import java.util.function.Supplier;
import io.github.spencerpark.ijava.IJava;
import java.util.Scanner;


public class JupyterTools {
    public static void register() {

    }

    private static Scanner sc = null;
    private static Scanner getScanner() {
        if(sc == null) {
            sc = new Scanner(System.in);
        }
        return sc;
    }

    public static String readString() {
        return getScanner().nextLine();
    } 

    public static int readInt() {
        return getScanner().nextInt();
    }

    public static double readDouble() {
        return getScanner().nextDouble();
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
    }
}
