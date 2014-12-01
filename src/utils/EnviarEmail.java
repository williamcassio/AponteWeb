package utils;

import java.util.List;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import rn.UsuarioRN;
import entity.Usuario;

public class EnviarEmail {

	public boolean enviarEmail(String anexo, String titulo) {
		boolean bretorno = false;
		String semail = "";
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(anexo);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Planilha de aponte");
		attachment.setName(titulo + ".xlsx");
		MultiPartEmail email = new MultiPartEmail();
		//email.setDebug(true);
		email.setHostName("mail.adm.objetivomao.br");
		/*
		 * email.setHostName("smtp.gmail.com"); email.setSslSmtpPort("465");
		 * email.setSSLOnConnect(true); email.setStartTLSRequired(true);
		 * email.setAuthentication("william.desenv@gmail.com", "delphi256");
		 */

		try {
			UsuarioRN usuarioRN = new UsuarioRN();
			List<Usuario> lista = usuarioRN.listarRecebeEmail();
			if (lista.size() > 0) {
				for (Usuario user : lista) {
					semail = user.getEmail();
					email.addTo(semail);
				}
				email.setFrom("sistema_gestor@uninorte.com.br");
				// email.setFrom("william.desenv@gmail.com");
				email.setSubject(titulo);
				email.setMsg("Em anexo planilha com aponte diário");
				email.attach(attachment);
				email.send();
				bretorno = true;
			}
		} catch (EmailException e) {
			bretorno = false;
			System.out.println(e.getMessage());
		}
		return bretorno;

	}
}
