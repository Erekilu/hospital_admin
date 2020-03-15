package top.lsyweb.hosadm.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
@Configuration
public class ShiroConfig
{
	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
	{
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 添加Shiro内置过滤器，设置拦截规则
		Map<String, String> filterMap = new LinkedHashMap<>();
		// 公开页面
		filterMap.put("/login", "anon");
		filterMap.put("/error", "anon");

		// 权限拦截规则
		filterMap.put("/", "perms[admin:assign]");
		filterMap.put("/index", "perms[admin:assign]");
		filterMap.put("/views/record/*", "perms[admin:record]");
		filterMap.put("/views/assign/*", "perms[admin:assign]");

		// 认证拦截规则
		filterMap.put("/views/**", "authc");
		filterMap.put("/adminChange", "authc");
		filterMap.put("/adminInfo", "authc");
		filterMap.put("/adminUpload", "authc");
		filterMap.put("/adminChangePassword", "authc");

		filterMap.put("/logout", "logout");


		// 指定登录页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 指定出错页面
//		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		// 绑定过滤规则
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


		return shiroFilterFactoryBean;
	}

	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean
	public DefaultWebSecurityManager getDefaultWebSecurityManager(AdminRealm adminRealm)
	{
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 关联realm
		defaultWebSecurityManager.setRealm(adminRealm);
		return defaultWebSecurityManager;
	}

	/**
	 * 创建Realm对象
	 */
	@Bean
	public AdminRealm getAdminRealm()
	{
		return new AdminRealm();
	}
}
