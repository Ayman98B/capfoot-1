package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Groupe;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public void addTournament(Championship championship);
    public void deleteTournament(Long id);
    public void updateTournament(Championship championship, Long id);

}
