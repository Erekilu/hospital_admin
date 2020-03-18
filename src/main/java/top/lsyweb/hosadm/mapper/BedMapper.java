package top.lsyweb.hosadm.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.lsyweb.hosadm.domain.Bed;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-13
 */
@Component
public interface BedMapper
{
	/**
	 * 根据病床id查询病床
	 * @param bedId 病床id
	 * @return 病床对象
	 */
	Bed queryBedById(int bedId);

	/**
	 * 查询目标病房下的所有病床
	 * @param wardId 病房id
	 * @return 病床集合
	 */
	List<Bed> queryAllBed(int wardId);

	/**
	 * 改变目标病床的状态（如：未占用 改成 占用）
	 * @param bedId 病床id
	 * @param bedStatus 新状态
	 * @return 该操作影响的数据库行数
	 */
	int changeBedStatus(@Param("bedId") int bedId, @Param("bedStatus") String bedStatus);
}
