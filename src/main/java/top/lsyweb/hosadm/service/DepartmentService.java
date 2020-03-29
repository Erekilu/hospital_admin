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




}
