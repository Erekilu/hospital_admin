package top.lsyweb.hosadm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.lsyweb.hosadm.domain.Department;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@Component
public interface DepartmentMapper
{
	/**
	 * 查询所有的部门
	 * @return 部门集合
	 */
	List<Department> queryAllDepartments();
	/**
	 * 查询隶属于目标部门的男性病人数量（单日新增）
	 * @param departmentId 部门id
	 * @return 满足条件的病人数量
	 */
	int queryManByInp(@Param("departmentId") Long departmentId);

	/**
	 * 查询隶属于目标部门的女性病人数量（单日新增）
	 * @param departmentId 部门id
	 * @return 满足条件的病人数量
	 */
	int queryWomanByInp(@Param("departmentId") Long departmentId);

	/**
	 * 查询主任所在科室每隔整小时（诸如01:00 - 02:00，02:00 - 03:00）时间段录入的病人数
	 * @param hour 距离当天00:00的向下取整小时数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	int queryPatientEachHourByInp(@Param("hour") int hour, @Param("departmentId") Long departmentId);

	/**
	 * 查询主任所在科室每隔整小时（诸如01:00 - 02:00，02:00 - 03:00）时间段住院的病人数
	 * @param hour 距离当天00:00的向下取整小时数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	int queryPatientInHospitalEachHourByInp(@Param("hour") int hour, @Param("departmentId") Long departmentId);

	/**
	 * 查询主任所在科室今天住院的病人数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	int queryPatientTodayInHospital(@Param("departmentId") Long departmentId);
}
