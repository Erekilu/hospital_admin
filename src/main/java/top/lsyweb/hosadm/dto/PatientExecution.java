package top.lsyweb.hosadm.dto;

import top.lsyweb.hosadm.domain.Patient;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-11
 */
public class PatientExecution
{
	private Patient patient;
	private int count;
	private List<Patient> patients;

	public Patient getPatient()
	{
		return patient;
	}

	public void setPatient(Patient patient)
	{
		this.patient = patient;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public List<Patient> getPatients()
	{
		return patients;
	}

	public void setPatients(List<Patient> patients)
	{
		this.patients = patients;
	}
}
