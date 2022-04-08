package com.capgemini.capfoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Groupe_Equipe {
    @EmbeddedId
    private GroupeEquipeId groupeEquipeId;
    private int nbMatchNull;
    private int nbMatchGagne;
    private int nbDefaite;
    private int pointCumulee;

}
