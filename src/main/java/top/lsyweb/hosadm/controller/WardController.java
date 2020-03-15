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

//		Map<Integer, Map<Integer, Bed>> mapMap = wardService.queryWardsBeds(departmentId.intValue());
		List<Ward> wards = wardService.queryWardsBeds(departmentId.intValue());

		map.put("data", wards);
		return map;
	}
}
