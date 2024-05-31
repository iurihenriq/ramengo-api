package br.com.redventures.ramengo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "tblOrder")
public class Order {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IMAGE")
    private String image;
}
