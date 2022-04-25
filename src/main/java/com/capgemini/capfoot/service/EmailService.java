package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Team;

import java.util.List;

public interface EmailService {

    public void sendEmail(Team toAddress, String subject, String message);
}
