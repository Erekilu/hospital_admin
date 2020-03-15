package top.lsyweb.hosadm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.dto.PatientExecution;
import top.lsyweb.hosadm.mapper.PatientMapper;
import top.lsyweb.hosadm.service.PatientService;
import top.lsyweb.hosadm.util.PathUtil;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@Service
public class PatientServiceImpl implements PatientService
{
	@Autowired
	private PatientMapper patientMapper;

	@Override
	public void addPatient(Patient patient)
	{
		// 设置创建，最后编辑时间
		patient.setPatientCreateTime(new Timestamp(new Date().getTime()));
		patient.setPatientLastEditTime(new Timestamp(new Date().getTime()));
		patientMapper.addPatient(patient);
	}

	@Override
	public PatientExecution queryPatientByInp(String queryString, int pageIndex, int pageSize, Long departmentId)
	{
		PatientExecution patientExecution = new PatientExecution();
		// 获取总数据量
		patientExecution.setCount(patientMapper.queryPatientCountByInp(queryString, departmentId));
		// 根据页号计算出行号
		int rowIndex = PathUtil.calculateRowIndex(pageIndex, pageSize);
		// 获取病人列表
		patientExecution.setPatients(patientMapper.queryPatientByInp(queryString, rowIndex, pageSize, departmentId));
		return patientExecution;
	}

	@Override
	public int changeBed(Patient patient)
	{
		patient.setPatientLastEditTime(new Timestamp(new Date().getTime()));
		return patientMapper.changeBed(patient);
	}
}
