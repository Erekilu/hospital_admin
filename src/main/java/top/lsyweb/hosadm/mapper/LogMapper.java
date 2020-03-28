package top.lsyweb.hosadm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.lsyweb.hosadm.domain.Log;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-16
 */
@Component
public interface LogMapper
{
	/**
	 * 插入一个log
	 * @param log log对象，包含了各种信息
	 * @return 该操作影响的数据库行数
	 */
	int insertLog(Log log);

	/**
	 * 分页查询目标用户所产生的日志
	 * @param logMaster 目标用户id
	 * @param rowIndex 行号（service层已经把页号转换成行号了）
	 * @param pageSize 每页数量
	 * @return 符合条件的日志集合
	 */
	List<Log> queryLogsByAdmin(@Param("logMaster") long logMaster,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 查询目标用户所产生的日志总数（配合上一个方法）
	 * @param logMaster 目标用户id
	 * @return 符合条件的日志总数
	 */
	int queryLogsCountByAdmin(long logMaster);

	/**
	 * 分页查询目标部门所产生的日志
	 * @param logBelong 部门id
	 * @param rowIndex 行号（service层已经把页号转换成行号了）
	 * @param pageSize 每页数量
	 * @return 符合条件的日志集合
	 */
	List<Log> queryLogsByDepartment(@Param("logBelong") long logBelong,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 查询目标部门所产生的日志总数（配合上一个方法）
	 * @param logBelong 部门id
	 * @return 符合条件的日志总数
	 */
	int queryLogsCountByDepartment(long logBelong);
}
