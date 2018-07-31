package com.spring.jamplan.myroom;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;


/**
 * @author Taehyuk, Kim
 *
 */
@Mapper
@Repository
public interface MyRoomMapper {
	// 팀리스트 보여주는 부분
	@SelectProvider(type=MyRoomSQL.class, method="getTeamList")
	ArrayList<TeamVO> getTeamList(@Param("user") UserVO user);
	
	// 팀 내 일정 보여주는 부분
	@SelectProvider(type=MyRoomSQL.class, method="getPlanList")
	ArrayList<PlanVO> getPlanList(@Param("user") UserVO user);
	
	// D-DAY를 계산하기 위해서 날짜 테이블에서 confirmedDate 컬럼을 가져온다.
	@SelectProvider(type=MyRoomSQL.class, method="getConfirmedDate")
	String getConfirmedDate(@Param("calendar") CalendarVO calendar);
	
	// 내 정보 아이콘 눌렀을 경우
	@SelectProvider(type=MyRoomSQL.class, method="getUserInfo")
	UserVO getUserInfo(@Param("user") UserVO user);
	
	// 메시지함 눌렀을 경우
	@SelectProvider(type=MyRoomSQL.class, method="getMessageList")
	MessageVO getMessageList(@Param("user") UserVO user);
	
	// 팀이름으로 검색한다.
	@SelectProvider(type=MyRoomSQL.class, method="searchTeam")
	TeamVO searchTeam(@Param("team") TeamVO team);
	
	@SelectProvider(type=MyRoomSQL.class, method="makeTeam")
	int makeTeam(@Param("user") UserVO user);
	
	// 팀장만 일정을 추가할 수 있기 때문에 팀장인지 체크한 후, 일정 만들 수 있다.
	@SelectProvider(type=MyRoomSQL.class, method="makePlan")
	int makePlan(@Param("user") UserVO user);
	
	
	
}
