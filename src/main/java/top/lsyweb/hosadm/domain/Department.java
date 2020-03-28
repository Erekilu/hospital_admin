package top.lsyweb.hosadm.domain;


public class Department {

  private Long departmentId;
  private String departmentName;
  private String departmentDescribe;
  private String departmentSrc;
  private java.sql.Timestamp departmentCreateTime;
  private java.sql.Timestamp departmentLastEditTime;

  @Override
  public String toString()
  {
    return "Department{" + "departmentId=" + departmentId + ", departmentName='" + departmentName + '\''
            + ", departmentDescribe='" + departmentDescribe + '\'' + ", departmentSrc='" + departmentSrc + '\''
            + ", departmentCreateTime=" + departmentCreateTime + ", departmentLastEditTime=" + departmentLastEditTime
            + '}';
  }

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(long departmentId) {
    this.departmentId = departmentId;
  }


  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }


  public String getDepartmentDescribe() {
    return departmentDescribe;
  }

  public void setDepartmentDescribe(String departmentDescribe) {
    this.departmentDescribe = departmentDescribe;
  }


  public String getDepartmentSrc() {
    return departmentSrc;
  }

  public void setDepartmentSrc(String departmentSrc) {
    this.departmentSrc = departmentSrc;
  }


  public java.sql.Timestamp getDepartmentCreateTime() {
    return departmentCreateTime;
  }

  public void setDepartmentCreateTime(java.sql.Timestamp departmentCreateTime) {
    this.departmentCreateTime = departmentCreateTime;
  }


  public java.sql.Timestamp getDepartmentLastEditTime() {
    return departmentLastEditTime;
  }

  public void setDepartmentLastEditTime(java.sql.Timestamp departmentLastEditTime) {
    this.departmentLastEditTime = departmentLastEditTime;
  }

}
