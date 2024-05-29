package br.com.redventures.ramen_go.controller;

import br.com.redventures.ramen_go.model.Broth;
import br.com.redventures.ramen_go.model.Order;
import br.com.redventures.ramen_go.model.Protein;
import br.com.redventures.ramen_go.model.dto.OrderDTO;
import br.com.redventures.ramen_go.service.RamenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
public class RamenController {

    private final RamenService ramenService;
    private final ObjectMapper objectMapper;

    @GetMapping("/broths")
    public ResponseEntity<List<Broth>> getAllBroths() {
        return new ResponseEntity<>(ramenService.getAllBroths(), OK);
    }

    @GetMapping("/proteins")
    public ResponseEntity<List<Protein>> getAllProteins() {
        return new ResponseEntity<>(ramenService.getAllProteins(), OK);
    }

    @PostMapping(value = "/order", consumes = {"application/json", "text/plain"})
    public ResponseEntity<Order> placeOrder(@RequestBody String orderString) {
        try {
            OrderDTO order;
            if (orderString.trim().startsWith("{")) {
                order = objectMapper.readValue(orderString, OrderDTO.class);
            } else {
                throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported media type");
            }

            if (order.brothId() == null || order.proteinId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "both brothId and proteinId are required");
            }

            return new ResponseEntity<>(ramenService.placeOrder(order), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format", e);
        }
    }
}
