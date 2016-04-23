package top.latfat.mailcontrol.cmdexec.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import top.latfat.mailcontrol.cmdexec.NativeCommandExecute;
/**
 * 系统命令执行体
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
 * 2016-4-21 下午10:23:30
 */
@Component
public class OsCommandExecute implements NativeCommandExecute {

	private static final Logger logger = LoggerFactory.getLogger(OsCommandExecute.class);

	/**
	 * 命令行
	 */
	private String command;
	
	/**
	 * CMD进程状态
	 */
	private boolean cmdProcessStatus;

	/**
	 * CMD回话超时时间
	 */
	@Value("${timeout}")
	private int timeout;

	private synchronized String getCommand() {
		return command;
	}

	@Override
	public synchronized void setCommand(String command) {
		this.command = command;
	}

	@Override
	public boolean isCmdProcessStatus() {
		return cmdProcessStatus;
	}

	private void setCmdProcessStatus(boolean cmdProcessStatus) {
		this.cmdProcessStatus = cmdProcessStatus;
	}

	/**
	 * 返回命令执行结果信息串
	 * 
	 * @param command
	 *            要执行的命令
	 * @throws Throwable
	 *             String[]
	 */
	@Override
	public void exec(PrintWriter out, String command) throws Throwable {
		if (command == null) {
			command =   "echo +------------------------欢迎使用网页控制台----------------------------+";
		}
		Process process = null;
		Runtime runtime = Runtime.getRuntime();

		String osName = System.getProperty("os.name").toLowerCase();
		logger.debug("本机运行<" + osName + ">操作系统...");
		if (osName.indexOf("windows 9") > -1) {
			process = runtime.exec("command.com /c " + command);
		} else if ((osName.indexOf("nt") > -1)
				|| (osName.indexOf("windows 20") > -1)
				|| (osName.indexOf("windows xp") > -1)
				|| (osName.indexOf("windows vista") > -1)
				|| (osName.indexOf("windows 8") > -1)
				|| (osName.indexOf("windows 10") > -1)) {

			/*
			 * 开关/C指明后面跟随的字符串是命令，并在执行命令后关闭DOS窗口，使用cmd /?查看帮助 。./K指明打开后保留该进程
			 */
			process = runtime.exec("cmd.exe /k " + command);
		} else {
			// Linux,Unix
			process = runtime.exec(command);
		}
		// Linux,Unix
		logger.debug("控制台已打开...");

		// 存储返回结果，第一个为标准信息，第二个为错误信息
		InputStream inputStream = process.getInputStream();
		InputStream errorStream = process.getErrorStream();
		new ReadThread(inputStream, out).start();
		new ReadThread(errorStream, out).start();
		OutputStream outputStream = process.getOutputStream();
		PrintWriter outProcess = new PrintWriter(outputStream);
		int time = timeout * 10;
		this.setCmdProcessStatus(CMD_PROCESS_LIVE);
		while (true) {
			String cmd = this.getCommand();
			if (cmd != null) {
				this.setCommand(null);
				//outProcess.println(new String(cmd.getBytes(),System.getProperty("sun.jnu.encoding")));
				//outProcess.flush();
				outputStream.write((cmd + "\n").getBytes(System.getProperty("sun.jnu.encoding")));
				outputStream.flush();
				time = timeout * 10;
			}
			if ("exit".equals(cmd) || time < 0) {
				this.destory(out, outProcess, inputStream, errorStream);
				process.destroy();
				logger.debug("控制台已关闭...");
				this.setCmdProcessStatus(CMD_PROCESS_DETH);
				break;
			}
			logger.debug("等待命令中..." + time);
			time--;
			Thread.sleep(100);
		}
	}

	private void destory(PrintWriter out, PrintWriter outProcess,
			InputStream inputStream, InputStream errorStream)
			throws IOException {
		out.println("<p>已断开连接...</p>");
		out.flush();
		out.close();
		outProcess.close();
		inputStream.close();
		errorStream.close();
	}

	/*
	 * public static void main(String args[]) throws Throwable { Scanner scan =
	 * new Scanner(System.in); //while (true) {
	 * WindowsCommandExecute.exec(scan.nextLine()); //}
	 * 
	 * }
	 */

	/**
	 * 标准流与错误流读取线程
	 */
	public class ReadThread extends Thread {

		private Logger logger = LoggerFactory.getLogger(ReadThread.class);

		private BufferedReader in;

		private PrintWriter out;

		public ReadThread(InputStream is, PrintWriter out)
				throws UnsupportedEncodingException {
			this.in = new BufferedReader(new InputStreamReader(is,
					System.getProperty("sun.jnu.encoding")));
			this.out = out;
			char[] bytes = new char[256];
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = ' ';
			}
			out.println(bytes);
			out.flush();
		}

		public void run() {
			logger.debug("信息输出线程启动...");
			try {
				String result = null;
				while (true) {
					if ((result = in.readLine()) != null && !result.trim().equals("")) {
						result = result.replace("<DIR>", "     ");
						out.println("<p>" + result + "</p>");
						out.flush();
					}
				}

			} catch (Exception e) {
				logger.debug("信息输出线程退出...");
			}
		}
	}

}
