package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	public TeamInfoVO getRole(TeamInfoVO vo) {
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		TeamInfoVO teamVO = myRoomMapper.getRole(vo);
		
		return teamVO;
	}
	
	@Override
	public ArrayList<TeamInfoVO> getTeamList(String id) {
		System.out.println(id);
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		System.out.println("mapper suc");
		ArrayList<TeamInfoVO> teamList = myRoomMapper.getTeamList(id);
		teamList = deleteValByTeamName(teamList);
		/*int k = 0;
		int size = teamList.size();
		//팀명을 출력 할  때 중복 vo 중복 제거
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
		}*/
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
	public ArrayList<TeamInfoVO> getTeamMember(UserVO vo) {
		System.out.println("a");
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		ArrayList<TeamInfoVO> teamList = myRoomMapper.getTeamMember(vo);

		return teamList;
	}
	

	@Override
	public ArrayList<PlanVO> getPlanList(TeamInfoVO team) {
		System.out.println("DAOService getPlanList method IN");
		ArrayList<PlanVO> planList = new ArrayList<PlanVO>();
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
	public int insertApplyMessage( String id, MessageVO message) {
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		//이미 가입된 팀 0, 이미 신청함 1, 신청 저장 2;
		int check = 0;
		System.out.println("insertApplyMessage 진입");
		String sender = id;
		String teamName = message.getTeamName();
		message.setSender(sender);
		System.out.println(teamName);
		
		//신청자가 해당 팀에 이미 신청했는지를 판단
		TeamInfoVO team1 = new TeamInfoVO();
		team1.setId(id);
		team1.setTeamName(teamName);
		ArrayList<TeamInfoVO> teamList = myRoomMapper.getTeamInfo(team1);
		
		if(teamList.size()!=0 ) {
			System.out.println("이미 팀원임");
			check = 0;
		}else {
			System.out.println("팀원은 아님");
			//이전에 신청을 했는지 확인
			ArrayList<MessageVO> messageList = myRoomMapper.checkApplyMessage(message);
			
			if(messageList.size() !=0) {
				System.out.println("근데 이미 신청 함");
				check =1;
			}else {
				check =2;
				System.out.println("팀원도 아니고 신청도 안함");
				String receiver="";
				team1 =new TeamInfoVO();	
				team1.setTeamName(teamName);
				team1.setRole(0);
				try {
					//받는 사람을 설정
					teamList = myRoomMapper.getTeamReceiver(team1);
					receiver = teamList.get(0).getId();
					System.out.println("insertApplyMessage 리더 아이디 가져오기 성공");
				}catch (Exception e) {
					System.out.println("insertApplyMessage 리더 아이디 가져오기 실패");
				}	
				//1일때 읽지 않은 메세지
				message.setIsRead(1);
				message.setReceiver(receiver);
				message.setSender(sender);
				message.setTeamName(teamName);
				
				try {
					//메세지 디비에 해당 신청 메세지 저장
					myRoomMapper.insertApplyMessage(message);
					System.out.println("insertApplyMessage 삽입 성공");
				}catch (Exception e) {
					System.out.println("insertApplyMessage 삽입 실패");
				}
			}
		}		
		return check;
	}
	
	@Override
	public int deleteCansleMessage(String id, MessageVO vo) {
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		//이미 가입된 팀 0, 이미 신청함 1, 신청 저장 2;
		int check = 0;
		System.out.println("deleteCansleMessage 진입");
		String sender = id;
		String teamName = message.getTeamName();
		message.setSender(sender);
		System.out.println(teamName);
		
		//신청자가 해당 팀에 이미 신청했는지를 판단
		TeamInfoVO team1 = new TeamInfoVO();
		team1.setId(id);
		team1.setTeamName(teamName);
		ArrayList<TeamInfoVO> teamList = myRoomMapper.getTeamInfo(team1);
		
		if(teamList.size()!=0 ) {
			System.out.println("이미 팀원임");
			check = 0;
		}else {
			System.out.println("팀원은 아님");
			//이전에 신청을 했는지 확인
			ArrayList<MessageVO> messageList = myRoomMapper.checkApplyMessage(message);
			
			if(messageList.size() !=0) {
				System.out.println("근데 이미 신청 함");
				myRoomMapper.deleteCansleMessage(vo);
				System.out.println("신청 데이터 지움");
				check =1;
			}else {
				System.out.println("팀원도 아니고 신청도 안함");
				check =2;
			}
		}
		return check;
	}
	
	@Override
	public ArrayList<TeamInfoVO> searchTeam(TeamInfoVO team) {
		
		System.out.println("DAOService method searchTeam IN");
		MyRoomMapper myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
		
		ArrayList<TeamInfoVO> teamInfo = myRoomMapper.searchTeam(team);
		System.out.println("mapper out");
		if(teamInfo.get(0).getTeamName() != null) {
			System.out.println("teamInfo에 데이터 저장됨");
			System.out.println(teamInfo.get(0).getTeamName());
			System.out.println("DAOService method searchTeam out SUCCESS");
			//중복된 팀명 제거 - plan에 의해 같은 팀명이 중복이 됨
			teamInfo = deleteValByTeamName(teamInfo);
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
	
	public ArrayList<TeamInfoVO> deleteValByTeamName(ArrayList<TeamInfoVO> teamList) {
		int k = 0;
		int size = teamList.size();
		//팀명을 출력 할  때 중복 vo 중복 제거
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
	
}
