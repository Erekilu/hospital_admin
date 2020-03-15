package top.lsyweb.hosadm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.service.BedService;
import top.lsyweb.hosadm.service.PatientService;
import top.lsyweb.hosadm.service.WardService;

import java.util.HashMap;
import java.util.List;
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
	private WardService wardService;
	@Autowired
	private PatientService patientService;

	/**
	 * 分配病床操作
	 * @param patient
	 * @param bedWardId
	 * @return
	 */
	@PostMapping("/assignBed")
	public Map<String, Object> assignBed(Patient patient, String bedWardId)
	{
		Map<String, Object> map = new HashMap<>();
		// 分离出目标病床的bed_id和ward_id
		int bedId = Integer.parseInt(bedWardId.split("-")[0]);
		int wardId = Integer.parseInt(bedWardId.split("-")[1]);
		// 冲突检测，判断病床是否被占用
		Bed resultBed = bedService.findBedById(bedId);
		if ("占用".equals(resultBed.getBedStatus()))
		{
			map.put("code", -1);
			map.put("msg", "该病床已占用，请重新选择病床");
			return map;
		}
		// 修改病人的病床信息
		Patient target = new Patient();
		target.setPatientId(patient.getPatientId());
		target.setPatientBed((long)bedId);
		patientService.changeBed(target);
		// 将目标床位设置成占用状态
		bedService.changeBedStatus(bedId, "占用");
		// 维护目标病房的拥挤程度
		maintain(wardId);

		// 如果原病床不为空，即调换病房
		if (patient.getPatientBed() != null)
		{
			// 解除原病房的占用
			bedService.changeBedStatus(patient.getPatientBed().intValue(), "未占用");
			// 维护原病房的状态
			Bed originBed = bedService.findBedById(patient.getPatientBed().intValue());
			maintain(originBed.getBedBelong().intValue());
		}
		map.put("code", 0);
		return map;
	}

	/**
	 * 解除病床占用
	 * @return
	 */
	@PostMapping("/removeBed")
	public Map<String, Object> removeBed(Patient patient)
	{
		Map<String, Object> map = new HashMap<>();
		if (patient.getPatientBed() == null)
		{
			map.put("code", -1);
			map.put("msg", "病床占用为空，无法解除占用");
			return map;
		}

		// 将目标床位设置成未占用状态
		bedService.changeBedStatus(patient.getPatientBed().intValue(), "未占用");
		// 修改病人的病床信息
		Patient target = new Patient();
		target.setPatientId(patient.getPatientId());
		target.setPatientBed(null);
		patientService.changeBed(target);
		// 维护目标病房的拥挤程度
		Bed originBed = bedService.findBedById(patient.getPatientBed().intValue());
		maintain(originBed.getBedBelong().intValue());

		map.put("code", 0);
		return map;
	}

	/**
	 * 维护目标病房的拥挤状态
	 * @param wardId 目标病房主键
	 */
	public void maintain(int wardId)
	{
		List<Bed> beds = bedService.queryBedsByWardId(wardId);
		int count = 0, all = 0;
		for (Bed bed : beds)
		{
			all++;
			if ("占用".equals(bed.getBedStatus()))
				count++;
		}
		if ((double)count / all >= 0.9)
		{
			wardService.changeStatus(wardId, "爆满");
		}
		else if ((double)count / all >= 0.6)
		{
			wardService.changeStatus(wardId, "拥挤");
		}
		else
		{
			wardService.changeStatus(wardId, "宽松");
		}
	}
}
