package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.AcaoUsuarioClienteOferta;
import br.senac.rj.crm.domain.ClienteOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcaoUsuarioClienteOfertaRepository extends JpaRepository<AcaoUsuarioClienteOferta, Integer> {

    List<AcaoUsuarioClienteOferta> findAllByClienteOfertaOrderByAcaoUsuarioClienteOfertaDataAsc(ClienteOferta clienteOferta);
}
