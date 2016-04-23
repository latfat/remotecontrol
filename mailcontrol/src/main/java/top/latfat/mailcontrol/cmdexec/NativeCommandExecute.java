package top.latfat.mailcontrol.cmdexec;

import java.io.PrintWriter;

/**
 * 抽取本地命令执行
 * @author seanwg
 * 2016-4-18 下午09:25:25
 */
public interface NativeCommandExecute {
	
	/**
	 * CMD进程存活
	 */
	static final boolean CMD_PROCESS_LIVE = true;
	/**
	 * CMD进程已超时关闭
	 */
	static final boolean CMD_PROCESS_DETH = false;

	/**
	 * 获取CMD进程服务网页端控制台
	 * @param out
	 * @param command
	 * @throws Throwable
	 */
	void exec(PrintWriter out, String command) throws Throwable;
	
	/**
	 * 发送命令行
	 * @param command
	 */
	public void setCommand(String command);

	/**
	 * CMD进程存活状态
	 * @return
	 */
	public boolean isCmdProcessStatus();

}
