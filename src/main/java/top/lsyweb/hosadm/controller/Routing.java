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
	// 测试路由，非必须
	@GetMapping(value = {"/", "/index"})
	public String welcome()
	{
		return "/views/assign/assignbed.html";
	}

	// 登录页面
	@GetMapping("/login")
	public String login()
	{
		return "/views/login.html";
	}

}
