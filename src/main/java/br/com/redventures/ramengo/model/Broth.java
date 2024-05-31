package br.com.redventures.ramengo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "tblBroth")
public class Broth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private String id;
    @Column(name = "IMAGEINACTIVE")
    private String imageInactive;
    @Column(name = "IMAGEACTIVE")
    private String imageActive;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private double price;
}