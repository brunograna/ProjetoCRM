package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.ClienteOferta;
import br.senac.rj.crm.domain.FunilEtapa;
import br.senac.rj.crm.domain.dto.ClienteOfertaDto;
import br.senac.rj.crm.domain.dto.FunilEtapaDto;
import br.senac.rj.crm.domain.dto.FunilItemDto;
import br.senac.rj.crm.repository.ClienteOfertaRepository;
import br.senac.rj.crm.repository.FunilEtapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteOfertaDtoService {

    @Autowired
    private FunilEtapaService funilEtapaService;

    @Autowired
    private ClienteOfertaRepository clienteOfertaRepository;

    public ClienteOfertaDto getClienteOferta(){
        ClienteOfertaDto clienteOfertaDto = new ClienteOfertaDto();

        List<FunilEtapa> funilEtapas = funilEtapaService.findAll();

        for(FunilEtapa funilEtapa: funilEtapas){
            FunilEtapaDto funilEtapaDto = new FunilEtapaDto();
            funilEtapaDto.setId(String.valueOf(funilEtapa.getFunilEtapaId()));
            funilEtapaDto.setTitle(funilEtapa.getFunilEtapaDescricao());

            List<ClienteOferta> clienteOfertaList = clienteOfertaRepository.findByFunilEtapa(funilEtapa);
            for(ClienteOferta clienteOferta: clienteOfertaList){
                FunilItemDto funilItemDto = new FunilItemDto();
                funilItemDto.setId(clienteOferta.getClienteOfertaId().getCliente().getClienteId()+"_"+clienteOferta.getClienteOfertaId().getOferta().getOfertaId());
                funilItemDto.setPreco(String.format("%.2f",clienteOferta.getClienteOfertaPreco()));
                funilItemDto.setTitle(clienteOferta.getClienteOfertaId().getCliente().getClienteNome());


                funilEtapaDto.addItem(funilItemDto);
            }

            clienteOfertaDto.addFunilEtapaDto(funilEtapaDto);
        }

        return clienteOfertaDto;
    }
}
