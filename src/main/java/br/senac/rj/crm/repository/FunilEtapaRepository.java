package br.senac.rj.crm.repository;

import br.senac.rj.crm.domain.FunilEtapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunilEtapaRepository extends JpaRepository<FunilEtapa, Integer> {
    List<FunilEtapa> findByFunilEtapaStatus(boolean status);
}
