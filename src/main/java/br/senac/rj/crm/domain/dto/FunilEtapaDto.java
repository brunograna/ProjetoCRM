package br.senac.rj.crm.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;

public class FunilEtapaDto {

    private String id;
    private String title;

    @JsonProperty("class")
    private String classe = "top_funil";

    private ArrayList<FunilItemDto> item = new ArrayList<>();

    public FunilEtapaDto() {
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return "<i class=\"fa fa-table\"></i> "+title+" <br><br><span class=\"total-qtd\">0</span> Oportunidade(s) <br> Total: R$<span class=\"total-price\">0.000</span>";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addItem(FunilItemDto funilItemDto){
        this.item.add(funilItemDto);
    }

    public void removeItem(FunilItemDto funilItemDto){
        this.item.remove(funilItemDto);
    }

    public ArrayList<FunilItemDto> getItem() {
        return item;
    }

    public void setItem(ArrayList<FunilItemDto> item) {
        this.item = item;
    }
}
