package br.senac.rj.crm.domain.dto;

import java.util.ArrayList;

public class ChartJsDto {
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<ChartJsDatasetDto> datasets = new ArrayList<>();

    public ChartJsDto() {
        datasets.add(new ChartJsDatasetDto());
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void addLabel(String label){
        this.labels.add(label);
    }

    public void removeLabel(String label){
        this.labels.remove(label);
    }

    public void addData(Double data){
        this.datasets.get(0).addData(data);
    }

    public void addColor(String color){
        this.datasets.get(0).addColor(color);
    }

    public void addBarLabel(String label){
        this.datasets.get(0).setLabel(label);
    }


    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public ArrayList<ChartJsDatasetDto> getDatasets() {
        return datasets;
    }

    public void setDatasets(ArrayList<ChartJsDatasetDto> datasets) {
        this.datasets = datasets;
    }
}
