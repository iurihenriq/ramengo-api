package br.com.redventures.ramengo.controller;

import br.com.redventures.ramengo.model.dto.ProteinDTO;
import br.com.redventures.ramengo.service.ProteinService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/proteins")
public class ProteinController {
    private final ProteinService proteinService;

    @GetMapping
    public ResponseEntity<List<ProteinDTO>> getAllProteins() {
        return new ResponseEntity<>(proteinService.getAllProteins(), OK);
    }
}
