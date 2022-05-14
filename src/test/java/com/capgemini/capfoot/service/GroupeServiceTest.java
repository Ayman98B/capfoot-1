package com.capgemini.capfoot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.repository.GroupRepository;

@RunWith(SpringRunner.class)
public class GroupeServiceTest {

    @InjectMocks
    private GroupServiceImpl groupeService;
    @Mock
    private GroupRepository groupeRepository;


    @Before
    public void setUp() {

        Groupe groupe = new Groupe();
        groupe.setName("groupe de test");
        groupe.setId(1L);
        Groupe groupe1 = new Groupe();
        groupe1.setName("groupe de test2");
        groupe1.setId(2L);

        Mockito.when(groupeRepository.
                findById(groupe.getId())).thenReturn(Optional.of(groupe));

        List<Groupe> groupes = new ArrayList<Groupe>();
        groupes.add(groupe1);
        groupes.add(groupe);

        Mockito.when(groupeRepository.
                findAll()).thenReturn(groupes);
    }


    @Test
    public void updateGroupe() {

        Groupe groupe = new Groupe();

        given(groupeRepository.save(groupe)).willReturn(groupe);
        groupe.setId(2L);
        groupe.setName("g2");
        // when -  action or the behaviour that we are going test
        Groupe updatedGroupe = groupeService.update(groupe, 1L);

        // then - verify the output
        assertThat(updatedGroupe.getId()).isEqualTo(2L);
        assertThat(updatedGroupe.getName()).isEqualTo("g2");
    }

    @Test
    public void deleteGrpoupe(){

        // given - precondition or setup
        long groupeId = 1L;

        willDoNothing().given(groupeRepository).deleteById(groupeId);

        // when -  action or the behaviour that we are going test
        groupeService.delete(groupeId);

        // then - verify the output
        verify(groupeRepository, times(1)).deleteById(groupeId);
    }

    @Test
    public void getAllGroupes() {

        List<Groupe> groupes = groupeService.findAll();
        Assert.assertEquals(groupes.size(), 2);
    }
}

