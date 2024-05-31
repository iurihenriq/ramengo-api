package br.com.redventures.ramengo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDTO {
    private String id;
    private String description;
    private String image;
}
