package br.senac.rj.crm.service;

import br.senac.rj.crm.domain.Cliente;
import br.senac.rj.crm.repository.ClienteDadoRepository;
import br.senac.rj.crm.repository.ClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteDadoRepository clienteDadoRepository;

    public Cliente findById(Integer id) throws ObjectNotFoundException {
        Optional<Cliente> dadoTipo = repository.findById(id);

        return dadoTipo.orElseThrow(() -> new ObjectNotFoundException("Cliente wih id ("+id+") not found"));
    }

    public Cliente save(Cliente c){
        c.cleanClienteDadoList(c.getClienteDado());
        return repository.save(c);
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente update(Cliente c) throws ObjectNotFoundException {
        Optional<Cliente> clienteFromDB = repository.findById(c.getClienteId());

        if(clienteFromDB.isPresent()){
            clienteDadoRepository.removeByClienteClienteId(clienteFromDB.get().getClienteId());
            c.cleanClienteDadoList(c.getClienteDado());
            clienteFromDB.get().setClienteCpf(c.getClienteCpf());
            clienteFromDB.get().setClienteDado(c.getClienteDado());
            clienteFromDB.get().setClienteEmail(c.getClienteEmail());
            clienteFromDB.get().setClienteNome(c.getClienteNome());
            clienteFromDB.get().setClienteSobrenome(c.getClienteSobrenome());
            clienteFromDB.get().setClienteStatus(c.getClienteStatus());

            return clienteFromDB.get();
        }else{
            throw new ObjectNotFoundException("Cliente Not Found");
        }
    }

    public void softDelete(Integer id) {
        Optional<Cliente> clienteFromDB = repository.findById(id);
        if(clienteFromDB.isPresent()){
            clienteFromDB.get().setClienteStatus(false);
        }
    }

    public long getTotalInNumber(){
        return repository.count();
    }

}