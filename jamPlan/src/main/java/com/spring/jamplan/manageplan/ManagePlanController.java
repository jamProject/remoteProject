package com.spring.jamplan.manageplan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.MapVO;

@Controller
public class ManagePlanController {

	@Autowired(required = true)
	MapVO mapVO;

	@Autowired	
	private ManagePlanDAOService managePlanDAOService;
	
	//test
	@RequestMapping(value="mapPage.map", method = RequestMethod.GET)
	public String mapPage() {
		return "managePlan/mapPage";
	}
	
	@RequestMapping(value="getPickList.map", method = RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getPickList() {		//클래스 타입 확인
		
		List<MapVO> pickList = managePlanDAOService.getPickList();
		//왜 mapVO안되냐
		String str="";
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(pickList);
			
		}catch(Exception e) {
			System.out.println("first() mapper: " + e.getMessage());
		}
		return str;	
	}
	
	@RequestMapping(value="insertMember.map", method = RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object insertMember(MapVO mapVO) {
		Map<String, Object> retVal = new HashMap<String, Object>(); 
		System.out.println("controller insert");
		try {
			System.out.println("mapVO.getId()=" + mapVO.getId());
			int res = managePlanDAOService.insertMember(mapVO); 
		
			retVal.put("res", "OK"); 
		}
		catch (Exception e)
		{
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		return retVal; 
	}	
	
	@RequestMapping(value="deleteMember.map", method = RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteMember(String id) {
		Map<String, String> retVal = new HashMap<String, String>(); 
		
		try {
			System.out.println("vo11.getId()=" + mapVO.getId());
			int res = managePlanDAOService.deleteMember(id); 
			
			retVal.put("res", "OK"); 
		}
		catch (Exception e)
		{
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		return retVal; 
	}	
}
