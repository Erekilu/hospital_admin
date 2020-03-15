package top.lsyweb.hosadm.service;

import org.springframework.web.multipart.MultipartFile;
import top.lsyweb.hosadm.domain.Admin;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
public interface AdminService
{
	public Admin findAdminById(int id);

	public Admin changeAdminById(Admin admin);

	public Admin changeAdminSrcById(Admin admin, MultipartFile file);
}
