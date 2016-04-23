package top.latfat.mailcontrol;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.latfat.mailcontrol.util.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Main.class)
public class TestMail {
	
	private Logger logger = LoggerFactory.getLogger(TestMail.class);

	@Resource
	private MailSender sender;
	
	@Resource
	private Store store;
	
	@Test
	public void testMailSender() throws MessagingException {
		System.out.println(sender);
		JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) sender;
		javaMailSenderImpl.testConnection();
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setTo("xiaochenyoo@sina.com");
		helper.setFrom(javaMailSenderImpl.getUsername());
		helper.setText("你好，测试邮件服务器！");
		helper.setSubject("测试邮件");
		javaMailSenderImpl.send(mimeMessage);
	}
	
	@Test
	public void testMailReader() throws MessagingException, IOException {
		Folder folder = store.getFolder("inbox");
		folder.open(Folder.READ_WRITE);
		logger.info(folder.toString());
		Message[] messages = folder.getMessages();
		logger.info("messages length:" + messages.length);
		boolean flag = true;
		for (Message message : messages) {
			logger.info("message hashcode:" + message.hashCode());
			message.writeTo(System.out);
			String msg = (String) message.getContent();
			System.out.println(msg);
			logger.info("message hashcode:" + message.hashCode());
//			if (flag) {
//				message.setFlag(Flags.Flag.DELETED, flag);
//				flag = false;
//			}
		}
		folder.close(true);
		//store.close();
		//testMailReader();
	}
}
