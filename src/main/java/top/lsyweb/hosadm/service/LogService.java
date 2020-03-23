package top.lsyweb.hosadm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.domain.Log;
import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.dto.LogExecution;
import top.lsyweb.hosadm.mapper.LogMapper;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-16
 */
public interface LogService
{
	/**
	 * 添加一条日志信息
	 * @param admin 日志的生成者
	 * @param content 日志内容
	 * @return 该操作影响的数据库行数
	 */
	int addLog(Admin admin, String content);

	/**
	 * 分页查询目标部门下的日志
	 * @param adminBelong 部门id
	 * @param page 页号
	 * @param limit 每页数量
	 * @return 日志集合和日志总数
	 */
	LogExecution findAllLog(long adminBelong, int page, int limit);

	/**
	 * 分页查询目标用户生成的日志
	 * @param adminId 用户id
	 * @param page 页号
	 * @param limit 每页数量
	 * @return 日志集合和日志总数
	 */
	LogExecution findMyLog(long adminId, int page, int limit);
}
