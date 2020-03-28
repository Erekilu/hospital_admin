package top.lsyweb.hosadm.controller;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.domain.Log;
import top.lsyweb.hosadm.dto.LogExecution;
import top.lsyweb.hosadm.service.LogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-16
 */
@Controller
@RestController
public class LogController
{
	@Autowired
	private LogService logService;

	/**
	 * 获取日志信息
	 * @param page 页号
 	 * @param limit 每页条数
	 * @param type 查询my_log还是all_log
	 * @return 满足条件的日志信息
	 */
	@GetMapping("/getLog")
	public Map<String, Object> getMyLog(int page, int limit, String type)
	{
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();

		if ("my_log".equals(type))
		{
			// 查询当前登录用户的所有日志
			LogExecution myLog = logService.findMyLog(sessionAdmin.getAdminId(), page, limit);
			map.put("data", myLog.getLogList());
			map.put("count", myLog.getLogCount());
		}
		else
		{
			// 查询当前登录用户所在部门的所有日志
			LogExecution allLog = logService.findAllLog(sessionAdmin.getAdminBelong(), page, limit);
			map.put("data", allLog.getLogList());
			map.put("count", allLog.getLogCount());
		}
		map.put("msg", "");
		map.put("code", 0);

		return map;
	}
}
