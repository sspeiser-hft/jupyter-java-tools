package hftstuttgart.dsa;

public class Measurement {
    int eops = 0;
    long millis = 0;
    double size;
    String series;
    
    public Measurement(String series, double size) {
        this.series = series;
        this.size = size;
    }
    
    public void init() {
        millis = System.currentTimeMillis();
    }
    
    public void finish() {
        millis = System.currentTimeMillis() - millis;
    }
    
    public void EOP(String ...args) {
        eops += args.length;
    }
    
    public String toHTML() {
        return "<tr><td>" + size + "</td><td>" + eops +"</td><td>" + millis + "</td></tr>";
    }
}