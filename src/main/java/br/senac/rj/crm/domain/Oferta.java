package br.senac.rj.crm.domain;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

//@Where(clause="oferta_status=1")
@Entity
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ofertaId;

    private String ofertaDescricao;
    private LocalDate ofertaDataInicio;
    private LocalDate ofertaDataFim;
    private double ofertaPreco;

    @Column(name="oferta_status")
    private boolean ofertaStatus;

    @OneToOne
    @JoinColumn( name = "produto_id" )
    private Produto ofertaProduto;

    public Integer getOfertaId() {
        return ofertaId;
    }

    public void setOfertaId(Integer ofertaId) {
        this.ofertaId = ofertaId;
    }

    public String getOfertaDescricao() {
        return ofertaDescricao;
    }

    public void setOfertaDescricao(String ofertaDescricao) {
        this.ofertaDescricao = ofertaDescricao;
    }

    public LocalDate getOfertaDataInicio() {
        return ofertaDataInicio;
    }

    public void setOfertaDataInicio(LocalDate ofertaDataInicio) {
        this.ofertaDataInicio = ofertaDataInicio;
    }

    public LocalDate getOfertaDataFim() {
        return ofertaDataFim;
    }

    public void setOfertaDataFim(LocalDate ofertaDataFim) {
        this.ofertaDataFim = ofertaDataFim;
    }

    public double getOfertaPreco() {
        return ofertaPreco;
    }

    public void setOfertaPreco(double ofertaPreco) {
        this.ofertaPreco = ofertaPreco;
    }

    public boolean getOfertaStatus() {
        return ofertaStatus;
    }

    public void setOfertaStatus(boolean ofertaStatus) {
        this.ofertaStatus = ofertaStatus;
    }

    public Produto getOfertaProduto() {
        return ofertaProduto;
    }

    public void setOfertaProduto(Produto ofertaProduto) {
        this.ofertaProduto = ofertaProduto;
    }
}
