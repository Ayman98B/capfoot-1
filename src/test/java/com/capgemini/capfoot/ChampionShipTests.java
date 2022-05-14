package com.capgemini.capfoot;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import com.capgemini.capfoot.service.ChampionshipService;
import com.capgemini.capfoot.service.ChampionshipServiceImpl;


@RunWith(SpringRunner.class)
public class ChampionShipTests {

    @Mock
    private ChampionshipRepo championshipRepo;

    @InjectMocks
    ChampionshipService championshipService = new ChampionshipServiceImpl(championshipRepo);



    @Test
    public void testUpdateChampion(){
        // Given
        Championship championship = new Championship();
        championship.setLabel("First Label");
        championship.setId(2L);

        Mockito.when(championshipRepo.findById(championship.getId()))
                .thenReturn(Optional.of(championship));

        championship.setLabel("Second Label");
        championshipService.updateChampionship(championship);

        assertThat(championshipService.getChampionshipById(championship.getId()).getLabel())
                .isEqualTo("Second Label");
    }

    @Test
    public void testDeleteChampionship() {

        Championship c = new Championship();
        c.setLabel("CapFoot");
        c.setId(1L);
        Mockito.when(championshipRepo.findById(c.getId())).thenReturn(Optional.of(c));
        championshipService.deleteChampionship(c.getId());
        Mockito.verify(championshipRepo, times(1)).deleteById(1L);

    }

    @Before
    public void createChamp(){
        Championship champ = new Championship();
        champ.setId(1L);
        champ.setLabel("test test");
        champ.setStartDate(LocalDate.now());
        champ.setEndDate(LocalDate.now());
        champ.setProgress(false);
        championshipRepo.save(champ);
        System.out.println(champ);
        System.out.println(championshipRepo.getById(champ.getId()));
    }

}
