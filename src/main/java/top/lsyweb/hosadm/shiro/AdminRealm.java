package top.lsyweb.hosadm.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.mapper.AdminMapper;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
public class AdminRealm extends AuthorizingRealm
{
	@Autowired
	private AdminMapper adminMapper;

	/**
	 * 执行授权逻辑
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
	{
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getPrincipal();

		// 添加授权字符串
		info.addStringPermission(admin.getAdminType());

		if (admin != null)
			System.out.println("用户 " + admin.getAdminName() + " 执行了授权操作，其权限为：" + admin.getAdminType());

		return info;
	}

	/**
	 * 执行认证逻辑
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException
	{


		// 获取Controller层封装的token
		UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
		// 获取查询出的admin对象
		Admin admin = adminMapper.findAdminByUsername(token.getUsername());
		// 用户名判断
		if (admin == null)
		{
			// 用户名不存在
			return null; // shiro遇到null抛出用户名不存在的异常
		}
		// 用户名存在
		System.out.println("用户 " + admin.getAdminName() + " 执行了认证操作");

		// 密码判断，并将用户实体放入shiro_session中
		return new SimpleAuthenticationInfo(admin, admin.getAdminPassword(), "");
	}
}
