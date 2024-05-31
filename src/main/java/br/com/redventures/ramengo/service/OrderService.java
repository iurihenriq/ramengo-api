package br.com.redventures.ramengo.service;

import br.com.redventures.ramengo.exception.OrderPlacementException;
import br.com.redventures.ramengo.model.Broth;
import br.com.redventures.ramengo.model.Protein;
import br.com.redventures.ramengo.model.dto.OrderDTO;
import br.com.redventures.ramengo.model.dto.OrderForm;
import br.com.redventures.ramengo.model.dto.OrderIdDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderService {
    private final BrothService brothService;
    private final ProteinService proteinService;
    private final String externalApi;
    private final String apiKey;
    private final String apiKeyHeaderName;

    public OrderService(BrothService brothService,
                        ProteinService proteinService,
                        @Value("${external-api.url}") String externalApi,
                        @Value("${external-api.key.value}") String apiKey,
                        @Value("${external-api.key.header.name}") String apiKeyHeaderName) {
        this.brothService = brothService;
        this.proteinService = proteinService;
        this.externalApi = externalApi;
        this.apiKey = apiKey;
        this.apiKeyHeaderName = apiKeyHeaderName;
    }


    public OrderDTO placeOrder(OrderForm order) {
        Optional<Protein> protein = proteinService.findById(order.proteinId());
        Optional<Broth> broth = brothService.findById(order.brothId());

        String orderId = getOrderId().orderId();
        String description = broth.get().getName() + " and " + protein.get().getName() + " Ramen";
        String image = "https://tech.redventures.com.br/icons/ramen/ramen" + protein.get().getName() + ".png";

        return OrderDTO.builder()
                .id(orderId)
                .description(description)
                .image(image)
                .build();
    }

    private OrderIdDTO getOrderId() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.add(this.apiKeyHeaderName, this.apiKey);
            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            String requestUrl = this.externalApi+"/orders/generate-id";

            ResponseEntity<OrderIdDTO> response =
                    restTemplate.exchange(
                            requestUrl,
                            HttpMethod.POST,
                            entity,
                            OrderIdDTO.class
                    );

            return response.getBody();
        } catch (RestClientException e) {
            throw new OrderPlacementException();
        }
    }
}
