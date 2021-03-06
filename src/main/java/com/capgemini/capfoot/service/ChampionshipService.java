package com.capgemini.capfoot.service;

import java.util.List;

import javax.mail.MessagingException;

import com.capgemini.capfoot.entity.Championship;

public interface ChampionshipService {

	public List<Championship> getAllChampionships();

	public Championship getChampionshipById(Long idCamp);

	public Championship createChampionship(Championship newChamp);

	public Championship updateChampionship(Championship updateChamp) throws MessagingException;

	public void deleteChampionship(Long id);

}
