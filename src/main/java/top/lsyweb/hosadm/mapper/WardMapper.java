package top.lsyweb.hosadm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.lsyweb.hosadm.domain.Ward;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-12
 */
@Component
public interface WardMapper
{
	/**
	 * 查询目标科室的所有病房
	 * @param wardBelong 科室id
	 * @return 满足条件的所有病房
	 */
	List<Ward> queryWards(Long wardBelong);

	/**
	 * 根据病房id查询病房
	 * @param wardId 病床id
	 * @return 病床对象
	 */
	Ward queryWardById(int wardId);

	/**
	 * 修改目标病房的状态（如：拥挤->爆满）
	 * @param wardId 病房id
	 * @param wardStatus 病房新状态
	 * @return 该操作影响的数据库行数
	 */
	int changeStatus(@Param("wardId") int wardId, @Param("wardStatus") String wardStatus);


}
