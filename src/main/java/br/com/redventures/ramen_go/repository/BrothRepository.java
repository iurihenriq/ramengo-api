package br.com.redventures.ramen_go.repository;

import br.com.redventures.ramen_go.model.Broth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrothRepository extends JpaRepository<Broth, String> {
}
