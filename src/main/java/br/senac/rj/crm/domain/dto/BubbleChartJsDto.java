package br.senac.rj.crm.domain.dto;

import java.util.ArrayList;

public class BubbleChartJsDto {
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<BubbleChartJsDatasetDto> datasets = new ArrayList<>();

    public BubbleChartJsDto() {

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

    public void addData(BubbleChartJsDatasetDto data){
        this.datasets.add(data);
    }


    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public ArrayList<BubbleChartJsDatasetDto> getDatasets() {
        return datasets;
    }

    public void setDatasets(ArrayList<BubbleChartJsDatasetDto> datasets) {
        this.datasets = datasets;
    }
}
