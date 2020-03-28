package top.lsyweb.hosadm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.mapper.BedMapper;
import top.lsyweb.hosadm.mapper.PatientMapper;
import top.lsyweb.hosadm.mapper.WardMapper;
import top.lsyweb.hosadm.service.BedService;
import top.lsyweb.hosadm.service.WardService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-14
 */
@Service
public class BedServiceImpl implements BedService
{
	@Autowired
	private BedMapper bedMapper;
	@Autowired
	private WardService wardService;
	@Autowired
	private PatientMapper patientMapper;

	/**
	 * 给病人分配病床
	 * @param patient 病人对象
	 * @param bedId 目标病床id
	 * @param wardId 目标病床所在病房id
	 * @throws Exception 冲突
	 */
	@Override
	@Transactional
	public void assignBed(Patient patient, int bedId, int wardId) throws Exception
	{
		// 冲突检测，判断病床是否被占用
		Bed resultBed = bedMapper.queryBedById(bedId);
		if (resultBed == null)
		{
			throw new Exception("找不到对应病床！");
		}
		if ("占用".equals(resultBed.getBedStatus()))
		{
			throw new Exception("病床已被占用！");
		}

		// 修改病人的病床指向
		Patient target = new Patient();
		target.setPatientId(patient.getPatientId());
		target.setPatientLastEditTime(new Timestamp(new Date().getTime()));
		target.setPatientBed((long)bedId);
		if (patientMapper.changeBed(target) != 1)
		{
			throw new Exception("修改病人patientBed指向失败");
		}
		// 将刚刚设置的病床设置为 占用 状态
		if (bedMapper.changeBedStatus(bedId, "占用") != 1)
		{
			throw new Exception("修改bedStatus为”占用“失败");
		}
		// 维护目标病床的拥挤程度
		wardService.maintainWard(wardId);
		// 如果原病床不为空，即调换病房
		if (patient.getPatientBed() != null)
		{
			// 将原病床状态设置为 未占用
			if (bedMapper.changeBedStatus(patient.getPatientBed().intValue(), "未占用") != 1)
			{
				throw new Exception("修改原病床为”未占用“失败");
			}
			// 维护原病床所在病房的状态
			Bed originBed = bedMapper.queryBedById(patient.getPatientBed().intValue());
			wardService.maintainWard(originBed.getBedBelong().intValue());
		}
	}

	/**
	 * 解除目标病人的病床占用
	 * @param patient 病人对象，封装了patient_id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void removeBed(Patient patient) throws Exception
	{
		if (patient == null || patient.getPatientBed() == null)
		{
			throw new Exception("病床占用为空，无法解除占用");
		}
		// 将病人的病床指向切换为空
		Patient target = new Patient();
		target.setPatientId(patient.getPatientId());
		target.setPatientLastEditTime(new Timestamp(new Date().getTime()));
		target.setPatientBed(null);
		if (patientMapper.changeBed(target) != 1)
		{
			throw new Exception("修改病人patientBed失败");
		}
		// 将病人原病床设置为未占用状态
		if (bedMapper.changeBedStatus(patient.getPatientBed().intValue(), "未占用") != 1)
		{
			throw new Exception("修改bedStatus失败");
		}
		// 维护病人原病床所在病床的拥挤度
		Bed originBed = bedMapper.queryBedById(patient.getPatientBed().intValue());
		wardService.maintainWard(originBed.getBedBelong().intValue());
	}
}
