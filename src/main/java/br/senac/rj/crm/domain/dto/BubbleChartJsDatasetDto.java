package br.senac.rj.crm.domain.dto;

import java.util.ArrayList;

public class BubbleChartJsDatasetDto {
    private ArrayList<BubbleChartJsThreeAxisDto> data = new ArrayList<>();
    private String backgroundColor;
    private String borderColor;
    private Integer borderWidth;
    private ArrayList<String> label = new ArrayList<>();

    public ArrayList<String> getLabel() {
        return label;
    }

    public void setLabel(ArrayList<String> label) {
        this.label = label;
    }

    public ArrayList<BubbleChartJsThreeAxisDto> getData() {
        return data;
    }

    public void addData(BubbleChartJsThreeAxisDto data){
        this.data.add(data);
    }
    public void removeData(BubbleChartJsThreeAxisDto data){
        this.data.remove(data);
    }

    public void setData(ArrayList<BubbleChartJsThreeAxisDto> data) {
        this.data = data;
    }



    public void addLabel(String label){
        this.label.add(label);
    }
    public void removeLabel(String label){
        this.label.remove(label);
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }
}
