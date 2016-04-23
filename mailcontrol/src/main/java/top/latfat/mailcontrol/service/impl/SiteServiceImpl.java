package top.latfat.mailcontrol.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import top.latfat.mailcontrol.cmdexec.NativeCommandExecute;
import top.latfat.mailcontrol.entity.Result;
import top.latfat.mailcontrol.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	private static final Logger logger = LoggerFactory.getLogger(SiteServiceImpl.class);

	@Value("${cmdTemplatePath}")
	private String cmdTemplatePath;
	
	@Override
	public Result sayHello() {
		logger.info("someone in home");
		return result;
	}

	@Override
	public Result sendCommand(String command, HttpSession session) {
		NativeCommandExecute execute = (NativeCommandExecute) session.getAttribute("cmd");
		if (execute == null || !execute.isCmdProcessStatus()) {
			logger.info("发送命令失败，该用户没有存活的CMD进程");
			session.removeAttribute("cmd");
			return result.setAll(Result.ERROR, "命令失败", null);
		}
		execute.setCommand(command);
		logger.debug("前端发送命令成功");
		return result.setAll(Result.SUCCESS, null, null);
	}

	@Override
	public void writeCMDHeadFile(PrintWriter writer) throws IOException {
		File file = new File(cmdTemplatePath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String tmp = null;
		while ((tmp = br.readLine()) != null) {
			writer.println(tmp);
		}
		writer.flush();
	} 
}
