package top.latfat.mailcontrol.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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

	/**
	 * 将控制台展示前端文件写出
	 * @param writer
	 * @return
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	void writeCMDHeadFile(PrintWriter writer) throws IOException;

}
