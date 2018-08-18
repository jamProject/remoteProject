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

	

	@Autowired	
	private ManagePlanDAOService managePlanDAOService;
	
	//test
	@RequestMapping(value="mapPage.mp")
	public String mapPage() {
		return "managePlan/mapPage";
	}
	
	@RequestMapping(value="getPickList.mp", method = RequestMethod.GET,
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
		
	@RequestMapping(value="getAllPickList.mp", method = RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getAllPickList() {		//클래스 타입 확인	
		List<MapVO> allPickList = managePlanDAOService.getAllPickList();
		
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
		
	@RequestMapping(value="insertMember.mp", method = RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object insertMember(MapVO mapVO) {
		int check, pickNum = 0;
		Map<String, Object> retVal = new HashMap<String, Object>(); 
		
		try {
			System.out.println("Checkcontrollerfirst");
			System.out.println(mapVO.getId());
			check = managePlanDAOService.checkPick(mapVO); 	//이 장소를 pick했는지 체크
			 
			if(check==0) {	//pick한 적 없음
				System.out.println("Checkcontroller3");
				System.out.println("mapVO.getId():");
				managePlanDAOService.insertMember(mapVO);		//데이터 삽입
				pickNum=managePlanDAOService.pickCount(mapVO);	//pick한 멤버카운트
				
				mapVO.setPickCount(pickNum);
				managePlanDAOService.updatePickCount(mapVO);	//데이터삽입후 pickCount수정
				
				System.out.println("mapVO.getId()=" + mapVO.getId());
				System.out.println("mapVO.getLocation()=" + mapVO.getLocation());
				System.out.println(pickNum);			
				
				//managePlanDAOService.updatePickCount(mapVO);				
				retVal.put("pickNum", String.valueOf(pickNum));
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
	
	@RequestMapping(value="deleteMember.mp", method = RequestMethod.GET,
			produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteMember(MapVO mapVO) {
		int pickNum, check;
		Map<String, Object> retVal = new HashMap<String, Object>(); 
		
		try {
			System.out.println("vo11.getId()=" + mapVO.getId());
			check = managePlanDAOService.checkPick(mapVO);
			
			if(check==1){	//해당 장소를 pick했을때
				managePlanDAOService.deleteMember(mapVO);	//pick취소
				pickNum = managePlanDAOService.pickCount(mapVO);	//pick한 멤버카운트
				mapVO.setPickCount(pickNum);
				managePlanDAOService.updatePickCount(mapVO);		//데이터삭제후 pickCount수정
				retVal.put("pickNum", String.valueOf(pickNum));		//pickCount를 마커에 집어넣기 위해		
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
