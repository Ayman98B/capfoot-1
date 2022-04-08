package com.capgemini.capfoot.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Groupe_Equipe {
    @EmbeddedId
    private GroupeEquipeId groupeEquipeId;
    private int nbMatchNull;
    private int nbMatchGagne;
    private int nbDefaite;
    private int pointCumulee;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString

    @Entity
    @Table
    public static class Championat {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String libelle;

        // @Column(nullable = false)
        private LocalDate date_debut;

        // @Column(nullable = false)
        private LocalDate date_fin;

        @Column(columnDefinition = "varchar(255) default 'INSCRIPTION'")
        private Joueur.Statut statut;

        private boolean enCours;

        /*
         * @OneToMany private Groupe groupes;
         *
         * @ManyToOne private Admin admin;
         */

        /*
         * public boolean isEncours() { //LocalDate currentDate = if() return true; }
         */
    }
}
