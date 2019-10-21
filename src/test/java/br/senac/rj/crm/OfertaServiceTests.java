package br.senac.rj.crm;

import br.senac.rj.crm.domain.NivelInstrucao;
import br.senac.rj.crm.domain.Oferta;
import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.service.NivelInstrucaoService;
import br.senac.rj.crm.service.OfertaService;
import br.senac.rj.crm.service.ProdutoService;
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

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmApplication.class)
@Transactional
public class OfertaServiceTests {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private NivelInstrucaoService nivelInstrucaoService;

    @Test
    public void saveOfertaWithAllFilled_shouldSuccess() throws ObjectNotFoundException {

        Produto produto = retrieveProduto();
        Oferta oferta = retrieveOferta(produto);

        Integer id = ofertaService.save(oferta).getOfertaId();

        Oferta ofertaFound = ofertaService.findById(id);

        assertNotNull(ofertaFound);
        assertEquals(ofertaFound.getOfertaDataFim(), oferta.getOfertaDataFim());
        assertEquals(ofertaFound.getOfertaDataInicio(), oferta.getOfertaDataInicio());
        assertEquals(ofertaFound.getOfertaDescricao(), oferta.getOfertaDescricao());
        assertEquals(ofertaFound.getOfertaPreco(), oferta.getOfertaPreco(), .2);
        assertEquals(ofertaFound.getOfertaStatus(), oferta.getOfertaStatus());
        assertEquals(ofertaFound.getOfertaProduto().getProdutoNome(), oferta.getOfertaProduto().getProdutoNome());
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void saveOfertaWithNullProduto_shouldCatchException() throws ObjectNotFoundException {
        Oferta oferta = retrieveOferta(null);
        ofertaService.save(oferta);
    }

    @Test
    public void saveOfertaWithNullDataInicio_shouldSuccess() throws ObjectNotFoundException {
        Produto produto = retrieveProduto();
        Oferta oferta = retrieveOferta(produto);
        oferta.setOfertaDataInicio(null);

        Integer id = ofertaService.save(oferta).getOfertaId();

        Oferta ofertaFound = ofertaService.findById(id);

        assertNotNull(ofertaFound);
        assertEquals(ofertaFound.getOfertaDataFim(), oferta.getOfertaDataFim());
        assertEquals(ofertaFound.getOfertaDataInicio(), oferta.getOfertaDataInicio());
        assertEquals(ofertaFound.getOfertaDescricao(), oferta.getOfertaDescricao());
        assertEquals(ofertaFound.getOfertaPreco(), oferta.getOfertaPreco(), .2);
        assertEquals(ofertaFound.getOfertaStatus(), oferta.getOfertaStatus());
        assertEquals(ofertaFound.getOfertaProduto().getProdutoNome(), oferta.getOfertaProduto().getProdutoNome());
    }

    @Test
    public void saveOfertaWithNullDataFim_shouldSuccess() throws ObjectNotFoundException {
        Produto produto = retrieveProduto();
        Oferta oferta = retrieveOferta(produto);
        oferta.setOfertaDataFim(null);

        Integer id = ofertaService.save(oferta).getOfertaId();

        Oferta ofertaFound = ofertaService.findById(id);

        assertNotNull(ofertaFound);
        assertEquals(ofertaFound.getOfertaDataFim(), oferta.getOfertaDataFim());
        assertEquals(ofertaFound.getOfertaDataInicio(), oferta.getOfertaDataInicio());
        assertEquals(ofertaFound.getOfertaDescricao(), oferta.getOfertaDescricao());
        assertEquals(ofertaFound.getOfertaPreco(), oferta.getOfertaPreco(), .2);
        assertEquals(ofertaFound.getOfertaStatus(), oferta.getOfertaStatus());
        assertEquals(ofertaFound.getOfertaProduto().getProdutoNome(), oferta.getOfertaProduto().getProdutoNome());
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void saveOfertaWithEmptyDescricao_shouldCatchException() throws ObjectNotFoundException {
        Produto produto = retrieveProduto();
        Oferta oferta = retrieveOferta(produto);
        oferta.setOfertaDescricao("");
        ofertaService.save(oferta);
    }

    @Test
    public void saveOfertaWithNullPreco_shouldBeZero() throws ObjectNotFoundException {
        Produto produto = retrieveProduto();
        Oferta oferta = new Oferta();
        oferta.setOfertaProduto(produto);
        oferta.setOfertaDescricao("Oferta de Natal");
        oferta.setOfertaDataInicio(LocalDate.now());
        oferta.setOfertaDataFim(LocalDate.now().plusDays(7));
        oferta.setOfertaStatus(true);

        Integer id = ofertaService.save(oferta).getOfertaId();

        Oferta ofertaFound = ofertaService.findById(id);

        assertNotNull(ofertaFound);
        assertEquals(ofertaFound.getOfertaDataFim(), oferta.getOfertaDataFim());
        assertEquals(ofertaFound.getOfertaDataInicio(), oferta.getOfertaDataInicio());
        assertEquals(ofertaFound.getOfertaDescricao(), oferta.getOfertaDescricao());
        assertEquals(ofertaFound.getOfertaPreco(), oferta.getOfertaPreco(), .2);
        assertEquals(ofertaFound.getOfertaStatus(), oferta.getOfertaStatus());
        assertEquals(ofertaFound.getOfertaProduto().getProdutoNome(), oferta.getOfertaProduto().getProdutoNome());
    }

    public Produto retrieveProduto(){
        NivelInstrucao nivelInstrucao = new NivelInstrucao();
        nivelInstrucao.setNivelInstrucaoStatus(true);
        nivelInstrucao.setNivelInstrucaoDescricao("Ensino Médio Completo");
        nivelInstrucaoService.save(nivelInstrucao).getNivelInstrucaoId();

        Produto p = new Produto();
        p.setProdutoNome("Análise e Desenvolvimento de Sistemas");
        p.setNivelInstrucao(nivelInstrucao);
        p.setProdutoDescricao("Ótimo Curso");
        p.setProdutoStatus(true);

        return produtoService.save(p);
    }

    public Oferta retrieveOferta(Produto produto){
        Oferta oferta = new Oferta();
        oferta.setOfertaProduto(produto);
        oferta.setOfertaPreco(599.99);
        oferta.setOfertaDescricao("Oferta de Natal");
        oferta.setOfertaDataInicio(LocalDate.now());
        oferta.setOfertaDataFim(LocalDate.now().plusDays(7));
        oferta.setOfertaStatus(true);
        return oferta;
    }
}
