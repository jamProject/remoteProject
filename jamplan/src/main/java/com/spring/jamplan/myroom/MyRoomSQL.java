package com.spring.jamplan.myroom;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.SQL;

import com.spring.jamplan.model.TeamVO;

public class MyRoomSQL {
	public String getTeamListByID(String id) {
	
		String query = new SQL() {
			{
				SELECT("*");
				FROM("TEAM");
				WHERE("ID = #{id}");
			}
		}.toString();
		return query;
	}
	
	public String getPlanListByTeam(ArrayList<TeamVO> teamVO) {

	}
}
