package br.com.redventures.ramengo.controller;

import br.com.redventures.ramengo.exception.MissingIdsException;
import br.com.redventures.ramengo.model.dto.OrderDTO;
import br.com.redventures.ramengo.model.dto.OrderForm;
import br.com.redventures.ramengo.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody String orderStr) throws JsonProcessingException {
        OrderForm order = objectMapper.readValue(orderStr, OrderForm.class);
        if ((order.proteinId() == null || order.proteinId().isEmpty()) ||
                (order.brothId() == null) || order.brothId().isEmpty())
            throw new MissingIdsException();
        return new ResponseEntity<>(orderService.placeOrder(order), HttpStatus.OK);
    }
}
