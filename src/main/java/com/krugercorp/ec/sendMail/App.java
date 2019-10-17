package com.krugercorp.ec.sendMail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws AddressException, MessagingException
    {
    	String port = "587";
    	String[] to = {"bpgtoapanta@outlook.com","bpgtoapanta_677@hotmail.com"};
    	String[] cc = null;
    	String subject = "Test de envio";
    	String body = "body del correo de prueba OPu";
    	String user = "testliberty29@gmail.com";
    	String password = "Volrisk01";
       SmtpSendMail.generateAndSendEmail(port, to, cc, subject, body, user, password);
    }
}
