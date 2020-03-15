package top.lsyweb.hosadm.domain;

import java.sql.Timestamp;

public class Bed
{

	private Long bedId;
	private Long bedBelong;
	private String bedStatus;
	private String bedName;
	private java.sql.Timestamp bedCreateTime;
	private java.sql.Timestamp bedLastEditTime;

	@Override
	public String toString()
	{
		return "Bed{" + "bedId=" + bedId + ", bedBelong=" + bedBelong + ", bedStatus='" + bedStatus + '\''
				+ ", bedName='" + bedName + '\'' + ", bedCreateTime=" + bedCreateTime + ", bedLastEditTime="
				+ bedLastEditTime + '}';
	}

	public Long getBedId()
	{
		return bedId;
	}

	public void setBedId(Long bedId)
	{
		this.bedId = bedId;
	}

	public Long getBedBelong()
	{
		return bedBelong;
	}

	public void setBedBelong(Long bedBelong)
	{
		this.bedBelong = bedBelong;
	}

	public String getBedStatus()
	{
		return bedStatus;
	}

	public void setBedStatus(String bedStatus)
	{
		this.bedStatus = bedStatus;
	}

	public String getBedName()
	{
		return bedName;
	}

	public void setBedName(String bedName)
	{
		this.bedName = bedName;
	}

	public Timestamp getBedCreateTime()
	{
		return bedCreateTime;
	}

	public void setBedCreateTime(Timestamp bedCreateTime)
	{
		this.bedCreateTime = bedCreateTime;
	}

	public Timestamp getBedLastEditTime()
	{
		return bedLastEditTime;
	}

	public void setBedLastEditTime(Timestamp bedLastEditTime)
	{
		this.bedLastEditTime = bedLastEditTime;
	}
}
