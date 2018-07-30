package com.spring.jamplan.myroom;

import java.util.ArrayList;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;
import com.spring.jamplan.model.PlanVO; 
import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.MessageVO;


/**
 * @author Taehyuk, Kim
 *
 */
public interface MyRoomMapper {
	
	ArrayList<TeamVO> getTeamList(UserVO user);
	ArrayList<PlanVO> getPlanList(UserVO user);
	
	// D-DAY를 계산하기 위해서 날짜 테이블에서 confirmedDate 컬럼을 가져온다.
	String getConfirmedDate(CalendarVO calendar);
	UserVO getUserInfo(String id);
	MessageVO getMessageList(String id);
	
	// 팀이름으로 검색한다.
	ArrayList<TeamVO> searchTeam();
	
	int makeTeam();
	// 팀장만 일정을 추가할 수 있기 때문에 팀장인지 체크한 후, 일정 만들 수 있다.
	int makePlan(UserVO user);
	
	
	
}
