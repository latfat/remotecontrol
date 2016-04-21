package top.latfat.mailcontrol.service;

import javax.servlet.http.HttpSession;

import top.latfat.mailcontrol.entity.Result;

/**
 * 分装网站服务
 * @author Administrator
 *
 */
public interface SiteService extends SiteBaseService {

	/**
	 * Hello World!
	 * @return
	 */
	Result sayHello();

	/**
	 * 向会话中的命令行进程发送一条命令
	 * @param command
	 * @param session
	 * @return
	 */
	Result sendCommand(String command, HttpSession session);

}
