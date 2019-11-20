package br.senac.rj.crm.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

public class FunilItemDto {

    private String id;
    private String title;
    private String preco;
    private boolean isSold;

    public FunilItemDto() {
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return "<div class=\"div_item\">" +
                    "<h4 class=\"mrg_0 item_nome\">" +
                        "<i class=\"fa fa-user\"></i> " + title + "</h4>" +
                    "<br>" +
                    "<i class=\"fa fa-money\"></i> R$<span class=\"item-valor\">" + preco + "</span>" +
                "</div>" +
                "<div class=\"div_item_icons\">" +
                    "<a href=\"#\" class=\"timelinemodal-open\" data-toggle=\"modal\" data-target=\"#modal-timeline\">" +
                        "<i class=\"fa fa-map-o icon_item\"></i>" +
                    "</a>" +
                    "<a href=\"#\" class=\"viewmodal-open\" data-toggle=\"modal\" data-target=\"#modal-oferta\">" +
                        "<i class=\"fa fa-eye icon_item\"></i>" +
                    "</a>" +
                    "<a href=\"#\" class=\"acaomodal-open\" data-toggle=\"modal\" data-target=\"#modal-acao\">" +
                        "<i class=\"fa fa-plus-circle icon_item\"></i>" +
                    "</a>" +
                "</div>";
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
