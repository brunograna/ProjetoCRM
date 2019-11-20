package br.senac.rj.crm.repository.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ClienteOfertaFechamentoDto {

    private Double valor;

    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFechamento;

    public ClienteOfertaFechamentoDto(Double valor, LocalDate dataFechamento) {
        this.valor = valor;
        this.dataFechamento = dataFechamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
}
