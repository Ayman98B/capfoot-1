package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.CoreMatchers.is;

@DataJpaTest
@TestMethodOrder(MethodOrderer.class)
public class ChampionShipTests {

    @Autowired
    private ChampionshipRepo championshipRepo;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void testCreateChampion(){
        Championship champ = new Championship();
        champ.setLabel("test test");
        champ.setStartDate(LocalDate.now());
        champ.setEndDate(LocalDate.now());
        champ.setProgress(true);
        Championship saveChampionship = championshipRepo.save(champ);

        assertNotNull(saveChampionship);
    }

    @Test
    @Rollback(value = false)
    @Order(2)
    public void testUpdateChampion(){
        String label = "test label";
        Championship champ = new Championship();
        champ.setId(1L);
        champ.setLabel(label);
        champ.setStartDate(LocalDate.now());
        champ.setEndDate(LocalDate.now());
        champ.setProgress(false);
        championshipRepo.save(champ);

        Championship updateChampionship = championshipRepo.findByLabel(label);
        assertThat(updateChampionship.getLabel()).isEqualTo(label);
    }

    @Test
    @Rollback(value = false)
    @Order(3)
    public void testListChampions(){
        List<Championship> championships = (List<Championship>) championshipRepo.findAll();
        Assertions.assertNotEquals(Collections.EMPTY_LIST, championships);
    }

}
