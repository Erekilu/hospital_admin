package top.lsyweb.hosadm.domain;

import java.sql.Timestamp;

public class Admin {

  private Long adminId;
  private Long adminBelong;
  private String adminType;
  private String adminUsername;
  private String adminPassword;
  private String adminName;
  private String adminSex;
  private String adminPhone;
  private String adminSrc;
  private String adminDescribe;
  private java.sql.Timestamp adminCreateTime;
  private java.sql.Timestamp adminLastEditTime;

  @Override
  public String toString()
  {
    return "Admin{" + "adminId=" + adminId + ", adminBelong=" + adminBelong + ", adminType='" + adminType + '\''
            + ", adminUsername='" + adminUsername + '\'' + ", adminPassword='" + adminPassword + '\'' + ", adminName='"
            + adminName + '\'' + ", adminSex='" + adminSex + '\'' + ", adminPhone='" + adminPhone + '\''
            + ", adminSrc='" + adminSrc + '\'' + ", adminDescribe='" + adminDescribe + '\'' + ", adminCreateTime="
            + adminCreateTime + ", adminLastEditTime=" + adminLastEditTime + '}';
  }

  public Long getAdminId()
  {
    return adminId;
  }

  public void setAdminId(long adminId)
  {
    this.adminId = adminId;
  }

  public Long getAdminBelong()
  {
    return adminBelong;
  }

  public void setAdminBelong(long adminBelong)
  {
    this.adminBelong = adminBelong;
  }

  public String getAdminType()
  {
    return adminType;
  }

  public void setAdminType(String adminType)
  {
    this.adminType = adminType;
  }

  public String getAdminUsername()
  {
    return adminUsername;
  }

  public void setAdminUsername(String adminUsername)
  {
    this.adminUsername = adminUsername;
  }

  public String getAdminPassword()
  {
    return adminPassword;
  }

  public void setAdminPassword(String adminPassword)
  {
    this.adminPassword = adminPassword;
  }

  public String getAdminName()
  {
    return adminName;
  }

  public void setAdminName(String adminName)
  {
    this.adminName = adminName;
  }

  public String getAdminSex()
  {
    return adminSex;
  }

  public void setAdminSex(String adminSex)
  {
    this.adminSex = adminSex;
  }

  public String getAdminPhone()
  {
    return adminPhone;
  }

  public void setAdminPhone(String adminPhone)
  {
    this.adminPhone = adminPhone;
  }

  public String getAdminSrc()
  {
    return adminSrc;
  }

  public void setAdminSrc(String adminSrc)
  {
    this.adminSrc = adminSrc;
  }

  public String getAdminDescribe()
  {
    return adminDescribe;
  }

  public void setAdminDescribe(String adminDescribe)
  {
    this.adminDescribe = adminDescribe;
  }

  public Timestamp getAdminCreateTime()
  {
    return adminCreateTime;
  }

  public void setAdminCreateTime(Timestamp adminCreateTime)
  {
    this.adminCreateTime = adminCreateTime;
  }

  public Timestamp getAdminLastEditTime()
  {
    return adminLastEditTime;
  }

  public void setAdminLastEditTime(Timestamp adminLastEditTime)
  {
    this.adminLastEditTime = adminLastEditTime;
  }
}
