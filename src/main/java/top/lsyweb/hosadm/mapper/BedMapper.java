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
	Bed queryBedById(int bedId);

	List<Bed> queryAllBed(int wardId);

	int changeBedStatus(@Param("bedId") int bedId, @Param("bedStatus") String bedStatus);
}
