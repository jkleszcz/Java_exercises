package Lab3.message;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.Properties;

public class EmailMessage {

    private String from; //required (must be e-mail)
    private LinkedList<String> to = new LinkedList<>(); //required at least one (must be e-mail)
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional

    protected EmailMessage(){

    }

    public static Builder builder(){
        return new EmailMessage.Builder();
    }

    public static class Builder{
        EmailMessage email;
        Builder(){
            this.email = new EmailMessage();
        }

        Builder addFrom(String f){
            this.email.from = f;
            return this;
        }

        Builder addTo(String address){
            this.email.to.add(address);
            return this;
        }

        Builder addSubject(String sub){
            this.email.subject = sub;
            return this;
        }

        Builder addContent(String c){
            this.email.content = c;
            return this;
        }

        Builder addMimeType(String mime){
            this.email.mimeType = mime;
            return this;
        }

        Builder addCc(String c){
            this.email.cc.add(c);
            return this;
        }

        Builder addBcc(String bcc){
            this.email.bcc.add(bcc);
            return this;
        }

        EmailMessage build(){
            if(this.email.from != null && !this.email.to.isEmpty())
                return this.email;
            return null;
        }
    }

    public static class SE{
        EmailMessage email;
        private static final String HOST = "poczta.agh.edu.pl";
        private static final int PORT = 465;
        private static final String PASSWORD = "Hipilobo";

        SE(EmailMessage e){
            this.email = e;
        }

        public void send() throws MessagingException{
            Properties props = new Properties();
            props.put("mail.transport.protocol","stmps");
            props.put("mail.stmps.auth","true");

            Session mailSession = Session.getDefaultInstance(props);

            mailSession.setDebug(true);

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(this.email.subject);
            message.setContent(this.email.content,"text/plain; charset=ISO-8859-2");
            for(String a : this.email.to){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(a));
            }

            Transport transport = mailSession.getTransport();
            transport.connect(HOST, PORT, this.email.from, PASSWORD);

            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.close();
        }
    }

    public void send(){
        try{
            new SE(this).send();
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] argv){
        EmailMessage newMail = EmailMessage.builder().addFrom("jkleszcz@student.agh.edu.pl")
                .addTo("jak.kleszcz@gmail.com").addSubject("Wiadomosc probna").addContent("To jest nowa" +
                        "wiadomosc. Jezeli to widzisz to znaczy ze sie udalo").build();
        newMail.send();
    }
}

