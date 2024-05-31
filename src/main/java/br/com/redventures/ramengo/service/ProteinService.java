package br.com.redventures.ramengo.service;

import br.com.redventures.ramengo.model.Protein;
import br.com.redventures.ramengo.model.dto.ProteinDTO;
import br.com.redventures.ramengo.repository.ProteinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProteinService {
    private final ProteinRepository proteinRepository;

    public List<ProteinDTO> getAllProteins() {
        return proteinRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Protein::getPrice))
                .map(ProteinDTO::new)
                .toList();
    }

    public Optional<Protein> findById(String id) {
        return proteinRepository.findById(id);
    }
}
