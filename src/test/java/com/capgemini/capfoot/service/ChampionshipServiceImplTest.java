package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;


import java.time.LocalDate;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ChampionshipServiceImplTest {

    @Mock
    private ChampionshipRepo championshipRepo;


    public ChampionshipServiceImplTest() {
    }

    @Test
    public void testDeleteChampionship() {
        Championship championship = new Championship();
        championship.setLabel("CapFoot");
        championship.setProgress(false);
        championship.setId(1L);
        championshipRepo.save(championship);
        Championship result = championshipRepo.getById(1L);
        System.out.println(result);
    }
}