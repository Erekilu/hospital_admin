package top.lsyweb.hosadm.service;

import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.dto.PatientExecution;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
public interface PatientService
{
	void addPatient(Patient patient);

	PatientExecution queryPatientByInp(String queryString, int pageIndex, int pageSize, Long departmentId);
}
