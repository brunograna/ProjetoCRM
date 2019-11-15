package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.*;
import br.senac.rj.crm.repository.ClienteOfertaRepository;
import br.senac.rj.crm.repository.ClienteRepository;
import br.senac.rj.crm.repository.FunilEtapaRepository;
import br.senac.rj.crm.repository.OfertaRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteOfertaService {

    private Logger logger = LoggerFactory.getLogger(ClienteOferta.class);

    @Autowired
    private ClienteOfertaRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private FunilEtapaRepository funilEtapaRepository;


    public ClienteOferta findById(ClienteOfertaId id) throws ObjectNotFoundException {
        Optional<ClienteOferta> clienteOferta = repository.findById(id);

        return clienteOferta.orElseThrow(() -> new ObjectNotFoundException("ClienteOferta wih id ("+id+") not found"));
    }

    public List<ClienteOferta> findAll(){
        return repository.findAll();
    }

    public ClienteOferta save(ClienteOferta clienteOferta){
        return repository.save(clienteOferta);
    }

    public ClienteOferta update(ClienteOferta clienteOferta) throws ObjectNotFoundException {
        Optional<ClienteOferta> clienteOfertaFromDB = repository.findById(clienteOferta.getClienteOfertaId());

        if(clienteOfertaFromDB.isPresent()){
            clienteOfertaFromDB.get().setClienteOfertaStatus(clienteOferta.getClienteOfertaStatus());
            clienteOfertaFromDB.get().setClienteOfertaPreco(clienteOferta.getClienteOfertaPreco());
            clienteOfertaFromDB.get().setClienteOfertaPrecoDescricao(clienteOferta.getClienteOfertaPrecoDescricao());
            clienteOfertaFromDB.get().setFunilEtapa(clienteOferta.getFunilEtapa());
            return clienteOfertaFromDB.get();
        }else{
            throw new ObjectNotFoundException("ClienteOferta Not Found");
        }
    }

    public ClienteOferta updateFunilEtapaByClienteIdAndOfertaId(Integer clienteId, Integer ofertaId, Integer funilEtapaId) throws ObjectNotFoundException {
        Optional<Cliente> clienteFromDB = clienteRepository.findById(clienteId);
        Optional<Oferta> ofertaFromDB = ofertaRepository.findById(clienteId);
        Optional<FunilEtapa> funilEtapa = funilEtapaRepository.findById(funilEtapaId);

        if(clienteFromDB.isPresent() && ofertaFromDB.isPresent() && funilEtapa.isPresent()){
            ClienteOfertaId clienteOfertaId = new ClienteOfertaId(clienteFromDB.get(), ofertaFromDB.get());
            Optional<ClienteOferta> clienteOfertaFromDB = repository.findById(clienteOfertaId);

            if(clienteOfertaFromDB.isPresent()){
                logger.info("Setting FunilEtapa["+funilEtapa.get().getFunilEtapaId()+"] to ClienteOferta["+clienteOfertaId.getCliente().getClienteId()+"_"+clienteOfertaId.getOferta().getOfertaId()+"]");
                clienteOfertaFromDB.get().setFunilEtapa(funilEtapa.get());

                return clienteOfertaFromDB.get();
            }else{
                throw new ObjectNotFoundException("ClienteOferta was not Found");
            }
        }else{
            throw new ObjectNotFoundException("Cliente or Oferta or FunilEtapa was not Found");
        }
    }

    public void softDelete(ClienteOfertaId id) {
        Optional<ClienteOferta> clienteOfertaFromDB = repository.findById(id);
        if(clienteOfertaFromDB.isPresent()){
            clienteOfertaFromDB.get().setClienteOfertaStatus(false);
        }
    }

    public List<ClienteOferta> findAllActive() {
        return repository.findByClienteOfertaStatus(true);
    }
}
