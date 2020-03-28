package top.lsyweb.hosadm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.lsyweb.hosadm.domain.Patient;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-06
 */
@Component
public interface PatientMapper
{
	/**
	 * 根据病人id查询病人
	 * @param patientId 病人id
	 * @return 病人对象
	 */
	Patient findPatientById(int patientId);

	/**
	 * 添加病人
	 * @param patient 病人对象
	 * @return 该操作影响的行数
	 */
	int addPatient(Patient patient);

	/**
	 * 分页查询所有隶属于目标部门满足查询条件的病人
	 * 	 * 注意：只要满足id，phone，name之一即可
	 * @param queryString 用户输入的查询条件
	 * @param rowIndex 行号（由service层将页号转化为行号）
	 * @param pageSize 每页数量
	 * @param departmentId 部门id
	 * @return 满足条件的病人集合
	 */
	List<Patient> queryPatientByInp(@Param("queryString") String queryString, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize, @Param("departmentId") Long departmentId);

	/**
	 * 查询所有隶属于目标部门满足查询条件的病人数量（和上一个方法配合使用）
	 * @param queryString 用户输入的查询条件
	 * @param departmentId 部门id
	 * @return 满足条件的病人总数
	 */
	int queryPatientCountByInp(@Param("queryString") String queryString, @Param("departmentId") Long departmentId);

	/**
	 * 修改目标病人的病床号
	 * @param patient 病人id和目标病床id封装在该对象里
	 * @return 该操作影响的数据库行数
	 */
	int changeBed(Patient patient);

	int queryManToday();

	int queryWomanToday();

	/**
	 * 查询所有隶属于目标部门的男性病人数量(可增加时间限制)
	 * @param departmentId 部门id
	 * @return 满足条件的病人数量
	 */
	int queryManByInp(@Param("departmentId") Long departmentId);

	/**
	 * 查询所有隶属于目标部门的女性病人数量
	 * @param departmentId 部门id
	 * @return 满足条件的病人数量
	 */
	int queryWomanByInp(@Param("departmentId") Long departmentId);

	/**
	 * 查询所有隶属于目标部门和目标时间段内的病人
	 * @param departmentId 部门id
	 * @param week 几个星期前
	 * @param day 几个星期前的星期几
	 * @return 满足条件的病人数量
	 */
	int queryPatientByTime(@Param("departmentId") Long departmentId, @Param("week") int week, @Param("day") int day);

	/**
	 * 查询所有隶属于目标部门并分配了病床的病人数量
	 * @param departmentId 部门id
	 * @return 满足条件的病人数量
	 */
	int queryPatientIfBed(@Param("departmentId") Long departmentId);

	/**
	 * 查询所有隶属于目标部门和目标时间段内的住院病人
	 * @param departmentId 部门id
	 * @param week 几个星期前
	 * @return 满足条件的病人数量
	 */
	int queryPatientInHos(@Param("departmentId") Long departmentId, @Param("week") int week);

}
