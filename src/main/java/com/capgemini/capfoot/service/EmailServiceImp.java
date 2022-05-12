package com.capgemini.capfoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	public void sendEmailToAllPlayers(List<Team> teams, String subject, String message) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		for (Team team : teams) {
			List<Player> players = team.getPlayers();
			for (Player player : players) {
				if (player.isCaptain()) {
					simpleMailMessage.setTo(player.getEmailAddress());
				} else {
					simpleMailMessage.setCc(player.getEmailAddress());
				}
			}
		}
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(buildEmailBody(teams));
		emailSender.send(simpleMailMessage);
	}

	public String buildEmailBody(List<Team> qualifiedTeams) {
		StringBuilder emailBody = new StringBuilder();
		emailBody.append("<div>\r\n" + "<p style=\"text-align:center\">\r\n"
				+ "<span style=\"color:rgb(0,112,173)\">\r\n" + "<span style=\"font-size:16px\">\r\n"
				+ "<strong>Le tournoi Cap du Monde a signé son grand retour cette année&nbsp;!</strong></span></span></p>\r\n");

		emailBody.append("<p style=\"text-align:justify\">&nbsp;</p>");
		emailBody.append("<p style=\"text-align:center\">\r\n" + "<span style=\"color:#ffffff\">\r\n"
				+ "<strong><span style=\"font-size:14px\">\r\n"
				+ "<span style=\"background-color:#0070ad\">La 5ème édition du championnat de football</span></span></strong></span></p>\r\n");

		emailBody.append("<p style=\"text-align:center\">\r\n" + "<span style=\"color:#ffffff\"><strong>\r\n"
				+ "<span style=\"font-size:14px\"><span style=\"background-color:#0070ad\">de</span></span></strong>\r\n"
				+ "</span><span style=\"color:#F4F4F4\"><strong><span style=\"font-size:14px\">\r\n"
				+ "<span style=\"background-color:#0070ad\">&nbsp;Capgemini TS Maroc a démarré !</span></span></strong></span></p>\r\n");

		emailBody.append("<p style=\"text-align:justify\">&nbsp;</p>");

		emailBody.append("<p style=\"text-align:justify\">\r\n"
				+ "<span style=\"font-size:14px\">Cette année encore, le début du mois de Ramadan a marqué le début du tournoi de football Cap du Monde qui voit s'affronter des équipes de collaborateurs Casaouis et Rbatis.&nbsp;&nbsp;</span></p>\r\n"
				+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n");

		emailBody.append(
				"<p style=\"text-align:justify\"><span style=\"font-size:14px\">Répartis en équipes de 7 joueurs, les 16 équipes de Casablanca finalisent cette semaine la phase de poule à l'issue de laquelle nous aurons <span style=\"color:#0070AD\"><strong>les équipes qualifiées aux quarts de finale</strong></span>.</span></p>\r\n");

		emailBody.append("<p style=\"text-align:justify\">\r\n"
				+ "<span style=\"font-size:14px\">On vous presente les équipes qualifiés à la prochaine phase qui sont: \r"
				+ "<span style=\"color:#0070AD\">\r\n" + "<strong>");

		emailBody.append("team1" + ", team_2" + ", team_3" + " et team_9");

		emailBody.append("</strong>\r\n" + "</span>&nbsp;qui s'affronteront en fin de semaine pour \r\n"
				+ "<span style=\"color:#0070AD\">\r\n");

		emailBody.append("<p>&nbsp;</p>\r\n" + "\r\n"
				+ "<p><span style=\"font-size:14px\"><strong>Pour en savoir +</strong></span></p>\r\n" + "\r\n"
				+ "<ul>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:hamza.maroubi@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Hamza Maroubi - SPOC Casablanca</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:younes.el-harim@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Younes El Harim - SPOC Rabat</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:mehdi.elkhomsi@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Mehdi ELKHOMSI - Chargé de communication</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "</ul>\r\n" + "</div>");

		return String.valueOf(emailBody);
	}

}
