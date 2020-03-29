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
import java.util.HashMap;
import java.util.Map;

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
	public Map<String, Object> querySexToday_2()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("man", patientMapper.queryManToday_2());
		map.put("woman", patientMapper.queryWomanToday_2());
		return map;
	}

	/**
	 * 查询指定与当天00:00间隔向下取整小时数的录入病人数
	 * @return 24个间隔一小时时间段的数据数组
	 */
	@Override
	public int[] queryPatientTodayEachHour_2()
	{
		int[] array = new int[24];
		int i = 0;
		while (i < 24){
			array[i] = patientMapper.queryPatientEachHour_2(i);
			i++;
		}
		return array;
	}

	/**
	 * 查询指定与当天00:00间隔向下取整小时数的录入病人数
	 * @return 24个间隔一小时时间段的数据数组
	 */
	@Override
	public int[] queryPatientInHospitalEachHour_2()
	{
		int[] array = new int[24];
		int i = 0;
		while (i < 24){
			array[i] = patientMapper.queryPatientInHospitalEachHour_2(i);
			i++;
		}
		return array;
	}

	/**
	 * 查询今日新增的男性女性病人数量
	 * @param departmentId
	 * @return 男性女性病人数的map
	 */
	@Override
	public Map<String, Object> querySexTodayByInp_2(Long departmentId)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("man", patientMapper.queryManByInp_2(departmentId));
		map.put("woman", patientMapper.queryWomanByInp_2(departmentId));
		return map;
	}

	/**
	 * 查询主任所在科室每隔整小时（诸如01:00 - 02:00，02:00 - 03:00）时间段录入的病人数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	@Override
	public int[] queryPatientEachHourByInp_2(Long departmentId){
		int[] array = new int[24];
		int i = 0;
		while (i < 24){
			array[i] = patientMapper.queryPatientEachHourByInp_2(i, departmentId);
			i++;
		}
		return array;
	}

	/**
	 * 查询主任所在科室每隔整小时（诸如01:00 - 02:00，02:00 - 03:00）时间段住院的病人数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	@Override
	public int[] queryPatientInHospitalEachHourByInp_2(Long departmentId){
		int[] array = new int[24];
		int i = 0;
		while (i < 24){
			array[i] = patientMapper.queryPatientInHospitalEachHourByInp_2(i, departmentId);
			i++;
		}
		return array;
	}

	/**
	 * 查询主任所在科室今天住院的病人数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	public int queryPatientTodayInHospital_2(Long departmentId){
		return patientMapper.queryPatientTodayInHospital_2(departmentId);
	}
}
