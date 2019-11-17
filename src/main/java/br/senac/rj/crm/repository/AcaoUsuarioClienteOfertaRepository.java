package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.AcaoUsuarioClienteOferta;
import br.senac.rj.crm.domain.ClienteOferta;
import br.senac.rj.crm.domain.dto.AcaoIndiceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public interface AcaoUsuarioClienteOfertaRepository extends JpaRepository<AcaoUsuarioClienteOferta, Integer> {

    List<AcaoUsuarioClienteOferta> findAllByClienteOfertaOrderByAcaoUsuarioClienteOfertaDataAsc(ClienteOferta clienteOferta);

//    @Query(value = "SELECT ACAO_ID, COUNT(ACAO_ID) as quantidade FROM ACAO_USUARIO_CLIENTE_OFERTA AS aco GROUP BY ACAO_ID", nativeQuery = true)
    @Query("SELECT NEW br.senac.rj.crm.domain.dto.AcaoIndiceDto(aco.acao, count(aco.acao)) From AcaoUsuarioClienteOferta as aco group by aco.acao")
    List<AcaoIndiceDto> findPercentageOfAcaoUsage();
}
