package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.NivelInstrucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NivelInstrucaoRepository extends JpaRepository<NivelInstrucao, Integer> {
    List<NivelInstrucao> findByNivelInstrucaoStatus(boolean status);
}
