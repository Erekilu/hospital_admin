package top.lsyweb.hosadm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.lsyweb.hosadm.domain.Admin;
import top.lsyweb.hosadm.domain.Patient;
import top.lsyweb.hosadm.dto.PatientExecution;
import top.lsyweb.hosadm.mapper.PatientMapper;
import top.lsyweb.hosadm.service.PatientService;
import top.lsyweb.hosadm.util.PathUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-10
 */
@Controller
@RestController
public class PatientController
{
	@Autowired
	private PatientService patientService;

	/**
	 * 添加病人（挂号）
	 * @param patient 病人信息
	 * @param patientUuid 病人头像路径标识
	 * @return
	 */
	@PostMapping("/addPatient")
	public Map<String, Object> addPatient(Patient patient, String patientUuid)
	{
		Map<String, Object> map = new HashMap<>();
		if (patientUuid == null || "".equals(patientUuid))
		{
			map.put("code", -1);
			map.put("msg", "患者图片不能为空！");
			return map;
		}

		// 封装患者图片路径
		patient.setPatientSrc("patient/" + patientUuid);
		// 添加患者
		patientService.addPatient(patient);

		map.put("code", 0);
		return map;
	}

	/**
	 * 上传病人图片
	 *
	 * @param file
	 * @return
	 */
	@PostMapping("/patientUpload")
	public Map<String, Object> patientUpload(MultipartFile file)
	{
		Map<String, Object> map = new HashMap<>();
		if (file != null)
		{

			// 获取上传文件名
			String fileName = file.getOriginalFilename();
			// 获取后缀名
			String sname = fileName.substring(fileName.lastIndexOf("."));
			// 获取完整的文件名
			String fullName = PathUtil.getUuidName(sname);
			// 拼接出本地路径
			String src = "patient/" + fullName;

			try
			{
				// 临时保存到本地
				PathUtil.createThumbnail(file.getInputStream(), PathUtil.getBasePath() + src);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			map.put("patientUuid", fullName);
			map.put("code", 0);
		}
		else
		{
			map.put("code", -1);
			map.put("msg", "文件不能为空");
		}

		return map;
	}

	/**
	 * 分页查询病人信息
	 * @param page 页号
	 * @param limit 每页多少条
	 * @param queryString 查询条件
	 * @return
	 */
	@GetMapping("/getPatients")
	public Map<String, Object> getPatients(int page, int limit, String queryString)
	{
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		Admin sessionAdmin = (Admin)subject.getPrincipal();
		if (sessionAdmin == null) {
			return null;
		}
		// 获取当前admin的部门
		Long departmentId = sessionAdmin.getAdminBelong();
		// 非法参数判断
		if (page < 0 || limit <= 0)
		{
			map.put("code", -1);
			map.put("msg", "查询条件不合法");
			return map;
		}
		// 获取当前部门下的所有病人信息
		PatientExecution patientExecution = patientService.queryPatientByInp(queryString, page, limit, departmentId);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", patientExecution.getCount());
		map.put("data", patientExecution.getPatients());
		return map;
	}
}
