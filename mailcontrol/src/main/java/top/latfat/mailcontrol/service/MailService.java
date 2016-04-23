package top.latfat.mailcontrol.service;

import java.io.IOException;

import javax.mail.MessagingException;

/**
 * 邮件服务
// 				    _ooOoo_ 
// 		  	       o8888888o 
// 	  	  	       88" . "88 
//                 (| -_- |) 
//                  O\ = /O 
//              ____/`---*\____ 
//               . * \\| |// `. 
//             / \\||| : |||// \ 
//           / _||||| -:- |||||- \ 
//             | | \\\ - /// | | 
//            | \_| **\---/** | | 
//           \  .-\__ `-` ___/-. / 
//            ___`. .* /--.--\ `. . __ 
//        ."" *< `.___\_<|>_/___.* >*"". 
//      | | : `- \`.;`\ _ /`;.`/ - ` : | | 
//         \ \ `-. \_ __\ /__ _/ .-` / / 
//======`-.____`-.___\_____/___.-`____.-*====== 
// `=---=* 
// 
// ............................................. 
//              佛祖保佑 永无BUG 
 * @author seanwg
 * 2016-4-21 下午11:09:27
 */
public interface MailService {

	/**
	 * 定时获取邮件命令
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	void timerLoadMail() throws MessagingException, IOException;

	/**
	 * 发送邮件
	 * @param to
	 * @param text
	 * @param subject
	 * @throws MessagingException
	 */
	void sendMail(String to, String text, String subject) throws MessagingException;

}