package top.lsyweb.hosadm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.service.BedService;
import top.lsyweb.hosadm.service.LogService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-14
 */
@Controller
@RestController
public class BedController
{
	@Autowired
	private BedService bedService;
	@Autowired
	private LogService logService;

	/**
	 * 分配病床操作，包括初次指定病床和调换病床操作
	 * @param patient 当前病人的信息
	 * @param bedWardId 病人即将分配到目标病床的病床号
	 * @return 处理结果和处理信息
	 */
	@PostMapping("/assignBed")
	public Map<String, Object> assignBed(Patient patient, String bedWardId)
	{
		Map<String, Object> map = new HashMap<>();

		// 前台参数检测
		if (patient == null || bedWardId == null || "".equals(bedWardId))
		{
			map.put("code", -1);
			map.put("msg", "参数异常！");
			return map;
		}
		// 分离出目标病床的bed_id和ward_id
		int bedId = Integer.parseInt(bedWardId.split("-")[0]);
		int wardId = Integer.parseInt(bedWardId.split("-")[1]);

		try
		{
			bedService.assignBed(patient, bedId, wardId);
			map.put("code", 0);
		}
		catch (Exception e)
		{
			map.put("code", -1);
			map.put("msg", e.getMessage());
		}

		// 添加日志记录
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin)subject.getPrincipal();
		String content = "主任【" + admin.getAdminName() + "】对病人【" + patient.getPatientName() + "】进行了分配病床操作";
		logService.addLog(admin, content);

		return map;
	}

	/**
	 * 解除目标病人的病床占用
	 * @param patient 目标病人的信息
	 * @return 处理结果和处理信息
	 */
	@PostMapping("/removeBed")
	public Map<String, Object> removeBed(Patient patient)
	{
		Map<String, Object> map = new HashMap<>();
		// 前台参数检测
		if (patient == null)
		{
			map.put("code", -1);
			map.put("msg", "参数异常！");
			return map;
		}
		try
		{
			bedService.removeBed(patient);
			map.put("code", 0);
		}
		catch (Exception e)
		{
			map.put("code", -1);
			map.put("msg", e.getMessage());
		}

		// 添加日志记录
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin)subject.getPrincipal();
		String content = "主任【" + admin.getAdminName() + "】解除了病人【" + patient.getPatientName() + "】的病床占用";
		logService.addLog(admin, content);

		return map;
	}
}
