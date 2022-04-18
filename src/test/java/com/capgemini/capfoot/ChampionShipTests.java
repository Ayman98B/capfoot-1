package com.capgemini.capfoot;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChampionShipTests {

    @Autowired
    private ChampionshipRepo championshipRepo;

    @Test
    @Rollback(value = false)
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

}
