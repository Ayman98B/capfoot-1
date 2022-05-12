package com.capgemini.capfoot.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	public void sendEmailToAllPlayers(List<Team> teams, String subject) throws MessagingException {

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

		helper.setSubject(subject);
		message.setContent(buildEmailBodySimple(), "text/html");
		emailSender.send(message);

	}

	public void sendEmail(String adress, String subject) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();

		boolean multipart = true;

		MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
		message.setContent(buildEmailBodySimple(), "text/html");

		String[] cc = new String[6];
		cc[0] = "mouad.lafnoune1403@gmail.com";
		cc[1] = "anassallabou@gmail.com";
		cc[2] = "ayman.8.belahcen@gmail.com";
		cc[3] = "hajarsellami97@gmail.com";
		cc[4] = "Abdessamad.njr@gmail.com";
		cc[5] = "o.intissar@mundiapolis.ma";

		helper.setCc(cc);
		helper.setTo(adress);
		helper.setSubject("Last test");
		this.emailSender.send(message);

	}

	public String buildEmailBodySimple() {
		StringBuilder emailBody = new StringBuilder();
		emailBody.append(
				"<table class=\"container\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"750\"><tbody><tr><td>\r\n"
						+ "<table cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><img src=\"https://builders.capgemini.com/upload/template_images/373C154C-7BE4-E87B-7155-A2850E95962D/user_banner/BannerCapdumonde2022V2.jpg\" border=\"0\" width=\"750\"></td></tr><tr><td>&nbsp;</td></tr></tbody></table>\r\n"
						+ "<!--##question_tags##-->\r\n"
						+ "<!--##NEW-ELEMENTS-SPACE-START##--><!--MULTILINE-START--><table cellpadding=\"0\" cellspacing=\"0\" class=\"comm_element\"><tbody><tr><td><div class=\"no_question\" id=\"div_multiline_1\"><p style=\"text-align:center\"><span style=\"color:rgb(0,112,173)\"><span style=\"font-size:16px\"><strong>Le tournoi Cap du Monde a signé son grand retour cette année&nbsp;!</strong></span></span></p>\r\n"
						+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
						+ "<p style=\"text-align:center\"><span style=\"color:#ffffff\"><strong><span style=\"font-size:14px\"><span style=\"background-color:#0070ad\">La 4ème édition du championnat de football <br> &nbsp;Capgemini TS Maroc a démarré !</span></span></strong></span></p>\r\n"
						+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
						+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">Cette année encore, le début du mois de Ramadan a marqué le début du tournoi de football Cap du Monde qui voit s'affronter des équipes de collaborateurs Casaouis et Rbatis.&nbsp;&nbsp;</span></p>\r\n"
						+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
						+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">Répartis en équipes de 7 joueurs, les 16 équipes de Casablanca finalisent cette semaine la phase de poule à l'issue de laquelle nous aurons <span style=\"color:#0070AD\"><strong>les équipes qualifiées aux quarts de finale</strong></span>.</span></p>\r\n"
						+ "\r\n"
						+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">Les 7 équipes de Rabat ont quant à elles vu triompher à l'issue de la phase de groupe les équipes <span style=\"color:#0070AD\"><strong>TOTEM, ODIGO FC, BYTEL et CAP UNITED</strong></span>&nbsp;qui s'affronteront en fin de semaine pour <span style=\"color:#0070AD\"><strong>les demi-finales</strong></span>. Nous saluons le talent de <span style=\"color:#0070AD\"><strong>Mouhcine Souaada de l'équipe TOTEM</strong></span> qui a inscrit 11 des 17 buts grâce à l'effort collectif de son équipe.</span></p>\r\n"
						+ "\r\n"
						+ "<p style=\"text-align:justify\"><span style=\"font-size:14px\">N'hésitez pas à venir les encourager sur les terrains de <span style=\"color:#0070AD\"><strong>Rabat à partir de 16h15</strong> <strong>vendredi 15 pour les demi-finales et mercredi 20 pour la finale et la 3ème place</strong></span>. Pour soutenir les équipes de <span style=\"color:#0070AD\"><strong>Casablanca</strong></span>, elles s'affrontent quant à elles les <span style=\"color:#0070AD\"><strong>lundi, mercredi et jeudi à partir de 17h</strong></span>.</span></p>\r\n"
						+ "\r\n" + "<p style=\"text-align:justify\">&nbsp;</p>\r\n" + "\r\n"
						+ "<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">\r\n"
						+ "	\r\n" + "		<tbody><tr>\r\n"
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
