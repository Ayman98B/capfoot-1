package com.capgemini.capfoot.entity;

import lombok.*;

import javax.persistence.*;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Groupe_Equipe {
    @EmbeddedId
    private Long id;
    private int nbMatchNull;
    private int nbMatchGagne;
    private int nbDefaite;
    private int pointCumulee;

}
