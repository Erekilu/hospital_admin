package top.lsyweb.hosadm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.lsyweb.hosadm.domain.Patient;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-06
 */
@Component
public interface PatientMapper
{
	Patient findPatientById(int patientId);

	int addPatient(Patient patient);

	/**
	 * 根据病人id，name，phone分页查询所有满足条件的病人
	 *
	 * @return 满足条件的所有病人信息
	 */
	List<Patient> queryPatientByInp(@Param("queryString") String queryString, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize, @Param("departmentId") Long departmentId);

	int queryPatientCountByInp(@Param("queryString") String queryString, @Param("departmentId") Long departmentId);

	int changeBed(Patient patient);
}
