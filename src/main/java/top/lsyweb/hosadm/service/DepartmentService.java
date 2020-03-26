package top.lsyweb.hosadm.service;

import org.apache.ibatis.annotations.Param;
import top.lsyweb.hosadm.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
public interface DepartmentService
{
	/**
	 * 查询所有的部门信息
	 * @return 部门对象集合
	 */
	List<Department> queryDepartments();

	/**
	 * 查询今日新增的男性女性病人数量
	 * @param departmentId
	 * @return 男性女性病人数的map
	 */
	Map<String, Object> querySexTodayByInp(Long departmentId);

	/**
	 * 查询主任所在科室每隔整小时（诸如01:00 - 02:00，02:00 - 03:00）时间段录入的病人数
	 * @param departmentId
	 * @return 满足条件的病人数
	 */
	int[] queryPatientEachHourByInp(Long departmentId);

	/**
	 * 查询主任所在科室每隔整小时（诸如01:00 - 02:00，02:00 - 03:00）时间段住院的病人数
	 * @param departmentId
	 * @return 满足条件的病人数
	 */
	int[] queryPatientInHospitalEachHourByInp(Long departmentId);

	/**
	 * 查询主任所在科室今天住院的病人数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	int queryPatientTodayInHospital(Long departmentId);


}
