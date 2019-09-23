package br.senac.rj.crm;

import br.senac.rj.crm.domain.NivelInstrucao;
import br.senac.rj.crm.domain.Produto;
import br.senac.rj.crm.service.NivelInstrucaoService;
import br.senac.rj.crm.service.ProdutoService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmApplication.class)
@Transactional
public class ProdutoServiceTests {

        @Autowired
        private ProdutoService produtoService;

        @Autowired
        private NivelInstrucaoService nivelInstrucaoService;

        @Test
        public void saveProdutoWithAllFilled_shouldSuccess() throws ObjectNotFoundException {
            NivelInstrucao nivelInstrucao = new NivelInstrucao();
            nivelInstrucao.setNivelInstrucaoStatus(true);
            nivelInstrucao.setNivelInstrucaoDescricao("Ensino Médio Completo");
            nivelInstrucaoService.save(nivelInstrucao).getNivelInstrucaoId();

            Produto p = new Produto();
            p.setProdutoNome("ADS");
            p.setNivelInstrucao(nivelInstrucao);
            p.setProdutoDescricao("Belo Produto");
            p.setProdutoStatus(true);
            Integer produtoId = produtoService.save(p).getProdutoId();

            Produto produtoFound = produtoService.findById(produtoId);

            assertNotNull(produtoFound);
            assertEquals(produtoFound.getProdutoNome(), p.getProdutoNome());
            assertEquals(produtoFound.getProdutoDescricao(), p.getProdutoDescricao());
            assertEquals(produtoFound.getProdutoStatus(), p.getProdutoStatus());
            assertEquals(produtoFound.getNivelInstrucao().getNivelInstrucaoStatus(), p.getNivelInstrucao().getNivelInstrucaoStatus());
            assertEquals(produtoFound.getNivelInstrucao().getNivelInstrucaoDescricao(), p.getNivelInstrucao().getNivelInstrucaoDescricao());
        }

        @Test(expected = javax.validation.ConstraintViolationException.class)
        public void saveProdutoWithNullName_shouldCatchException() {
            NivelInstrucao nivelInstrucao = new NivelInstrucao();
            nivelInstrucao.setNivelInstrucaoStatus(true);
            nivelInstrucao.setNivelInstrucaoDescricao("Ensino Médio Completo");
            nivelInstrucaoService.save(nivelInstrucao).getNivelInstrucaoId();

            Produto p = new Produto();
            p.setProdutoNome(null);
            p.setNivelInstrucao(nivelInstrucao);
            p.setProdutoDescricao("Belo Produto");
            p.setProdutoStatus(true);

            produtoService.save(p);
        }

        @Test
        public void saveProdutoWithNullStatus_shouldBeFalseOnDatabase() throws ObjectNotFoundException {
            NivelInstrucao nivelInstrucao = new NivelInstrucao();
            nivelInstrucao.setNivelInstrucaoStatus(true);
            nivelInstrucao.setNivelInstrucaoDescricao("Ensino Médio Completo");
            nivelInstrucaoService.save(nivelInstrucao).getNivelInstrucaoId();

            Produto p = new Produto();
            p.setProdutoNome("ADS");
            p.setNivelInstrucao(nivelInstrucao);
            p.setProdutoDescricao("Belo Produto");

            Integer id = produtoService.save(p).getProdutoId();

            Produto produtoFound = produtoService.findById(id);

            assertEquals(produtoFound.getProdutoStatus(), false);
        }

        @Test
        public void saveProdutoWithNullNivelInstrucao_shouldSuccess() throws ObjectNotFoundException {

            Produto p = new Produto();
            p.setProdutoNome("ADS");
            p.setNivelInstrucao(null);
            p.setProdutoDescricao("Belo Produto");
            p.setProdutoStatus(true);

            Integer id = produtoService.save(p).getProdutoId();

            Produto produtoFound = produtoService.findById(id);

            assertNotNull(produtoFound);
            assertEquals(produtoFound.getProdutoNome(), p.getProdutoNome());
            assertEquals(produtoFound.getProdutoDescricao(), p.getProdutoDescricao());
            assertEquals(produtoFound.getProdutoStatus(), p.getProdutoStatus());
            assertNull(p.getNivelInstrucao());
        }


}
