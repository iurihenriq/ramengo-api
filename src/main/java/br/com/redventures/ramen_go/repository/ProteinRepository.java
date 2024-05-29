package br.com.redventures.ramen_go.repository;

import br.com.redventures.ramen_go.model.Protein;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProteinRepository extends JpaRepository<Protein, String> {
}
