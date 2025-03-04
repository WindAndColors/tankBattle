package objects;

public class Projection { //投影
    private double min;
    private double max;

    public Projection(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    //判断投影是否重叠
    public boolean overlap(Projection other) {
        return this.max >= other.min && other.max >= this.min;
    }

    //返回重叠的量(应先判断是否重叠)
    public double getOverlap(Projection other) {
        double overlapStart = Math.max(this.min, other.min);
        double overlapEnd = Math.min(this.max, other.max);
        return overlapEnd - overlapStart;
    }
}
