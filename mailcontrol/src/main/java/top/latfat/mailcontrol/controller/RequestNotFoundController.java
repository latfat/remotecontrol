package top.latfat.mailcontrol.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.latfat.mailcontrol.entity.Result;

/**
 * 404处理
 * @author Administrator
 *
 */
@RestController
public class RequestNotFoundController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	@RequestMapping(ERROR_PATH)
	public Result handleError() {
		return new Result(Result.ERROR, "404", null);
	}

}
