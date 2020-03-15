package top.lsyweb.hosadm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.mapper.AdminMapper;
import top.lsyweb.hosadm.service.AdminService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
@Controller
@RestController
public class AdminController
{
	@Autowired
	private AdminService adminService;

	/**
	 * 登录逻辑验证
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/loginSubmit")
	public Map<String, Object> loginSubmit(String username, String password)
	{
		Map<String, Object> map = new HashMap<>();
		// 获取Subject
		Subject subject = SecurityUtils.getSubject();
		// 封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 执行判定逻辑
		int code;
		String msg;
		String url = "";
		try
		{
			subject.login(token);
			// 登录成功
			code = 0;
			msg = "登录成功";
			// 根据角色设置跳转路径，
			Admin admin = (Admin)subject.getPrincipal();
			if (admin.getAdminType().equals("admin:record"))
				url = "/views/record/frontindex.html";
			else
				url = "/views/assign/index.html";
			// 将用户放入shiro的session中
			subject.getSession().setAttribute("admin", admin);
		}
		catch (UnknownAccountException e)
		{
			code = -1;
			msg = "用户名不存在";
		}
		catch (IncorrectCredentialsException e)
		{
			code = -1;
			msg = "密码错误";
		}
		map.put("code", code);
		map.put("msg", msg);
		map.put("url", url);

		return map;
	}

	/**
	 * 获取用户信息
	 * @return
	 */
	@GetMapping("/adminInfo")
	public Map<String, Object> adminInfo()
	{
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin)subject.getPrincipal();

		// 登录者的密码不会包装
		if (admin != null)
		{
			admin.setAdminPassword("");
			map.put("admin", admin);
			map.put("code", 0);
		}
		else
		{
			map.put("code", -1);
			map.put("msg", "获取用户信息失败");
		}

		return map;
	}

	/**
	 * 更改用户信息
	 * @return
	 */
	@PostMapping("/adminChange")
	public Map<String, Object> adminChange(Admin admin)
	{
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();
		// 如果当前用户还未登录，直接返回
		if (sessionAdmin == null)
			return null;

		// 将sessionAdmin中的用户id赋值给表单中的admin
		admin.setAdminId(sessionAdmin.getAdminId());

		// 执行修改信息操作
		Admin result = adminService.changeAdminById(admin);
		// 维护shiro中的admin对象，这里不可能出问题，所以没加异常判断
		if (result != null)
		{
			sessionAdmin.setAdminPhone(result.getAdminPhone());
			sessionAdmin.setAdminDescribe(result.getAdminDescribe());
			sessionAdmin.setAdminSex(result.getAdminSex());
			sessionAdmin.setAdminLastEditTime(result.getAdminLastEditTime());
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * 接收用户上传的图片
	 * @param file
	 * @return
	 */
	@PostMapping("/adminUpload")
	public Map<String, Object> adminUpload(MultipartFile file)
	{
		Map<String, Object> map = new HashMap<>();
		Admin admin = new Admin();
		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();
		// 如果当前用户还未登录，直接返回
		if (sessionAdmin == null)
			return null;
		// 文件判空
		if (file == null)
			return null;

		// 将sessionAdmin中的用户id赋值给空admin
		admin.setAdminId(sessionAdmin.getAdminId());
		// 将sessionAdmin中的旧src赋值给admin，方便service层删除本地图片
		admin.setAdminSrc(sessionAdmin.getAdminSrc());

		// 执行admin头像更改操作
		Admin result = adminService.changeAdminSrcById(admin, file);
		// 维护shiro中的admin对象，这里头像更改可能出问题
		if (result != null)
		{
			sessionAdmin.setAdminSrc(result.getAdminSrc());
			sessionAdmin.setAdminLastEditTime(result.getAdminLastEditTime());
			map.put("code", 0);
		}
		return map;
	}

	/**
	 * admin修改密码
	 * @return
	 */
	@PostMapping("/adminChangePassword")
	public Map<String, Object> adminChangePassword(String oldPassword, String password)
	{
		Map<String, Object> map = new HashMap<>();
		Admin admin = new Admin();
		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();
		// 如果当前用户还未登录，直接返回
		if (sessionAdmin == null)
			return null;
		// 判空
		int code = 0;
		String msg = "";
		if (password != null && oldPassword != null)
		{
			// 获取数据库中的密码
			String realPassword = adminService.findAdminById(sessionAdmin.getAdminId().intValue()).getAdminPassword();
			// 如果旧密码不对
			System.out.println(oldPassword+ "  " + realPassword);
			if (!realPassword.equals(oldPassword))
			{
				code = -1;
				msg = "密码错误，请重新输入";
			}
			// 旧密码正确
			else
			{
				// 将sessionAdmin中的用户id赋值给空admin
				admin.setAdminId(sessionAdmin.getAdminId());
				// 将新密码赋值给空admin
				admin.setAdminPassword(password);
				// 执行修改密码操作
				Admin result = adminService.changeAdminById(admin);
				// 维护sessionAdmin的信息
				sessionAdmin.setAdminLastEditTime(result.getAdminLastEditTime());
			}
		}
		else
		{
			code = -2;
			msg = "密码不能为空";
		}

		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
}
