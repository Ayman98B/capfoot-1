package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ChampionshipRepo championshipRepo;

    @Override
    public void addTournament(Championship championship) {
        championshipRepo.save(championship);
    }

    @Override
    public void deleteTournament(Long id) {
        championshipRepo.deleteById(id);
    }

    @Override
    public void updateTournament(Championship championship, Long id) {
        Championship championship1 = championshipRepo.getById(id);
        championship1 = championship;
        championshipRepo.save(championship1);
    }
}
