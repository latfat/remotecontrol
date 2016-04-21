package top.latfat.mailcontrol.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import top.latfat.mailcontrol.entity.Result;
import top.latfat.mailcontrol.service.SiteBaseService;
import top.latfat.mailcontrol.util.JSON;

/**
 * 全局异常处理
 * @author zjb
 * @param 2016/3/16 16:31
 * 
 */
public class CustomizedExceptionResolver implements HandlerExceptionResolver,SiteBaseService {

	private static final Logger logger = LoggerFactory.getLogger(CustomizedExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception e) {
		logger.error(e.getMessage());
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(JSON.toJsonString(result.setAll(Result.ERROR, "系统繁忙", null)));
			out.flush();
			out.close();
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}
		return null;
	}


}
