package top.lsyweb.hosadm.service;

import top.lsyweb.hosadm.domain.Department;

import java.util.List;

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
