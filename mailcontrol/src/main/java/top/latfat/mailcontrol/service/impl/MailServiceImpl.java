package top.latfat.mailcontrol.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import top.latfat.mailcontrol.service.MailService;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Folder;

@Service
public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Value("${mail.folder}")
	private String mailFolder;
	
	private static Set<String> mailSet = Collections.synchronizedSet(new HashSet<String>());
	
	@Resource
    private JavaMailSenderImpl sender;
	
	@Resource
	private Store store;
	
	@Override
	@Scheduled(fixedRate = 5*1000)
	public void timerLoadMail() throws MessagingException, IOException {
		Folder folder = store.getFolder(mailFolder);
		folder.open(Folder.READ_WRITE);
		Message[] messages = folder.getMessages();
		logger.info("messages length:" + messages.length);
		for (Message message : messages) {
			String uid = null;
			if (folder instanceof POP3Folder) {
				uid = ((POP3Folder) folder).getUID(message);
			} else if (folder instanceof IMAPFolder) {
				IMAPFolder imapFolder = (IMAPFolder) folder;
				uid = String.valueOf(imapFolder.getUID(message));
			} else {
				continue;
			}
			if (mailSet.contains(uid)) {
				continue;
			}
			mailSet.add(uid);
			String msg = (String) message.getContent();
			System.out.println(msg);
		}
		folder.close(true);
	}

	@Override
    public void sendMail(String to, String text, String subject) throws MessagingException {
		MimeMessage mimeMessage = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setTo(to);
		helper.setFrom(sender.getUsername());
		helper.setText(text);
		helper.setSubject(subject);
		sender.send(mimeMessage);
		logger.info("mailto:" + to);
    }
    
}
