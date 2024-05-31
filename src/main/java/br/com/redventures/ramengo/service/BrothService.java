package br.com.redventures.ramengo.service;

import br.com.redventures.ramengo.model.Broth;
import br.com.redventures.ramengo.model.dto.BrothDTO;
import br.com.redventures.ramengo.repository.BrothRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrothService {
    private final BrothRepository brothRepository;

    public List<BrothDTO> getAllBroths() {
        return brothRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Broth::getPrice))
                .map(BrothDTO::new)
                .toList();
    }

    public Optional<Broth> findById(String id) {
        return brothRepository.findById(id);
    }
}
