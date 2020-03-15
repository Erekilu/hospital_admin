package top.lsyweb.hosadm.service;

import top.lsyweb.hosadm.domain.Bed;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-14
 */
public interface BedService
{
	Bed findBedById(int bedId);

	int changeBedStatus(int bedId, String bedStatus);

	List<Bed> queryBedsByWardId(int wardId);
}
