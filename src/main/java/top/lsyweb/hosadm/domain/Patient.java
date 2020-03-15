package top.lsyweb.hosadm.domain;

import java.sql.Timestamp;

public class Patient
{

	private Long patientId;
	private Long patientBelong;
	private String patientName;
	private String patientPhone;
	private String patientSex;
	private Long patientBed;
	private java.sql.Timestamp patientCreateTime;
	private java.sql.Timestamp patientLastEditTime;
	private String patientDescribe;
	private String patientSrc;

	@Override
	public String toString()
	{
		return "Patient{" + "patientId=" + patientId + ", patientBelong=" + patientBelong + ", patientName='"
				+ patientName + '\'' + ", patientPhone='" + patientPhone + '\'' + ", patientSex='" + patientSex + '\''
				+ ", patientBed=" + patientBed + ", patientCreateTime=" + patientCreateTime + ", patientLastEditTime="
				+ patientLastEditTime + ", patientDescribe='" + patientDescribe + '\'' + ", patientSrc='" + patientSrc
				+ '\'' + '}';
	}

	public Long getPatientId()
	{
		return patientId;
	}

	public void setPatientId(Long patientId)
	{
		this.patientId = patientId;
	}

	public Long getPatientBelong()
	{
		return patientBelong;
	}

	public void setPatientBelong(Long patientBelong)
	{
		this.patientBelong = patientBelong;
	}

	public String getPatientName()
	{
		return patientName;
	}

	public void setPatientName(String patientName)
	{
		this.patientName = patientName;
	}

	public String getPatientPhone()
	{
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone)
	{
		this.patientPhone = patientPhone;
	}

	public String getPatientSex()
	{
		return patientSex;
	}

	public void setPatientSex(String patientSex)
	{
		this.patientSex = patientSex;
	}

	public Long getPatientBed()
	{
		return patientBed;
	}

	public void setPatientBed(Long patientBed)
	{
		this.patientBed = patientBed;
	}

	public Timestamp getPatientCreateTime()
	{
		return patientCreateTime;
	}

	public void setPatientCreateTime(Timestamp patientCreateTime)
	{
		this.patientCreateTime = patientCreateTime;
	}

	public Timestamp getPatientLastEditTime()
	{
		return patientLastEditTime;
	}

	public void setPatientLastEditTime(Timestamp patientLastEditTime)
	{
		this.patientLastEditTime = patientLastEditTime;
	}

	public String getPatientDescribe()
	{
		return patientDescribe;
	}

	public void setPatientDescribe(String patientDescribe)
	{
		this.patientDescribe = patientDescribe;
	}

	public String getPatientSrc()
	{
		return patientSrc;
	}

	public void setPatientSrc(String patientSrc)
	{
		this.patientSrc = patientSrc;
	}
}
