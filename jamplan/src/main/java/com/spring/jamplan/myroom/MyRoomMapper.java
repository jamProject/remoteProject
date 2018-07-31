package com.spring.jamplan.myroom;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
	@SelectProvider(type = MyRoomSQL.class, method = "getTeamListByID")
	public ArrayList<TeamVO> getTeamListByID(@Param("id") String id);
	
	@SelectProvider(type = MyRoomSQL.class, method ="getPlanListByTeam" )
	public ArrayList<PlanVO> getPlanListByTeam(@Param("TeamVO")ArrayList<TeamVO>teamVO);

	@Select()
	public String getConfirmedDate(CalendarVO calendar);

	@Select()
	public UserVO getUserInfo(String id);
	
	@Override
	public MessageVO getMessageList(String id);

	@Select()
	public TeamVO searchTeam();

	@Select()
	public int makeTeam();

	@Select()
	public int makePlan(UserVO user);

}
