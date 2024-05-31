package br.com.redventures.ramengo.repository;

import br.com.redventures.ramengo.model.Broth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrothRepository extends JpaRepository<Broth, String> {
}
