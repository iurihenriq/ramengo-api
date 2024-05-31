package br.com.redventures.ramengo.controller;

import br.com.redventures.ramengo.model.dto.BrothDTO;
import br.com.redventures.ramengo.service.BrothService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/broths")
public class BrothController {
    private final BrothService brothService;

    @GetMapping
    public ResponseEntity<List<BrothDTO>> getAllBroths() {
        return new ResponseEntity<>(brothService.getAllBroths(), OK);
    }

}
