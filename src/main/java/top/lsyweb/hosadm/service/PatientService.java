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

	Map<String, Object> querySexByInp(Long departmentId);

	Map<String, Object> queryPatientByTime(Long departmentId);

	int queryPatientIfBed(Long departmentId);

	Map<String, Object> queryPatientInHos(Long departmentId);
}
