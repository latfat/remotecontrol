package top.latfat.mailcontrol.config;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮件配置类
 *
 * @author seanwg
 * 2016-4-23 下午10:38:23
 */
@Configuration
public class MailConfiguration {

	@Value("${mail.host}")
	private String host;
	@Value("${mail.username}")
	private String user;
	@Value("${mail.password}")
	private String pwd;
	@Value("${mail.sendprotocol}")
	private String sendProtocol;
	@Value("${mail.receiveprotocol}")
	private String receiveProtocol;
	
	@Bean
	JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(this.sendProtocol + "." + this.host);
		sender.setUsername(this.user);
		sender.setPassword(this.pwd);
		return sender;
	}
	
	@Bean(destroyMethod="close")
	Store store() throws InterruptedException {
		String host = this.receiveProtocol + "." + this.host;
		Properties properties = new Properties();
		properties.setProperty("mail.store.protocol", receiveProtocol);
		properties.setProperty("mail.pop3.host", host);
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		Store store = null;
		while (store == null) {
			try {
				store = session.getStore();
				store.connect(host, user, pwd);
			} catch (MessagingException e) {
				e.printStackTrace();
				Thread.sleep(3000);
			}
		}
		return store;
	}
}
