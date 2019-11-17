package br.senac.rj.crm.domain.dto;

import java.util.ArrayList;

public class ChartJsDatasetDto {

    private ArrayList<Double> data = new ArrayList<>();
    private ArrayList<String> backgroundColor = new ArrayList<>();
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<Double> getData() {
        return data;
    }

    public void addData(Double data){
        this.data.add(data);
    }
    public void removeData(Double data){
        this.data.remove(data);
    }

    public void setData(ArrayList<Double> data) {
        this.data = data;
    }

    public void addColor(String color){
        this.backgroundColor.add(color);
    }
    public void removeColor(String color){
        this.backgroundColor.remove(color);
    }

    public ArrayList<String> getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(ArrayList<String> backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
