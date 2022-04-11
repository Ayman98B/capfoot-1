package com.capgemini.capfoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.capfoot.repository.MatchRepository;
import com.capgemini.capfoot.entity.MatchDisputee;

public class MatchServiceImpl implements MatchService{

	@Autowired
	MatchRepository matchRepository;
	
	@Override
	public List<MatchDisputee> getAllMatchs() {
		return matchRepository.findAll();
	}

	@Override
	public MatchDisputee getMatchById(Long id) {
		return matchRepository.findById(id).get();
	}

	@Override
	public MatchDisputee addMatch(MatchDisputee match) {
		return matchRepository.save(match);
	}

	@Override
	public void updateMatch(Long id, MatchDisputee match) {
		matchRepository.save(match);
	}

	@Override
	public void deleteMatch(Long id) {
		matchRepository.deleteById(id);
		
	}

}
