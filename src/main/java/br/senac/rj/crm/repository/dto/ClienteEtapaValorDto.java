package br.senac.rj.crm.repository.dto;

import br.senac.rj.crm.domain.FunilEtapa;

public class ClienteEtapaValorDto {

    private FunilEtapa funilEtapa;
    private Double valor;

    public ClienteEtapaValorDto(FunilEtapa funilEtapa, Double valor) {
        this.funilEtapa = funilEtapa;
        this.valor = valor;
    }

    public FunilEtapa getFunilEtapa() {
        return funilEtapa;
    }

    public void setFunilEtapa(FunilEtapa funilEtapa) {
        this.funilEtapa = funilEtapa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
