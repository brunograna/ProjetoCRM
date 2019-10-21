package br.senac.rj.crm;


import br.senac.rj.crm.domain.CategoriaDado;
import br.senac.rj.crm.domain.Cliente;
import br.senac.rj.crm.domain.ClienteDado;
import br.senac.rj.crm.domain.DadoTipo;
import br.senac.rj.crm.service.CategoriaDadoService;
import br.senac.rj.crm.service.ClienteService;
import br.senac.rj.crm.service.DadoTipoService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmApplication.class)
@Transactional
public class ClienteServiceTests {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CategoriaDadoService categoriaDadoService;

    @Autowired
    private DadoTipoService dadoTipoService;

    @Test
    public void saveClienteWithAllFilled_shouldSuccess() throws ObjectNotFoundException {
        Cliente cliente = new Cliente();
        cliente.setClienteDado(retrieveClienteDados());
        cliente.setClienteStatus(true);
        cliente.setClienteSobrenome("Da Silva");
        cliente.setClienteNome("João");
        cliente.setClienteEmail("joao@email.com");
        cliente.setClienteCpf("111.333.444-55");

        Integer id = clienteService.save(cliente).getClienteId();

        Cliente clienteFound = clienteService.findById(id);

        assertNotNull(clienteFound);
        assertEquals(clienteFound.getClienteCpf(), cliente.getClienteCpf());
        assertEquals(clienteFound.getClienteEmail(), cliente.getClienteEmail());
        assertEquals(clienteFound.getClienteNome(), cliente.getClienteNome());
        assertEquals(clienteFound.getClienteSobrenome(), cliente.getClienteSobrenome());
        assertEquals(clienteFound.getClienteStatus(), cliente.getClienteStatus());
        assertTrue(clienteFound.getClienteDado().equals(cliente.getClienteDado()));
    }

    @Test
    public void saveClienteWithNullClienteDado_shouldSuccess() throws ObjectNotFoundException {
        Cliente cliente = new Cliente();
        cliente.setClienteDado(null);
        cliente.setClienteStatus(true);
        cliente.setClienteSobrenome("Da Silva");
        cliente.setClienteNome("João");
        cliente.setClienteEmail("joao@email.com");
        cliente.setClienteCpf("111.333.444-55");

        Integer id = clienteService.save(cliente).getClienteId();

        Cliente clienteFound = clienteService.findById(id);

        assertNotNull(clienteFound);
        assertEquals(clienteFound.getClienteCpf(), cliente.getClienteCpf());
        assertEquals(clienteFound.getClienteEmail(), cliente.getClienteEmail());
        assertEquals(clienteFound.getClienteNome(), cliente.getClienteNome());
        assertEquals(clienteFound.getClienteSobrenome(), cliente.getClienteSobrenome());
        assertEquals(clienteFound.getClienteStatus(), cliente.getClienteStatus());
        assertNull(clienteFound.getClienteDado());
    }

    public List<ClienteDado> retrieveClienteDados(){
        CategoriaDado categoriaDado = new CategoriaDado();
        categoriaDado.setCategoriaDadoDescricao("Dados Pessoais");
        categoriaDado.setCategoriaDadoStatus(true);
        categoriaDadoService.save(categoriaDado);

        DadoTipo dadoTipo1 = new DadoTipo();
        dadoTipo1.setDadoTipoPadrao("");
        dadoTipo1.setDadoTipoDescricao("Passaporte");
        dadoTipo1.setCategoria(categoriaDado);
        dadoTipoService.save(dadoTipo1);

        ClienteDado clienteDado1 = new ClienteDado();
        clienteDado1.setClienteDadoStatus(true);
        clienteDado1.setClienteDadoValor("Meu Passaporte");
        clienteDado1.setDadoTipo(dadoTipo1);

        List<ClienteDado> clienteDados = new ArrayList<>();
        clienteDados.add(clienteDado1);
        return clienteDados;
    }
}
