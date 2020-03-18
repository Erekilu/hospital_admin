package top.lsyweb.hosadm.service;

import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.domain.Ward;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-12
 */
public interface WardService
{
	/**
	 * 查询目标科室下的所有病房
	 * @param wardBelong 科室id
	 * @return 病房集合
	 */
	List<Ward> queryWardsBeds(int wardBelong);

	/**
	 * 维护目标病房的拥挤状态
	 * @param wardId 病房id
	 */
	void maintainWard(int wardId);
}
