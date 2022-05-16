package com.capgemini.capfoot.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	@Lazy
	GroupTeamService groupTeamService;

	public void sendEmailAfterInscription(Team team) throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();
		boolean multipart = true;
		MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

		List<String> toList = new ArrayList<String>();
		List<String> ccList = new ArrayList<String>();

		List<Player> players = team.getPlayers();
		for (Player player : players) {
			if (player.isCaptain()) {
				toList.add(player.getEmailAddress());
			} else {
				ccList.add(player.getEmailAddress());
			}
		}

		String[] toArray = new String[toList.size()];
		String[] ccArray = new String[ccList.size()];
		helper.setTo(toList.toArray(toArray));
		helper.setCc(ccList.toArray(ccArray));

		helper.setSubject("Félicitations ! Votre equipe est inscrit au tournoi Cap du monde.");
		message.setContent(buildEmailAfterRegistration(team), "text/html");
		emailSender.send(message);

	}

	public void sendEmailQualifiedTeams(List<Team> teams, Championship_State champSt) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		boolean multipart = true;
		MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

		List<String> toList = new ArrayList<String>();
		List<String> ccList = new ArrayList<String>();

		for (Team team : teams) {
			List<Player> players = team.getPlayers();
			for (Player player : players) {
				if (player.isCaptain()) {
					toList.add(player.getEmailAddress());
				} else {
					ccList.add(player.getEmailAddress());
				}
			}
			String[] toArray = new String[toList.size()];
			String[] ccArray = new String[ccList.size()];
			helper.setTo(toList.toArray(toArray));
			helper.setCc(ccList.toArray(ccArray));
			helper.setSubject("Félicitations! Votre equipe a été qualifié à la prochaine phase: " + champSt);
			message.setContent(buildEmailToQualifiedTeams(team.getName(), champSt), "text/html");
			emailSender.send(message);
		}

	}

	public void sendEmailChampNewsToAll(List<Team> teams, Championship_State champSt) throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();
		boolean multipart = true;
		MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

		List<String> toList = new ArrayList<String>();
		List<String> ccList = new ArrayList<String>();
		for (Team team : teams) {
			List<Player> players = team.getPlayers();
			for (Player player : players) {
				if (player.isCaptain()) {
					toList.add(player.getEmailAddress());
				} else {
					ccList.add(player.getEmailAddress());
				}
			}
		}
		String[] toArray = new String[toList.size()];
		String[] ccArray = new String[ccList.size()];
		helper.setTo(toList.toArray(toArray));
		helper.setCc(ccList.toArray(ccArray));

		helper.setSubject("Actualités du tournoi Cap du Monde: Phase de " + champSt);
		message.setContent(buildEmailStateChanged(champSt), "text/html");
		emailSender.send(message);

	}

	public String buildEmailToQualifiedTeams(String teamName, Championship_State champSt) {
		StringBuilder emailBody = new StringBuilder();
		emailBody.append(
				"<table class=\"container\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"750\"><tbody><tr><td>\r\n"
						+ "<table cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><img src=\"https://builders.capgemini.com/upload/template_images/373C154C-7BE4-E87B-7155-A2850E95962D/user_banner/BannerCapdumonde2022V2.jpg\" border=\"0\" width=\"750\"></td></tr><tr><td>&nbsp;</td></tr></tbody></table>\r\n"
						+ "<!--##question_tags##-->\r\n"
						+ "<!--##NEW-ELEMENTS-SPACE-START##--><!--MULTILINE-START--><table cellpadding=\"0\" cellspacing=\"0\" class=\"comm_element\"><tbody><tr><td><div class=\"no_question\" id=\"div_multiline_1\"><p style=\"text-align:center\"><span style=\"color:rgb(0,112,173)\"><span style=\"font-size:16px\"><strong>Passage à la prochaine phase du tournoi Cap du Monde!</strong></span></span></p>\r\n"
						+ "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
						+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">"
						+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">Chers membres de l'équipe <span style=\"color:#0070AD\"><strong>"
						+ teamName + " </strong></span>,<br><br> Félicitations, vous etes qualifies à la phase de \""
						+ champSt + "\" du tournoi Cap du Monde! </b> </span></p>\r\n" + "<ul>");

		emailBody.append(
				"<p style=\"text-align:justify\"><span style=\"font-size:14px\">N'hésitez pas à inviter vos collegues aux Match sur les terrains à <span style=\"color:#0070AD\"><strong>Rabat</strong></span> et à <span style=\"color:#0070AD\"><strong>Casablanca</strong></span>.</p>\r\n"
						+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
						+ "<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">\r\n"
						+ "		<tbody><tr>\r\n"
						+ "			<td style=\"text-align:center\"><strong>ACCEDER AUX TERRAINS DE RABAT</strong></td>\r\n"
						+ "			<td style=\"text-align:center\"><strong>ACCEDER TERRAINS DE CASABLANCA</strong></td>\r\n"
						+ "		</tr>\r\n" + "		<tr>\r\n"
						+ "			<td style=\"text-align:center\"><a href=\"https://www.google.com/maps/place/Foot+Premium/@33.9730349,-6.7351934,18.46z/data=!4m5!3m4!1s0xda741408325d5f5:0x8a2f133b9cc3f29d!8m2!3d33.9729876!4d-6.7347674\" target=\"_blank\"><img alt=\"\" src=\"https://builders.capgemini.com/upload/user_images/B6282260-BEAE-6BD9-3AF5-0ADF0817F06C/foot_premium_rabat.jpg\" style=\"height:68px; width:150px\" width=\"150\" height=\"68\"></a></td>\r\n"
						+ "			<td style=\"text-align:center\"><a href=\"https://www.google.com/maps/place/Green+Sports+Park/@33.4795654,-7.6385106,17z/data=!3m1!4b1!4m5!3m4!1s0xda62d9b0dfb9975:0xde8f2002e0d6869b!8m2!3d33.4795609!4d-7.6363219\" target=\"_blank\"><img alt=\"\" src=\"https://builders.capgemini.com/upload/user_images/B6282260-BEAE-6BD9-3AF5-0ADF0817F06C/green_park_bouskoura.jpg\" style=\"height:75px; width:150px\" width=\"150\" height=\"75\"></a></td>\r\n"
						+ "		</tr>\r\n" + "	\r\n" + "</tbody></table>\r\n" + "\r\n"
						+ "<p><span style=\"font-size:14px\"><strong>Pour en savoir +</strong></span></p>\r\n" + "\r\n"
						+ "<ul>\r\n" + "	<li>\r\n"
						+ "	<p><a href=\"mailto:hamza.maroubi@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Hamza Maroubi - SPOC Casablanca</span></span></a></p>\r\n"
						+ "	</li>\r\n" + "	<li>\r\n"
						+ "	<p><a href=\"mailto:younes.el-harim@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Younes El Harim - SPOC Rabat</span></span></a></p>\r\n"
						+ "	</li>\r\n" + "	<li>\r\n"
						+ "	<p><a href=\"mailto:mehdi.elkhomsi@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Mehdi ELKHOMSI - Chargé de communication</span></span></a></p>\r\n"
						+ "	</li>" + "</ul>\r\n"
						+ "</div></td></tr></tbody></td></tr></tbody></table></td></tr></tbody></table>");
		return String.valueOf(emailBody);
	}

	public String buildEmailAfterRegistration(Team team) {
		StringBuilder emailBody = new StringBuilder();
		emailBody.append(
				"<table class=\"container\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"750\"><tbody><tr><td>\r\n"
						+ "<table cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><img src=\"https://builders.capgemini.com/upload/template_images/373C154C-7BE4-E87B-7155-A2850E95962D/user_banner/BannerCapdumonde2022V2.jpg\" border=\"0\" width=\"750\"></td></tr><tr><td>&nbsp;</td></tr></tbody></table>\r\n"
						+ "<!--##question_tags##-->\r\n"
						+ "<!--##NEW-ELEMENTS-SPACE-START##--><!--MULTILINE-START--><table cellpadding=\"0\" cellspacing=\"0\" class=\"comm_element\"><tbody><tr><td><div class=\"no_question\" id=\"div_multiline_1\"><p style=\"text-align:center\"><span style=\"color:rgb(0,112,173)\"><span style=\"font-size:16px\"><strong>Votre inscription au tournoi Cap du Monde !</strong></span></span></p>\r\n"
						+ "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
						+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">"
						+ "Félicitations, votre inscription au tournoi Cap du Monde a bien été prise en compte !&nbsp;</span></p>\r\n"
						+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">Votre equipe <span style=\"color:#0070AD\"><strong>"
						+ team.getName()
						+ " </strong></span> a été bien inscripts au tournoi Cap du Monde, votre équipe se constitue de 7 joueurs: </b> </span></p>\r\n"
						+ "<ul>");

		for (Player player : team.getPlayers()) {
			emailBody.append("<li> <span style=\"color:#0070AD\"><strong>" + player.getFullName()
					+ " </strong></span>: " + player.getEmailAddress() + " </li></b>");
		}

		emailBody.append("</ul>"
				+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">N'hésitez pas à inviter vos collegues aux Match sur les terrains à <span style=\"color:#0070AD\"><strong>Rabat</strong></span> et à <span style=\"color:#0070AD\"><strong>Casablanca</strong></span>.</p>\r\n"
				+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
				+ "<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">\r\n"
				+ "		<tbody><tr>\r\n"
				+ "			<td style=\"text-align:center\"><strong>ACCEDER AUX TERRAINS DE RABAT</strong></td>\r\n"
				+ "			<td style=\"text-align:center\"><strong>ACCEDER TERRAINS DE CASABLANCA</strong></td>\r\n"
				+ "		</tr>\r\n" + "		<tr>\r\n"
				+ "			<td style=\"text-align:center\"><a href=\"https://www.google.com/maps/place/Foot+Premium/@33.9730349,-6.7351934,18.46z/data=!4m5!3m4!1s0xda741408325d5f5:0x8a2f133b9cc3f29d!8m2!3d33.9729876!4d-6.7347674\" target=\"_blank\"><img alt=\"\" src=\"https://builders.capgemini.com/upload/user_images/B6282260-BEAE-6BD9-3AF5-0ADF0817F06C/foot_premium_rabat.jpg\" style=\"height:68px; width:150px\" width=\"150\" height=\"68\"></a></td>\r\n"
				+ "			<td style=\"text-align:center\"><a href=\"https://www.google.com/maps/place/Green+Sports+Park/@33.4795654,-7.6385106,17z/data=!3m1!4b1!4m5!3m4!1s0xda62d9b0dfb9975:0xde8f2002e0d6869b!8m2!3d33.4795609!4d-7.6363219\" target=\"_blank\"><img alt=\"\" src=\"https://builders.capgemini.com/upload/user_images/B6282260-BEAE-6BD9-3AF5-0ADF0817F06C/green_park_bouskoura.jpg\" style=\"height:75px; width:150px\" width=\"150\" height=\"75\"></a></td>\r\n"
				+ "		</tr>\r\n" + "	\r\n" + "</tbody></table>\r\n" + "\r\n"
				+ "<p><span style=\"font-size:14px\"><strong>Pour en savoir +</strong></span></p>\r\n" + "\r\n"
				+ "<ul>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:hamza.maroubi@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Hamza Maroubi - SPOC Casablanca</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:younes.el-harim@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Younes El Harim - SPOC Rabat</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:mehdi.elkhomsi@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Mehdi ELKHOMSI - Chargé de communication</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "</ul>\r\n"
				+ "</div></td></tr></tbody></td></tr></tbody></table></td></tr></tbody></table>");
		return String.valueOf(emailBody);
	}

	public String buildEmailStateChanged(Championship_State champState) throws MessagingException {
		List<Team> teams = new ArrayList<Team>();
		StringBuilder emailBody = new StringBuilder();
		emailBody.append(
				"<table class=\"container\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"750\"><tbody><tr><td>\r\n"
						+ "<table cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><img src=\"https://builders.capgemini.com/upload/template_images/373C154C-7BE4-E87B-7155-A2850E95962D/user_banner/BannerCapdumonde2022V2.jpg\" border=\"0\" width=\"750\"></td></tr><tr><td>&nbsp;</td></tr></tbody></table>\r\n"
						+ "<!--##question_tags##-->\r\n"
						+ "<!--##NEW-ELEMENTS-SPACE-START##--><!--MULTILINE-START--><table cellpadding=\"0\" cellspacing=\"0\" class=\"comm_element\"><tbody><tr><td><div class=\"no_question\" id=\"div_multiline_1\"><p style=\"text-align:center\"><span style=\"color:rgb(0,112,173)\"><span style=\"font-size:16px\"><strong>Actualités du tournoi Cap du Monde: La phase de "
						+ champState + ".</strong></span></span></p>" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n"
						+ "<p style=\"text-align:center\"><span style=\"color:#ffffff\"><strong><span style=\"font-size:14px\"><span style=\"background-color:#0070ad\">La 4ème édition du championnat de football <br> &nbsp;Capgemini TS Maroc a démarré !</span></span></strong></span></p>\r\n"
						+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n");
		emailBody.append(
				"<p style=\"text-align:justify\"><span style=\"font-size:14px\">Nous félicitons les equipes qualifiés à la phase de "
						+ "<span style=\"color:#0070AD\"><strong>\"" + champState + "\"</strong></span>, qui sont: "
						+ "<ul>");

		if (champState == Championship_State.LAST_SIXTEEN) {
			teams = groupTeamService.qualifiedTeamsToLastSixteen();
		} else if (champState == Championship_State.QUART_FINAL) {
			teams = groupTeamService.qualifiedTeamsToQuarterFinals();
		} else if (champState == Championship_State.DEMI_FINAL) {
			teams = groupTeamService.qualifiedTeamsToSemiFinals();
		} else if (champState == Championship_State.FINAL) {
			teams = groupTeamService.qualifiedTeamsToFinals();
		}

		for (Team team : teams) {
			emailBody.append(
					"<li> L'equipe <span style=\"color:#0070AD\"><strong>" + team.getName() + "</strong></span></li>");
		}
		emailBody.append("</ul></span></p>\r\n"
				+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">N'hésitez pas à venir les encourager sur les terrains de <span style=\"color:#0070AD\"><strong>Rabat et Casablanca à partir de 16h15</strong></span>.</p>\r\n"
				+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
				+ "<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">\r\n" + "	\r\n"
				+ "		<tbody><tr>\r\n"
				+ "			<td style=\"text-align:center\"><strong>ACCEDER AUX TERRAINS DE RABAT</strong></td>\r\n"
				+ "			<td style=\"text-align:center\"><strong>ACCEDER TERRAINS DE CASABLANCA</strong></td>\r\n"
				+ "		</tr>\r\n" + "		<tr>\r\n"
				+ "			<td style=\"text-align:center\"><a href=\"https://www.google.com/maps/place/Foot+Premium/@33.9730349,-6.7351934,18.46z/data=!4m5!3m4!1s0xda741408325d5f5:0x8a2f133b9cc3f29d!8m2!3d33.9729876!4d-6.7347674\" target=\"_blank\"><img alt=\"\" src=\"https://builders.capgemini.com/upload/user_images/B6282260-BEAE-6BD9-3AF5-0ADF0817F06C/foot_premium_rabat.jpg\" style=\"height:68px; width:150px\" width=\"150\" height=\"68\"></a></td>\r\n"
				+ "			<td style=\"text-align:center\"><a href=\"https://www.google.com/maps/place/Green+Sports+Park/@33.4795654,-7.6385106,17z/data=!3m1!4b1!4m5!3m4!1s0xda62d9b0dfb9975:0xde8f2002e0d6869b!8m2!3d33.4795609!4d-7.6363219\" target=\"_blank\"><img alt=\"\" src=\"https://builders.capgemini.com/upload/user_images/B6282260-BEAE-6BD9-3AF5-0ADF0817F06C/green_park_bouskoura.jpg\" style=\"height:75px; width:150px\" width=\"150\" height=\"75\"></a></td>\r\n"
				+ "		</tr>\r\n" + "	\r\n" + "</tbody></table>\r\n" + "\r\n"
				+ "<p><span style=\"font-size:14px\"><strong>Pour en savoir +</strong></span></p>\r\n" + "\r\n"
				+ "<ul>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:hamza.maroubi@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Hamza Maroubi - SPOC Casablanca</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:younes.el-harim@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Younes El Harim - SPOC Rabat</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "	<li>\r\n"
				+ "	<p><a href=\"mailto:mehdi.elkhomsi@capgemini.com\"><span style=\"color:#12abdb\"><span style=\"font-size:14px\">Contacter Mehdi ELKHOMSI - Chargé de communication</span></span></a></p>\r\n"
				+ "	</li>\r\n" + "</ul>\r\n"
				+ "</div></td></tr></tbody></td></tr></tbody></table></td></tr></tbody></table>");

		return String.valueOf(emailBody);
	}
}
