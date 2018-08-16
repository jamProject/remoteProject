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
	@RequestMapping(value="mapPage.map")
	public String mapPage() {
		return "managePlan/mapPage";
	}
	
	@RequestMapping(value="getPickList.map", method = RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getPickList(String location) {		//클래스 타입 확인		
		List<MapVO> pickList = managePlanDAOService.getPickList(location);
		System.out.println("controllerPickList");
		String str="";
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("listcontrollerfirst");
			str = mapper.writeValueAsString(pickList);
			System.out.println("listcontrollelast");
			System.out.println("pickList 메소드: " + str);
		}catch(Exception e) {
			System.out.println("first() mapper: " + e.getMessage());
		}
		return str;	
	}
		
	@RequestMapping(value="getAllPickList.map", method = RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getAllPickList() {		//클래스 타입 확인	
		List<MapVO> allPickList = managePlanDAOService.getAllPickList();
		//int pickCount = pickList.length;
		String str="";
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("listcontrollerfirst");
			str = mapper.writeValueAsString(allPickList);
			System.out.println("listcontrollelast");
			System.out.println("allPickList 메소드: " + str);
		}catch(Exception e) {
			System.out.println("first() mapper: " + e.getMessage());
		}
		return str;	
	}	
		
	@RequestMapping(value="insertMember.map", method = RequestMethod.POST,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object insertMember(MapVO mapVO) {
		int check, pickNum = 0, newPickNum=0;
		Map<String, Object> retVal = new HashMap<String, Object>(); 
		
		try {
			System.out.println("Checkcontrollerfirst");
			check = managePlanDAOService.checkPick(mapVO); 	//이 장소를 pick했는지 체크
			 
			if(check==0) {	//pick한 적 없음
				pickNum=managePlanDAOService.pickCount(mapVO);	
				newPickNum = pickNum + 1;
				mapVO.setPickCount(newPickNum);
				managePlanDAOService.insertMember(mapVO);
				
				System.out.println("mapVO.getId()=" + mapVO.getId());
				System.out.println("mapVO.getLocation()=" + mapVO.getLocation());
				System.out.println(newPickNum);			
				
				//managePlanDAOService.updatePickCount(mapVO);				
				retVal.put("newPickNum", String.valueOf(newPickNum));
			}
			System.out.println("Checkcontrollersecond");
			
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
	public Object deleteMember(MapVO mapVO) {
		int pickNum, check;
		Map<String, Object> retVal = new HashMap<String, Object>(); 
		
		try {
			System.out.println("vo11.getId()=" + mapVO.getId());
			check = managePlanDAOService.checkPick(mapVO);
			
			if(check==1){
				managePlanDAOService.deleteMember(mapVO);
				pickNum = managePlanDAOService.pickCount(mapVO);
				mapVO.setPickCount(pickNum);
				managePlanDAOService.updatePickCount(mapVO);
				retVal.put("pickNum", String.valueOf(pickNum));				
			}
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
