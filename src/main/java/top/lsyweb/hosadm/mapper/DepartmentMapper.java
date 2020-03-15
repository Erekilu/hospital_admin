package top.lsyweb.hosadm.mapper;

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
	List<Department> queryAllDepartments();
}
