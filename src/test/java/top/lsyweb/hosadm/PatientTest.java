package top.lsyweb.hosadm;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.mapper.PatientMapper;
import top.lsyweb.hosadm.service.PatientService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientTest
{
	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private PatientService patientService;

	@Test
	public void addPatient()
	{

	}

//	@Test
//	public void queryManBySomeWeekInp() {
//		int all = patientMapper.queryPatientIfBed(1);
//		System.out.println("all = " + all);
//	}


}
