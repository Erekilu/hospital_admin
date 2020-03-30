package top.lsyweb.hosadm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lsyweb.hosadm.service.BedService;
import top.lsyweb.hosadm.service.PatientService;
import top.lsyweb.hosadm.service.WardService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-22
 */
@Controller
@RestController
public class EchartsController
{
	@Autowired
	private PatientService patientService;
	@Autowired
	private BedService bedService;
	@Autowired
	private WardService wardService;

	@GetMapping("/getSexToday")
	public Map<String, Object> getData()
	{
		Map<String, Object> map = patientService.querySexToday();
		map.put("code", 0);
		//
		return map;
	}
}
