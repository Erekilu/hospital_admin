package top.lsyweb.hosadm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.domain.Ward;
import top.lsyweb.hosadm.service.BedService;
import top.lsyweb.hosadm.service.DepartmentService;
import top.lsyweb.hosadm.service.PatientService;
import top.lsyweb.hosadm.service.WardService;

import java.util.HashMap;
import java.util.List;
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

	@GetMapping("/getData_2")
	public Map<String, Object> getData_2()
	{
		Map<String, Object> map = new HashMap<>();

		Map<String, Object> map1 = patientService.querySexToday_2();
		int[] map2 = patientService.queryPatientTodayEachHour_2();
		int[] map3 = patientService.queryPatientInHospitalEachHour_2();

		map.put("sex", map1);
		map.put("array", map2);
		map.put("array2", map3);
		return map;
	}

	@GetMapping("/getData2_2")
	public Map<String, Object> getData2_2()
	{
		Map<String, Object> map = new HashMap<>();

		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();
		if (sessionAdmin == null) {
			return null;
		}
		// 获取当前admin的部门
		Long departmentId = sessionAdmin.getAdminBelong();

		Map<String, Object> map1 = patientService.querySexTodayByInp_2(departmentId);
		int[] map2 = patientService.queryPatientEachHourByInp_2(departmentId);
		int[] map3 = patientService.queryPatientInHospitalEachHourByInp_2(departmentId);

		map.put("sex", map1);
		map.put("array", map2);
		map.put("array2", map3);
		map.put("ih", patientService.queryPatientTodayInHospital_2(departmentId));
		return map;
	}

	@GetMapping("/getPatientsByInp")
	public Map<String, Object> getPatientsByInp() {
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();
		if (sessionAdmin == null) {
			return null;
		}
		// 获取当前admin的部门
		Long departmentId = sessionAdmin.getAdminBelong();
		// 获取当前部门下上一周以及这一周每一天的病人数
		map.putAll(patientService.queryPatientByTime_1(departmentId));
		// 获取当前部门下的男女病人数目
		map.putAll(patientService.querySexByInp_1(departmentId));
		// 获取当前部门并分配了病床的病人数量
		map.put("haveBed", patientService.queryPatientIfBed_1(departmentId));
		// 获取当前部门所有病房信息
		List<Ward> list = wardService.queryWardsBeds(departmentId.intValue());
		// 获取当前部门病房数量
		map.put("allWards", list.size());
		// 获取当前部门宽松病房数量
		int rel = 0, allbeds = 0;
		for (Ward ward : list) {
			if (ward.getWardStatus().equals("宽松")) {
				rel++;
			}
			allbeds += ward.getWardBeds().size();
		}
		map.put("rel", rel);
		// 获取当前部门病床数量
		map.put("allBeds", allbeds);
		// 获取当前部门此星期的住院数以及占用病床数目  上星期的住院人数
		map.putAll(patientService.queryPatientInHos_1(departmentId));
		map.put("code", 0);
		return map;
	}
}
