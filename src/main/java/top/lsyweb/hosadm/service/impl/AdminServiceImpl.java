package top.lsyweb.hosadm.service.impl;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.mapper.AdminMapper;
import top.lsyweb.hosadm.service.AdminService;
import top.lsyweb.hosadm.util.PathUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-07
 */
@Component
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminMapper adminMapper;

	/**
	 * 根据用户id查询用户对象
	 * @param id 用户id
	 * @return 用户对象
	 */
	@Override
	public Admin findAdminById(int id)
	{
		return adminMapper.findAdminById(id);
	}

	/**
	 * 更新用户数据
	 * @param admin 封装了部分用户数据的用户对象
	 * @return 更新后的完整用户对象
	 */
	@Override
	public Admin changeAdminById(Admin admin)
	{
		admin.setAdminLastEditTime(new Timestamp(new Date().getTime()));
		adminMapper.changeAdminById(admin);
		return adminMapper.findAdminById(admin.getAdminId().intValue());
	}

	/**
	 * 修改用户头像
	 * @param admin 用户对象，包含了用户id用户修改数据库的src
	 * @param file 图片文件
	 * @return 修改src之后的新对象
	 */
	@Override
	public Admin changeAdminSrcById(Admin admin, MultipartFile file)
	{
		// 此时的admin对象只拥有admin_id和admin_src属性
		// 先删掉本地已有的图片
		File oldfile = new File(PathUtil.getBasePath() + admin.getAdminSrc());
		if (oldfile.exists())
		{
			// 如果本地图片存在，删除它
			oldfile.delete();
		}
		// 使用thumbnailator处理file，并保存到本地
		//获取上传文件名
		String fileName = file.getOriginalFilename();
		//获取后缀名
		String sname = fileName.substring(fileName.lastIndexOf("."));
		// 获取完整的文件名
		String fullName = PathUtil.getUuidName(sname);
		// 拼接路径名(放到数据库中的)
		String src = "admin/" + admin.getAdminId() + "/" + fullName;
		try
		{
			PathUtil.createThumbnail(file.getInputStream(), PathUtil.getBasePath() + src);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		admin.setAdminSrc(src);
		admin.setAdminLastEditTime(new Timestamp(new Date().getTime()));
		// 修改数据库信息
		adminMapper.changeAdminById(admin);
		return adminMapper.findAdminById(admin.getAdminId().intValue());
	}
}
