package top.lsyweb.hosadm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.domain.Ward;
import top.lsyweb.hosadm.mapper.WardMapper;
import top.lsyweb.hosadm.service.WardService;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WardTest
{
	@Autowired
	private WardMapper wardMapper;
	@Autowired
	private WardService wardService;

//	@Test
//	public void queryWards() {
//		List<Ward> list = wardMapper.queryWards(1);
//		for (Ward ward : list) {
//			System.out.println(ward);
//		}
//	}
}
