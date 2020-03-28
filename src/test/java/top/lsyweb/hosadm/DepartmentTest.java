package top.lsyweb.hosadm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lsyweb.hosadm.domain.Department;
import top.lsyweb.hosadm.mapper.DepartmentMapper;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentTest
{
	@Autowired
	private DepartmentMapper departmentMapper;

	@Test
	public void queryDepartments()
	{
	}
}
