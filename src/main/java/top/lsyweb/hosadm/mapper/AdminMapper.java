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
	Admin findAdminByUsername(String username);

	Admin findAdminById(int id);

	int changeAdminById(Admin admin);
}
