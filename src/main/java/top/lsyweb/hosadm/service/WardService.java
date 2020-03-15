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
	List<Ward> queryWardsBeds(int wardBelong);

	void maintainWard(int wardId);
}
