package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.AcaoUsuarioClienteOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoUsuarioClienteOfertaRepository extends JpaRepository<AcaoUsuarioClienteOferta, Integer> {
}
