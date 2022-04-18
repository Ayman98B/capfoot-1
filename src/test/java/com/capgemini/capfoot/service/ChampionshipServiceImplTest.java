package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Statut;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;


class ChampionshipServiceImplTest {
    @Mock
    private ChampionshipRepo championshipRepo;
//    @InjectMocks
    private ChampionshipService championshipServiceTest;

    @BeforeEach
    void SetUp(){
        this.championshipServiceTest = new ChampionshipServiceImpl(this.championshipRepo);
    }

    @Test
    void testDeleteChampionship() {
        Championship championship = new Championship(1L,"Capfoot",LocalDate.of(2022,02,01), LocalDate.of(2022,02,03), Statut.INSCRIPTION,false,null,null);
        championshipServiceTest.createChampionship(championship);



    }


}