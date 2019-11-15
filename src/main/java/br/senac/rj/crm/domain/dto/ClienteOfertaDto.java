package br.senac.rj.crm.domain.dto;

import br.senac.rj.crm.domain.ClienteOferta;
import br.senac.rj.crm.domain.FunilEtapa;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

public class ClienteOfertaDto {
    private ArrayList<FunilEtapaDto> funilEtapaDtos = new ArrayList<>();;

    public ClienteOfertaDto() {
    }

    public void addFunilEtapaDto(FunilEtapaDto funilEtapaDto){
        this.funilEtapaDtos.add(funilEtapaDto);
    }

    public void removeFunilEtapaDto(FunilEtapaDto funilEtapaDto){
        this.funilEtapaDtos.remove(funilEtapaDto);
    }

    public ArrayList<FunilEtapaDto> getFunilEtapaDtos() {
        return funilEtapaDtos;
    }

    public void setFunilEtapaDtos(ArrayList<FunilEtapaDto> funilEtapaDtos) {
        this.funilEtapaDtos = funilEtapaDtos;
    }
}
