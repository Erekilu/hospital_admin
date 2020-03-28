package top.lsyweb.hosadm.mapper;

import org.springframework.stereotype.Component;
import top.lsyweb.hosadm.domain.Admin;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
@Component
public interface AdminMapper
{
	/**
	 * 通过用户用户名来查询目标用户
	 * @param username 用户名
	 * @return 用户对象
	 */
	Admin findAdminByUsername(String username);

	/**
	 * 根据用户id查询用户
	 * @param id 用户id
	 * @return 用户对象
	 */
	Admin findAdminById(int id);

	/**
	 * 改变目标用户的部分属性
	 * @param admin 目标用户，含有id和其他需要修改的属性
	 * @return 该操作影响的数据库条数
	 */
	int changeAdminById(Admin admin);
}
