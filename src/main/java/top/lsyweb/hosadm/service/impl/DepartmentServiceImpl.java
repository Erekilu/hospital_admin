package top.lsyweb.hosadm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Department;
import top.lsyweb.hosadm.mapper.DepartmentMapper;
import top.lsyweb.hosadm.service.DepartmentService;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@Service
public class DepartmentServiceImpl implements DepartmentService
{
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> queryDepartments()
	{
		return departmentMapper.queryAllDepartments();
	}
}
