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
	List<Ward> queryWards(int wardBelong);

	Ward queryWardById(int wardId);

	int changeStatus(@Param("wardStatus") String wardStatus, @Param("wardId") int wardId);
}
