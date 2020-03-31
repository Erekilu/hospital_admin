package top.lsyweb.hosadm.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Department;
import top.lsyweb.hosadm.mapper.DepartmentMapper;
import top.lsyweb.hosadm.service.DepartmentService;
import top.lsyweb.hosadm.util.RedisUtil;

import java.io.IOException;
import java.util.ArrayList;
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
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 查询所有的部门信息
	 * @return 部门对象集合
	 */
	@Override
	public List<Department> queryDepartments()
	{
		List<Department> departments = null;
		ObjectMapper mapper = new ObjectMapper();
		if (!redisUtil.exists("department")) {
			// 如果redis中没有该缓存，则写入redis
			departments = departmentMapper.queryAllDepartments();
			String jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(departments);
				redisUtil.set("department", jsonString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String jsonString = redisUtil.get("department");
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Department.class);
			try
			{
				departments = mapper.readValue(jsonString, javaType);
			}
			catch (JsonProcessingException e)
			{
				e.printStackTrace();
			}
		}
		return departments;
//		return departmentMapper.queryAllDepartments();
	}
}
