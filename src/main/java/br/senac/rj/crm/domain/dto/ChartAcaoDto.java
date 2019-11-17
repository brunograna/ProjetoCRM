package br.senac.rj.crm.domain.dto;

import java.util.ArrayList;

public class ChartAcaoDto {
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<ChartAcaoDatasetDto> datasets = new ArrayList<>();

    public ChartAcaoDto() {
        datasets.add(new ChartAcaoDatasetDto());
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


    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public ArrayList<ChartAcaoDatasetDto> getDatasets() {
        return datasets;
    }

    public void setDatasets(ArrayList<ChartAcaoDatasetDto> datasets) {
        this.datasets = datasets;
    }
}
