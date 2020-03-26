package top.lsyweb.hosadm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.service.BedService;
import top.lsyweb.hosadm.service.DepartmentService;
import top.lsyweb.hosadm.service.PatientService;
import top.lsyweb.hosadm.service.WardService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-22
 */
@Controller
@RestController
public class EchartsController
{
	@Autowired
	private PatientService patientService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private BedService bedService;
	@Autowired
	private WardService wardService;

	@GetMapping("/getData")
	public Map<String, Object> getData()
	{
		Map<String, Object> map = new HashMap<>();

		Map<String, Object> map1 = patientService.querySexToday();
		int[] map2 = patientService.queryPatientTodayEachHour();
		int[] map3 = patientService.queryPatientInHospitalEachHour();

		map.put("sex", map1);
		map.put("array", map2);
		map.put("array2", map3);
		return map;
	}

	@GetMapping("/getData2")
	public Map<String, Object> getData2()
	{
		Map<String, Object> map = new HashMap<>();

		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();
		if (sessionAdmin == null) {
			return null;
		}
		// 获取当前admin的部门
		Long departmentId = sessionAdmin.getAdminBelong();

		Map<String, Object> map1 = departmentService.querySexTodayByInp(departmentId);
		int[] map2 = departmentService.queryPatientEachHourByInp(departmentId);
		int[] map3 = departmentService.queryPatientInHospitalEachHourByInp(departmentId);

		map.put("sex", map1);
		map.put("array", map2);
		map.put("array2", map3);
		map.put("ih", departmentService.queryPatientTodayInHospital(departmentId));
		return map;
	}
}
