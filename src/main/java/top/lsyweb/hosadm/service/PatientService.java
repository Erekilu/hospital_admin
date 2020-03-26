package top.lsyweb.hosadm.service;

import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.dto.PatientExecution;

import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
public interface PatientService
{
	/**
	 * 添加病人
	 * @param patient 病人对象
	 */
	void addPatient(Patient patient);

	/**
	 * 分页查询目标部门下满足条件的病人
	 * @param queryString 查询条件
	 * @param pageIndex 页号
	 * @param pageSize 每页数量
	 * @param departmentId 部门id
	 * @return 病人集合和病人总数
	 */
	PatientExecution queryPatientByInp(String queryString, int pageIndex, int pageSize, Long departmentId);


	Map<String, Object> querySexToday();

	/**
	 * 查询指定与当天00:00间隔向下取整小时数的录入病人数
	 * @return 24个间隔一小时时间段的病人数数组
	 */
	int[] queryPatientTodayEachHour();

	/**
	 * 查询指定与当天00:00间隔向下取整小时数的住院病人数
	 * @return 24个间隔一小时时间段的病人数数组
	 */
	int[] queryPatientInHospitalEachHour();
}
