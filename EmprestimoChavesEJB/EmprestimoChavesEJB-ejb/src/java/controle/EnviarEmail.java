package controle;

import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author dflenzi
 */
@Singleton
public class EnviarEmail{

    private Session getMailgmailSessao() throws NamingException {
        Context c = new InitialContext();
        return (Session) c.lookup("mail/gMailSessao");
    }

    private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        Session mailgmailSessao = getMailgmailSessao();
        MimeMessage message = new MimeMessage(mailgmailSessao);
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        message.setText(body);        
        Transport.send(message);
    }

    
    public void enviarEmail(String destino, String assunto, String corpo) throws Exception {
        sendMail(destino, assunto, corpo);
    }
}
