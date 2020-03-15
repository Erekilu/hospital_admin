package top.lsyweb.hosadm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.mapper.AdminMapper;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminTest
{
	@Autowired
	AdminMapper adminMapper;

	@Test
	public void findAdminByUsername()
	{
	}

	@Test
	public void changeAdminById()
	{
	}
}
