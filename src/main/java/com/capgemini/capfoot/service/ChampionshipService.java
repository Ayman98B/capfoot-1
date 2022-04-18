package com.capgemini.capfoot.service;

import java.util.List;

import com.capgemini.capfoot.entity.Championship;

public interface ChampionshipService {

	public List<Championship> getAllChampionships();

	public Championship getChampionshipById(Long idCamp);

	public void createChampionship(Championship newChamp);

	public void updateChampionship(Long id,Championship Champion);

	public void deleteChampionship(Long id);

}
