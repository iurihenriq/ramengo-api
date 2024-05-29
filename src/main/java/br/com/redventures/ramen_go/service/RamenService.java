package br.com.redventures.ramen_go.service;

import br.com.redventures.ramen_go.model.Broth;
import br.com.redventures.ramen_go.model.Order;
import br.com.redventures.ramen_go.model.Protein;
import br.com.redventures.ramen_go.model.dto.OrderDTO;
import br.com.redventures.ramen_go.model.dto.OrderIdDTO;
import br.com.redventures.ramen_go.repository.BrothRepository;
import br.com.redventures.ramen_go.repository.ProteinRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@Service
public class RamenService {

    private final BrothRepository brothRepository;
    private final ProteinRepository proteinRepository;
    private final String externalApi;
    private final String apiKey;
    private final String apiKeyHeaderName;


    public RamenService(BrothRepository brothRepository,
                        ProteinRepository proteinRepository,
                        @Value("${external-api.url}") String externalApi,
                        @Value("${external-api.key.value}") String apiKey,
                        @Value("${external-api.key.header.name}") String apiKeyHeaderName) {
        this.brothRepository = brothRepository;
        this.proteinRepository = proteinRepository;
        this.externalApi = externalApi;
        this.apiKey = apiKey;
        this.apiKeyHeaderName = apiKeyHeaderName;
    }

    public List<Broth> getAllBroths() {
        return brothRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Broth::getPrice))
                .toList();
    }

    public List<Protein> getAllProteins() {
        return proteinRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Protein::getPrice))
                .toList();
    }

    public Order placeOrder(OrderDTO order) {
        Optional<Protein> protein = proteinRepository.findById(order.proteinId());
        Optional<Broth> broth = brothRepository.findById(order.brothId());

        String orderId = getOrderId().orderId();
        String description = broth.get().getName() + " and " + protein.get().getName() + " Ramen";
        String image = "https://tech.redventures.com.br/icons/ramen/ramen"+protein.get().getName()+".png";

        return Order.builder()
                .id(orderId)
                .description(description)
                .image(image)
                .build();
    }

    public OrderIdDTO getOrderId() {
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
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "could not place order");
        }
    }
}
