package top.lsyweb.hosadm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.dto.PatientExecution;
import top.lsyweb.hosadm.mapper.PatientMapper;
import top.lsyweb.hosadm.service.PatientService;
import top.lsyweb.hosadm.util.PathUtil;

import java.sql.Timestamp;
import java.util.*;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@Service
public class PatientServiceImpl implements PatientService
{
	@Autowired
	private PatientMapper patientMapper;

	/**
	 * 添加病人
	 * @param patient 病人对象
	 */
	@Override
	public void addPatient(Patient patient)
	{
		// 设置创建，最后编辑时间
		patient.setPatientCreateTime(new Timestamp(new Date().getTime()));
		patient.setPatientLastEditTime(new Timestamp(new Date().getTime()));
		patientMapper.addPatient(patient);
	}

	/**
	 * 分页查询目标部门下满足条件的病人
	 * @param queryString 查询条件
	 * @param pageIndex 页号
	 * @param pageSize 每页数量
	 * @param departmentId 部门id
	 * @return 病人集合和病人总数
	 */
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
	public Map<String, Object> querySexToday()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("man", patientMapper.queryManToday());
		map.put("woman", patientMapper.queryWomanToday());
		return map;
	}

	@Override
	public Map<String, Object> querySexByInp(Long departmentId) {
		Map<String, Object> map = new HashMap<>();
		map.put("man", patientMapper.queryManByInp(departmentId));
		map.put("woman", patientMapper.queryWomanByInp(departmentId));
		return map;
	}

	@Override
	public Map<String, Object> queryPatientByTime(Long departmentId) {
		Map<String, Object> map = new HashMap<>();
		int thisweek = 0, lastweek = 0;
		for (int i = 0; i < 7; i++) {
			int temp1 = patientMapper.queryPatientByTime(departmentId, 0, i);
			int temp2 = patientMapper.queryPatientByTime(departmentId, 1, i);
			thisweek += temp1;
			lastweek += temp2;
			if (i == 0) map.put("six", temp1);
			else if (i == 1) map.put("seven", temp1);
			else if (i == 2) map.put("one", temp1);
			else if (i == 3) map.put("two", temp1);
			else if (i == 4) map.put("three", temp1);
			else if (i == 5) map.put("four", temp1);
			else map.put("five", temp1);
		}
		map.put("thisweek", thisweek);
		map.put("lastweek", lastweek);
		return map;
	}

	@Override
	public int queryPatientIfBed(Long departmentId) {
		return patientMapper.queryPatientIfBed(departmentId);
	}

	@Override
	public Map<String, Object> queryPatientInHos(Long departmentId) {
		Map<String, Object> map = new HashMap<>();
		map.put("thisweekbed", patientMapper.queryPatientInHos(departmentId, 0));
		map.put("lastweekbed", patientMapper.queryPatientInHos(departmentId, 1));
		return map;
	}

}
