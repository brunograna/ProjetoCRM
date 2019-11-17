package br.senac.rj.crm.domain.dto;

public class AcaoGraficoDto {

    private String label;
    private Double value;

    public AcaoGraficoDto(String label, Double value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
