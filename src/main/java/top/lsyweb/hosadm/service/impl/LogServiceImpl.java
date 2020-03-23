package top.lsyweb.hosadm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.domain.Log;
import top.lsyweb.hosadm.dto.LogExecution;
import top.lsyweb.hosadm.mapper.LogMapper;
import top.lsyweb.hosadm.service.LogService;
import top.lsyweb.hosadm.util.PathUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-16
 */
@Service
public class LogServiceImpl implements LogService
{
	@Autowired
	private LogMapper logMapper;

	/**
	 * 添加一条日志信息
	 * @param admin 日志的生成者
	 * @param content 日志内容
	 * @return 该操作影响的数据库行数
	 */
	@Override
	public int addLog(Admin admin, String content)
	{
		Log log = new Log();
		log.setLogMaster(admin.getAdminId());
		log.setLogContent(content);
		log.setLogBelong(admin.getAdminBelong());
		log.setLogCreateTime(new Timestamp(new Date().getTime()));
		return logMapper.insertLog(log);
	}

	/**
	 * 分页查询目标部门下的日志
	 * @param adminBelong 部门id
	 * @param page 页号
	 * @param limit 每页数量
	 * @return 日志集合和日志总数
	 */
	@Override
	public LogExecution findAllLog(long adminBelong, int page, int limit)
	{
		LogExecution logExecution = new LogExecution();
		int rowIndex = PathUtil.calculateRowIndex(page, limit);
		logExecution.setLogList(logMapper.queryLogsByDepartment(adminBelong, rowIndex, limit));
		logExecution.setLogCount(logMapper.queryLogsCountByDepartment(adminBelong));
		return logExecution;
	}

	/**
	 * 分页查询目标用户生成的日志
	 * @param adminId 用户id
	 * @param page 页号
	 * @param limit 每页数量
	 * @return 日志集合和日志总数
	 */
	@Override
	public LogExecution findMyLog(long adminId, int page, int limit)
	{
		LogExecution logExecution = new LogExecution();
		int rowIndex = PathUtil.calculateRowIndex(page, limit);
		logExecution.setLogList(logMapper.queryLogsByAdmin(adminId, rowIndex, limit));
		logExecution.setLogCount(logMapper.queryLogsCountByAdmin(adminId));
		return logExecution;
	}
}
