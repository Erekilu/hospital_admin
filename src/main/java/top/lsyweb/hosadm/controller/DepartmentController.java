package top.lsyweb.hosadm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
public class DepartmentController
{
	@Autowired
	private DepartmentService departmentService;

	/**
	 * 获取所有的部门信息，来渲染前台下拉框
	 * @return 所有部门信息封装成的集合
	 */
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
