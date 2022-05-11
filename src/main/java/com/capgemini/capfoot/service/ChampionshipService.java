package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Championship;

import java.util.List;

public interface ChampionshipService {

	public List<Championship> getAllChampionships();

	public Championship getChampionshipById(Long idCamp);

	public Championship createChampionship(Championship newChamp);

	public Championship updateChampionship(Championship updateChamp);

	public void deleteChampionship(Long id);

}
