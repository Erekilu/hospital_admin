package top.lsyweb.hosadm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-06
 */
@Controller
public class Routing
{
	/**
	 * 登录界面路由
	 * @return
	 */
	@GetMapping("/login")
	public String login()
	{
		return "/views/login.html";
	}
}
