package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;

public interface GroupTeamService {
    public void addTeamToGroup(Team team, Groupe group);
    public void addWin(Team team, Groupe group);
    public void addLoss(Team team, Groupe group);
    public void addDraw(Team team, Groupe group);


}
