package top.lsyweb.hosadm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.domain.Ward;
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

	@Override
	public List<Ward> queryWardsBeds(int wardBelong)
	{
		List<Ward> wards = wardMapper.queryWards(wardBelong);
		return wards;
	}

	@Override
	public Ward queryWardById(int wardId)
	{
		return wardMapper.queryWardById(wardId);
	}

	@Override
	public int changeStatus(int wardId, String wardStatus)
	{
		return wardMapper.changeStatus(wardStatus, wardId);
	}
}
