package br.senac.rj.crm.repository.dto;

import br.senac.rj.crm.domain.Acao;

public class AcaoIndiceDto {

    private Acao acao;
    private Long quantidade;

    public AcaoIndiceDto(Acao acao, Long quantidade) {
        this.acao = acao;
        this.quantidade = quantidade;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

}
