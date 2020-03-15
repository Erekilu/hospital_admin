package top.lsyweb.hosadm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lsyweb.hosadm.domain.Department;
import top.lsyweb.hosadm.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@Controller
public class DepartmentController
{
	@Autowired
	private DepartmentService departmentService;

	@ResponseBody
	@GetMapping("/department")
	public Map<String, Object> department()
	{
		Map<String, Object> map = new HashMap<>();
		List<Department> departments = departmentService.queryDepartments();
		map.put("departments", departments);
		map.put("code", 0);
		return map;
	}
}
