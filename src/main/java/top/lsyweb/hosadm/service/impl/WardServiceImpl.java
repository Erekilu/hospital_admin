package top.lsyweb.hosadm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.domain.Ward;
import top.lsyweb.hosadm.mapper.BedMapper;
import top.lsyweb.hosadm.mapper.WardMapper;
import top.lsyweb.hosadm.service.WardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-12
 */
@Service
public class WardServiceImpl implements WardService
{
	@Autowired
	private WardMapper wardMapper;
	@Autowired
	private BedMapper bedMapper;

	@Override
	public List<Ward> queryWardsBeds(int wardBelong)
	{
		List<Ward> wards = wardMapper.queryWards(wardBelong);
		return wards;
	}

	/**
	 * 维护目标病房的拥挤状态
	 * @param wardId 目标病房主键
	 */
	@Override
	public void maintainWard(int wardId)
	{
		List<Bed> beds = bedMapper.queryAllBed(wardId);
		int count = 0, all = 0;
		for (Bed bed : beds)
		{
			all++;
			if ("占用".equals(bed.getBedStatus()))
				count++;
		}
		if ((double)count / all >= 0.9)
		{
			wardMapper.changeStatus(wardId, "爆满");
		}
		else if ((double)count / all >= 0.6)
		{
			wardMapper.changeStatus(wardId, "拥挤");
		}
		else
		{
			wardMapper.changeStatus(wardId, "宽松");
		}
	}
}
