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



}
