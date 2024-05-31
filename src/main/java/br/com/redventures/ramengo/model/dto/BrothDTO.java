package br.com.redventures.ramengo.model.dto;

import br.com.redventures.ramengo.model.Broth;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrothDTO {
    private String id;
    private String imageInactive;
    private String imageActive;
    private String name;
    private String description;
    private double price;

    public BrothDTO(Broth broth) {
        this.id = broth.getId();
        this.imageInactive = broth.getImageInactive();
        this.imageActive = broth.getImageActive();
        this.name = broth.getName();
        this.description = broth.getDescription();
        this.price = broth.getPrice();
    }
}
