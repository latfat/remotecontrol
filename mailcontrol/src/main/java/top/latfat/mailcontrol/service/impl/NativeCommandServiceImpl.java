package top.latfat.mailcontrol.service.impl;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import top.latfat.mailcontrol.cmdexec.NativeCommandExecute;
import top.latfat.mailcontrol.service.NativCommandService;

@Service
public class NativeCommandServiceImpl implements NativCommandService {

	private static final Logger logger = LoggerFactory.getLogger(NativeCommandServiceImpl.class);
	
	@Resource
	private NativeCommandExecute execute;

	@Override
	public void shuttingDown(String time) throws Throwable {
		logger.info("正在关机...");
		execute.exec(null, "shutdown now");
	}

	@Override
	public void cmd(PrintWriter out, String command) throws Throwable {
		logger.info("控制台启动...");
		execute.exec(out, command);
	}

	@Override
	public NativeCommandExecute getExecute() {
		return execute;
	}
	
}
