package top.lsyweb.hosadm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.domain.Ward;
import top.lsyweb.hosadm.service.WardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-13
 */
@Controller
@RestController
public class WardController
{
	@Autowired
	private WardService wardService;

	/**
	 * 获取当前管理者部门的所有病房及病床信息
	 * @return 满足条件病房病床信息（嵌套）
	 */
	@GetMapping("/getWards")
	public Map<String, Object> getWards()
	{
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();
		if (sessionAdmin == null) {
			return null;
		}
		// 获取当前admin的部门
		Long departmentId = sessionAdmin.getAdminBelong();

		List<Ward> wards = wardService.queryWardsBeds(departmentId);

		map.put("data", wards);
		return map;
	}
}
