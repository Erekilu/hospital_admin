package top.lsyweb.hosadm.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * 应该就叫Util的，本来想着给各个功能分类，但功能实在不多，所以就写到一个工具类里了
 * 包括了获取基路径，生成uuid，图片输出到本地，页号与行号的转化功能
 * @Auther: Erekilu
 * @Date: 2020-03-08
 */
public class PathUtil
{
	/**
	 * 获取本地文件夹基路径
	 * @return
	 */
	public static String getBasePath()
	{
		String os = System.getProperty("os.name");
		String basePath;

		// 判断系统
		if (os.toLowerCase().startsWith("win"))
		{
			basePath = "D:/hospital_admin/";
		}
		else
		{
			basePath = "/hospital_admin/";
		}
		return basePath;
	}

	/**
	 * 用一个uuid做文件名，生成完整的文件名。如：134435sd1f3adfadf5463.jpg
	 * @param sname 文件后缀名
	 * @return
	 */
	public static String getUuidName(String sname)
	{
		// 给图片命名
		String uuid = UUID.randomUUID().toString().replace("-", "");
		// 拼接成完整的文件名
		String fullName = uuid + sname;

		return fullName;
	}

	/**
	 * 将图片流输出到本地
	 * @param source 图片流
	 * @param target 本地路径
	 */
	public static void createThumbnail(InputStream source, String target)
	{
		try
		{
			// 获取图片宽，高
			BufferedImage sourceImage = ImageIO.read(source);
			int width = Math.min(sourceImage.getWidth(), sourceImage.getHeight());
			// 裁剪成一个正方形的图片
			Thumbnails.of(sourceImage)
					.sourceRegion(Positions.CENTER, width, width)
					.size(325, 325)
					.toFile(new File(target));
		}
		catch (Exception e)
		{
			throw new RuntimeException("本地创建图片失败：" + e.toString());
		}
	}

	/**
	 * 将pageIndex转化成rowIndex
	 * @param pageIndex 页号
	 * @param pageSize 每一页的数据量
	 * @return rowIndex
	 */
	public static int calculateRowIndex(int pageIndex, int pageSize)
	{
		return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
	}
}
