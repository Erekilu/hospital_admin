package top.lsyweb.hosadm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lsyweb.hosadm.service.AdminService;
import top.lsyweb.hosadm.service.DepartmentService;
import top.lsyweb.hosadm.util.RedisUtil;

import java.util.ArrayList;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest
{
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private AdminService adminService;

//	@Test
//	public void set()
//	{
//		int[] array = new int[3];
//		array[0] = 0;
//		array[1] = 1;
//		array[2] = 2;
//		redisUtil.setIntArray(array, "testArray");
//		//		redisUtil.set("hello", "world");
//	}
//
//	@Test
//	public void get()
//	{
//		int[] testArrays = redisUtil.getIntArray("testArray");
//		System.out.println(testArrays[0] + " " +  testArrays[1] + " " + testArrays[2]);
//	}

//	@Test
//	public void departmentGet()
//	{
//		//		List<Department> departments = departmentService.queryDepartments();
//		//		System.out.println(departments);
//	}
//
//	@Test
//	public void admin()
//	{
//		adminService.findAdminById(1);
//		adminService.findAdminById(2);
//		adminService.findAdminById(1);
//	}
	@Test
	public void test()
	{

	}

}
