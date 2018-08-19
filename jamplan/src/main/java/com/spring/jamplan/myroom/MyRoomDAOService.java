package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanUpdateVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Service
public class MyRoomDAOService implements MyRoomDAO {
	
	@Autowired(required=false) 
	private SqlSession sqlSession;
	
	@Autowired(required=false)
	private MessageVO message;
	
	// 변동사항 발생한 일정을 검색하기 위한 bean 객체 생성
	@Autowired(required=false)
	private PlanVO plan;
	
/*	@Autowired
	private TeamInfoVO vo;
*/
	@Override
	public ArrayList<TeamInfoVO> getTeamList(String id) {
		System.out.println(id);
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		System.out.println("mapper suc");
		ArrayList<TeamInfoVO> teamList = myRoomMapper.getTeamList(id);
		
		int k = 0;
		int size = teamList.size();
		//일정을 여러명이 선택 했을 때 중복 제거하고 +n 카운트 하나만 보이도록 
		for (int i = 0; i < teamList.size(); i++) {
			for (int j = 0; j < teamList.size(); j++) {
				if (i != j) {
					if (teamList.get(i).getTeamName().equals(teamList.get(j).getTeamName())) {
						//System.out.println("remove : " + voList.get(j).getSelectDate());
						teamList.remove(j);
						k++;
						i = 0;
						break;
					}
				}
			}
			if (i > size - k) {
				break;
			}
		}
		return teamList;
	}
	@Override
	public  ArrayList<TeamInfoVO> getPlanListById(String id){
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		System.out.println("getPlanListById start");
		ArrayList<TeamInfoVO> teamList = myRoomMapper.getTeamList(id);
		
		for(int i = 0; i < teamList.size(); i++) {
			System.out.println(teamList.get(i).getPlanNo());
		}
		
		return teamList;
	}
	
	@Override
	public void deleteNullPlanTeaminfo(String teamName) {
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		myRoomMapper.deleteNullPlan(teamName);
	}
	
	// 처음 myroom으로 진입 시, 같은 팀인지를 확인 후에 웹소켓으로 메시지 뿌려줄 수 있다.
	@Override
	public List<TeamInfoVO> getTeamMember(UserVO vo) {
		System.out.println("a");
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		List<TeamInfoVO> teamList = myRoomMapper.getTeamMember(vo);

		return teamList;
	}
	

	@Override
	public List<PlanVO> getPlanList(TeamInfoVO team) {
		System.out.println("DAOService getPlanList method IN");
		List<PlanVO> planList = new ArrayList<PlanVO>();
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		planList = myRoomMapper.getPlanList(team);
		System.out.println("DAOService getPlanList method OUT");
		return planList;
	}

	
	// 일정에서 변동사항이 있는지 확인한다.
	@Override
	public ArrayList<PlanVO> checkUpdate(UserVO vo) {
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		List<PlanUpdateVO> updateList = myRoomMapper.checkUpdate(vo);
		ArrayList<PlanVO> planList = new ArrayList<PlanVO>();
	
		if(updateList != null) {
			//변동사항 있는 일정들의 번호를 불러온다.
			for(PlanUpdateVO planUpdate : updateList) {
				// 불러온 일정번호들을 하나하나 plan의 planNo에 맵핑.
				plan.setPlanNo(planUpdate.getPlanNo());
				// 맵핑된 plan으로 해당 planNo를 가진 일정을 찾는다.
				planList.add(searchPlan(plan));
			}
		}else {
			planList.add(null);
		}
		
		return planList;
	}


	@Override
	public UserVO getUserInfo(UserVO vo) {
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		UserVO userVO = myRoomMapper.getUserInfo(vo);
		return userVO;
	}

	@Override
	public List<TeamInfoVO> searchTeam(TeamInfoVO team) {
		
		System.out.println("DAOService method searchTeam IN");
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		
		List<TeamInfoVO> teamInfo = myRoomMapper.searchTeam(team);
		System.out.println("mapper out");
		if(teamInfo.get(0).getTeamName() != null) {
			System.out.println("teamInfo에 뭔가가 담겨있음");
			System.out.println(teamInfo.get(0).getTeamName());
			System.out.println("DAOService method searchTeam out SUCCESS");
			return teamInfo;
		}else {
			System.out.println("DAOService method searchTeam out NULL");
			return null;
		}
	}
	
	// 변동된 일정을 찾기 위한 메서드
	@Override
	public PlanVO searchPlan(PlanVO plan) {
		
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		
		if(myRoomMapper.searchPlan(plan) != null) {
			plan = myRoomMapper.searchPlan(plan);
		}else {
			plan = null;
		}
		return plan;
	}

	@Override
	public int makeTeam(TeamInfoVO team) {
		System.out.println("makeTeam IN");
		System.out.println(team.getId());
		int check;
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		check = myRoomMapper.makeTeam(team);
		System.out.println("makeTeam OUT");
		return check;
	}

	@Override
	public int insertPlan(TeamInfoVO vo) {
		int check =0;
		try {
			MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			myRoomMapper.insertPlan(vo);
			check =1;
			System.out.println("plan insert suc");
			//성공시 1
		}catch (Exception e) {
			// TODO: handle exception
			//실패시 0;
			System.out.println("plan insert fail");
			check = 0;
		}
		
		return check;
	}
	
	@Override
	public ArrayList<TeamInfoVO> getTeamInfo (TeamInfoVO teamVO) {
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		ArrayList<TeamInfoVO> vo = myRoomMapper.getTeamInfo(teamVO);
		return vo;
	}
	
	@Override
	public int getMaxPlanNo() {
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		Object maxNo = myRoomMapper.getMaxPlanNo();
		int max =0;
		
		if( maxNo == null) 
		{
			max = 0;
		}else {
			max = (int)maxNo;
		}
		return max;
	}
	
	@Override
	public String validationTeamName(TeamInfoVO team) {
		
		System.out.println("DAOService method validatinoTeamName IN");
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		String validationTeamName = null;
	
		if(myRoomMapper.validationTeamName(team) != null) {
			System.out.println("DAOService method validationTeamName out FAIL");
			validationTeamName = "FAIL";
			return validationTeamName;
		}else {
			validationTeamName = "SUCCESS";
			System.out.println("DAOService method validationTeamName out SUCCESS");
			
		}
		return validationTeamName;
	}

	@Override
	public Object deleteTeam(TeamInfoVO team) {
		Map<String, Object> checkMap = new HashMap<String, Object>();
		
		int check;
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		check = myRoomMapper.deleteTeam(team);
		
		if(check == 1) {
			checkMap.put("check", Integer.valueOf(check));
		} else {
			checkMap.put("check", null);
		}
		
		return checkMap;
	}

	@Override
	public Object deletePlan(PlanVO plan) {
		Map<String, Object> checkMap = new HashMap<String, Object>();
		
		int check;
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		check = myRoomMapper.deletePlan(plan);
		
		if(check == 1) {
			checkMap.put("check", Integer.valueOf(check));
		} else {
			checkMap.put("check", null);
		}
		
		return checkMap;
	}
}
