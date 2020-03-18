package top.lsyweb.hosadm.service;

import org.springframework.web.multipart.MultipartFile;
import top.lsyweb.hosadm.domain.Admin;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
public interface AdminService
{
	/**
	 * 根据用户id查询用户对象
	 * @param id 用户id
	 * @return 用户对象
	 */
	Admin findAdminById(int id);

	/**
	 * 更新用户数据
	 * @param admin 封装了部分用户数据的用户对象
	 * @return 更新后的完整用户对象
	 */
	Admin changeAdminById(Admin admin);

	/**
	 * 修改用户头像
	 * @param admin 用户对象，包含了用户id用户修改数据库的src
	 * @param file 图片文件
	 * @return 修改src之后的新对象
	 */
	Admin changeAdminSrcById(Admin admin, MultipartFile file);
}
