package br.com.redventures.ramengo.repository;

import br.com.redventures.ramengo.model.Protein;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProteinRepository extends JpaRepository<Protein, String> {
}
