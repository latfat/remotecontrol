package top.latfat.mailcontrol.service;

import java.io.PrintWriter;

import top.latfat.mailcontrol.cmdexec.NativeCommandExecute;

/**
 * 本地命令执行服务
 * @author Administrator
 *
 */
public interface NativCommandService {

	/**
	 * 关机动作，携带时间参数
	 * @param time
	 * @throws Throwable
	 */
	void shuttingDown(String time) throws Throwable;
	
	/**
	 * 
	 * @param out
	 * @param command
	 * @throws Throwable
	 */
	void cmd(PrintWriter out, String command) throws Throwable;

	/**
	 * 获取命令行执行体
	 * @return
	 */
	NativeCommandExecute getExecute();

}