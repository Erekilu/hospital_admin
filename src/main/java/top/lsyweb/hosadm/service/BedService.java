package top.lsyweb.hosadm.service;

import top.lsyweb.hosadm.domain.Bed;
import top.lsyweb.hosadm.domain.Patient;

import java.util.List;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-14
 */
public interface BedService
{
	/**
	 * 给病人分配病床
	 * @param patient 病人对象
	 * @param bedId 目标病床id
	 * @param wardId 目标病床所在病房id
	 * @throws Exception 冲突
	 */
	void assignBed(Patient patient, int bedId, int wardId) throws Exception;

	/**
	 * 解除病床占用
	 * @param patient
	 * @throws Exception
	 */
	void removeBed(Patient patient) throws Exception;
}
