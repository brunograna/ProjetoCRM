package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.ClienteOferta;
import br.senac.rj.crm.domain.ClienteOfertaId;
import br.senac.rj.crm.domain.FunilEtapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteOfertaRepository extends JpaRepository<ClienteOferta, ClienteOfertaId> {
    List<ClienteOferta> findByClienteOfertaStatus(boolean status);

    List<ClienteOferta> findByFunilEtapa(FunilEtapa funilEtapa);
}
