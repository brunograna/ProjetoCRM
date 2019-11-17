package br.senac.rj.crm.repository.dto;

import br.senac.rj.crm.domain.FunilEtapa;
import br.senac.rj.crm.domain.Oferta;

public class OfertaEtapaDto {

    private Oferta oferta;
    private FunilEtapa etapa;
    private Long quantidade;

    public OfertaEtapaDto(Oferta oferta, FunilEtapa etapa, Long quantidade) {
        this.oferta = oferta;
        this.etapa = etapa;
        this.quantidade = quantidade;
    }


    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public FunilEtapa getEtapa() {
        return etapa;
    }

    public void setEtapa(FunilEtapa etapa) {
        this.etapa = etapa;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
