package top.lsyweb.hosadm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.mapper.BedMapper;
import top.lsyweb.hosadm.service.BedService;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-14
 */
@Service
public class BedServiceImpl implements BedService
{
	@Autowired
	private BedMapper bedMapper;

	@Override
	public Bed findBedById(int bedId)
	{
		return bedMapper.queryBedById(bedId);
	}

	@Override
	public int changeBedStatus(int bedId, String bedStatus)
	{
		return bedMapper.changeBedStatus(bedId, bedStatus);
	}

	@Override
	public List<Bed> queryBedsByWardId(int wardId)
	{
		return bedMapper.queryAllBed(wardId);
	}
}
