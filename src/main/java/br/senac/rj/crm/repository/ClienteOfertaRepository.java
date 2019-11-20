package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.ClienteOferta;
import br.senac.rj.crm.domain.ClienteOfertaId;
import br.senac.rj.crm.domain.FunilEtapa;
import br.senac.rj.crm.repository.dto.ClienteEtapaValorDto;
import br.senac.rj.crm.repository.dto.ClienteOfertaFechamentoDto;
import br.senac.rj.crm.repository.dto.OfertaEtapaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteOfertaRepository extends JpaRepository<ClienteOferta, ClienteOfertaId> {
    List<ClienteOferta> findByClienteOfertaStatus(boolean status);

    List<ClienteOferta> findByFunilEtapa(FunilEtapa funilEtapa);

    @Query("SELECT NEW br.senac.rj.crm.repository.dto.ClienteEtapaValorDto(co.funilEtapa, sum(co.clienteOfertaPreco)) From ClienteOferta as co where co.clienteOfertaStatus = true group by co.funilEtapa")
    List<ClienteEtapaValorDto> getFunilEtapaClienteValor();

    @Query("SELECT NEW br.senac.rj.crm.repository.dto.OfertaEtapaDto(co.clienteOfertaId.oferta, co.funilEtapa, count(co.clienteOfertaId.oferta)) From ClienteOferta as co where co.clienteOfertaStatus = true group by co.clienteOfertaId.oferta, co.funilEtapa")
    List<OfertaEtapaDto> getOfertaPorEtapa();

    @Query("SELECT NEW br.senac.rj.crm.repository.dto.ClienteOfertaFechamentoDto(sum(co.clienteOfertaPreco), co.clienteOfertaDataFechamento) From ClienteOferta as co group by co.clienteOfertaDataFechamento having co.clienteOfertaDataFechamento <> null")
    List<ClienteOfertaFechamentoDto> getClienteOfertaFechamento();

    Long countByClienteOfertaStatus(boolean status);
}
