package br.senac.rj.crm.domain.dto;

public class BubbleChartJsThreeAxisDto {
    private double x;
    private double y;
    private int r;

    public BubbleChartJsThreeAxisDto(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
