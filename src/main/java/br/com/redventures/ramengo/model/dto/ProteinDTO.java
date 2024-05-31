package br.com.redventures.ramengo.model.dto;

import br.com.redventures.ramengo.model.Protein;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProteinDTO {
    private String id;
    private String imageInactive;
    private String imageActive;
    private String name;
    private String description;
    private double price;

    public ProteinDTO(Protein protein) {
        this.id = protein.getId();
        this.imageInactive = protein.getImageInactive();
        this.imageActive = protein.getImageActive();
        this.name = protein.getName();
        this.description = protein.getDescription();
        this.price = protein.getPrice();
    }
}
