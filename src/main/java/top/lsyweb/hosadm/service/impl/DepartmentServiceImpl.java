package top.lsyweb.hosadm.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Department;
import top.lsyweb.hosadm.mapper.DepartmentMapper;
import top.lsyweb.hosadm.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@Service
public class DepartmentServiceImpl implements DepartmentService
{
	@Autowired
	private DepartmentMapper departmentMapper;

	/**
	 * 查询所有的部门信息
	 * @return 部门对象集合
	 */
	@Override
	public List<Department> queryDepartments()
	{
		return departmentMapper.queryAllDepartments();
	}


	/**
	 * 查询今日新增的男性女性病人数量
	 * @param departmentId
	 * @return 男性女性病人数的map
	 */
	@Override
	public Map<String, Object> querySexTodayByInp(Long departmentId)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("man", departmentMapper.queryManByInp(departmentId));
		map.put("woman", departmentMapper.queryWomanByInp(departmentId));
		return map;
	}

	/**
	 * 查询主任所在科室每隔整小时（诸如01:00 - 02:00，02:00 - 03:00）时间段录入的病人数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	@Override
	public int[] queryPatientEachHourByInp(Long departmentId){
		int[] array = new int[24];
		int i = 0;
		while (i < 24){
			array[i] = departmentMapper.queryPatientEachHourByInp(i, departmentId);
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
	public int[] queryPatientInHospitalEachHourByInp(Long departmentId){
		int[] array = new int[24];
		int i = 0;
		while (i < 24){
			array[i] = departmentMapper.queryPatientInHospitalEachHourByInp(i, departmentId);
			i++;
		}
		return array;
	}

	/**
	 * 查询主任所在科室今天住院的病人数
	 * @param departmentId 部门id
	 * @return 满足条件的病人数
	 */
	public int queryPatientTodayInHospital(Long departmentId){
		return departmentMapper.queryPatientTodayInHospital(departmentId);
	}
}
