package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.ClienteOferta;
import br.senac.rj.crm.domain.ClienteOfertaId;
import br.senac.rj.crm.repository.ClienteOfertaRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteOfertaService {

    @Autowired
    private ClienteOfertaRepository repository;


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
