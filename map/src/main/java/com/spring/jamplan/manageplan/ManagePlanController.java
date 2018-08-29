package com.spring.jamplan.manageplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.CalendarVO;
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
	
	@RequestMapping(value="getAllPickList.mp", method = RequestMethod.POST,	produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getAllPickList(MapVO mapVO) {		//클래스 타입 확인	
		System.out.println("why");
		List<MapVO> allPickList = managePlanDAOService.getAllPickList(mapVO);
		System.out.println("why2");
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
	
	@RequestMapping(value="getPickList.mp", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getPickList(MapVO mapVO) {		//클래스 타입 확인		
		List<MapVO> pickList = managePlanDAOService.getPickList(mapVO);
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
		
	@RequestMapping(value="insertMember.mp", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> insertMember(MapVO mapVO) {
		int check, pickNum = 0;
		Map<String, Object> retVal = new HashMap<String, Object>(); 
		
		try {
			System.out.println("Checkcontrollerfirst");
			System.out.println(mapVO.getUserColor());
			
			check = managePlanDAOService.checkPick(mapVO); 	//이 장소를 pick했는지 체크
			 
			if(check==0) {	//pick한 적 없음
				System.out.println("Checkcontroller3");
				managePlanDAOService.insertMember(mapVO);		//데이터 삽입
				pickNum=managePlanDAOService.pickCount(mapVO);	//pick한 멤버카운트
				
				mapVO.setPickCount(pickNum);
				managePlanDAOService.updatePickCount(mapVO);	//데이터삽입후 pickCount수정
				
				System.out.println("mapVO.getId()=" + mapVO.getId());
				System.out.println("mapVO.getLocation()=" + mapVO.getPlaceName());
				System.out.println(pickNum);			
				
				//managePlanDAOService.updatePickCount(mapVO);				
				retVal.put("pickNum", pickNum);
				
			}
			else{
				System.out.println("whhathat");
			}
			System.out.println("Checkcontrollersecond");
			
			retVal.put("res", "OK"); 
		}
		catch (Exception e)
		{
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
			System.out.println(retVal);
		}
		return retVal; 
	}	
	
	@RequestMapping(value="deleteMember.mp", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> deleteMember(MapVO mapVO) {
		int pickNum, check;
		Map<String, Object> retVal = new HashMap<String, Object>(); 
		
		try {
			System.out.println("vo11.getId()=" + mapVO.getId());
			check = managePlanDAOService.checkPick(mapVO);
			System.out.println(check);
			if(check==1){	//해당 장소를 pick했을때
				managePlanDAOService.deleteMember(mapVO);	//pick취소
				pickNum = managePlanDAOService.pickCount(mapVO);	//pick한 멤버카운트
				System.out.println("deleteController1");
				mapVO.setPickCount(pickNum);
				managePlanDAOService.updatePickCount(mapVO);		//데이터삭제후 pickCount수정
				System.out.println("deleteController2");
				retVal.put("pickNum", pickNum);		//pickCount를 마커에 집어넣기 위해		
				System.out.println(pickNum);			
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
	
	@RequestMapping(value="confirmPlace.mp", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> confirmPlace(MapVO mapVO, String[] placeList){		
		List<String> confirmPlaceList = new ArrayList<String>();
		Map<String, Object> hm = new HashMap<String, Object>(); 
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		System.out.println("confirmPlaceController");
		try {
			for(String str:placeList) {		//placeList바로 사용못함
				confirmPlaceList.add(str);			
			}
			System.out.println("confirmPlaceList="+confirmPlaceList);
			
			hm.put("confirmPlaceList", confirmPlaceList);
			/*hm.put("planNo", planNoList);
			hm.put("selectDate", selectDateList);*/
			hm.put("planNo", mapVO.getPlanNo());
			hm.put("selectDate", mapVO.getSelectDate());
			
			System.out.println(hm);
			System.out.println("confirmController1");
			managePlanDAOService.confirmPlace(hm);
			System.out.println("confirmController2");
			
			retVal.put("res", "OK");
		}
		catch (Exception e)
		{
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		
		return retVal; 
	}
	
	@RequestMapping(value="resetPlace.mp", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> resetPlace(MapVO mapVO){
		System.out.println("resetplaceController");
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		try {
			managePlanDAOService.resetPlace(mapVO);	
			retVal.put("res", "OK");
		}
		catch (Exception e)
		{
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		
		return retVal;
	}
	
	
	@RequestMapping(value="changeColor.mp", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> changeColor(MapVO mapVO){
		System.out.println("changeColorController");
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		try {
			System.out.println("mapVO.getPlanNo():" + mapVO.getPlanNo() + " mapVO.getUserColor():" + mapVO.getUserColor());
			managePlanDAOService.changeColor(mapVO);	
			retVal.put("res", "OK");
		}
		catch (Exception e)
		{
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		
		return retVal;
	}
	
	/*@RequestMapping(value="getSelectDate.mp", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getSelectDate(MapVO mapVO){
		calendarVO = managePlanDAOService.getSelectDate(mapVO);
		System.out.println("controllergetselectDate");
		String str="";
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("getselectDatecontrolle2");
			str = mapper.writeValueAsString(selectDateList);
			System.out.println("str=" + str);W
		}catch(Exception e) {
			System.out.println("first() mapper: " + e.getMessage());
		}
		return str;	
	}*/
		
}
