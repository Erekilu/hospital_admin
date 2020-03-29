package top.lsyweb.hosadm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.lsyweb.hosadm.domain.Department;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@Component
public interface DepartmentMapper
{
	/**
	 * 查询所有的部门
	 * @return 部门集合
	 */
	List<Department> queryAllDepartments();

}
