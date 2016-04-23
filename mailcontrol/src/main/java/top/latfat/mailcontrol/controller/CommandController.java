package top.latfat.mailcontrol.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.latfat.mailcontrol.domain.Result;
import top.latfat.mailcontrol.service.NativCommandService;
import top.latfat.mailcontrol.service.SiteService;

/**
 * 连接本地控制台
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
 * 2016-4-21 下午09:15:05
 */
@Controller
public class CommandController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommandController.class);
	
	/**
	 * 打开CMD命令服务进程
	 */
	private static final String CMD = "/cmd";
	
	/**
	 * CMD命令传输
	 */
	private static final String COMMAND = "/command";
	
	@Resource
	private NativCommandService cmdService;
	
	@Resource
	private SiteService service;

	@RequestMapping(CMD)
	public void cmd(HttpServletResponse response, HttpSession session) throws Throwable {
		logger.debug("前端获取本地命令行执行进程");
		session.setAttribute("cmd", cmdService.getExecute());
		PrintWriter writer = response.getWriter();
		service.writeCMDHeadFile(writer);
		cmdService.cmd(writer, null);
	}
	
	
	@RequestMapping(COMMAND)
	@ResponseBody
	public Result command(String command, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
		logger.debug("前端发来一条命令");
		return service.sendCommand(command, session);
	}
}
