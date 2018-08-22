package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.HashMap;
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

	@Autowired
	private SqlSession sqlSession;

	private Map<String, Object> checkMap;
	private MyRoomMapper myRoomMapper;

	@Override
	//receiver의 메세지 테이블을 가져와서  읽음 처리
	public void updateReadMessage(String receiver) {
		System.out.println("==DAO updateReadMessage IN");
		ArrayList<MessageVO>messageList;
		MessageVO vo= new MessageVO();
		vo.setReceiver(receiver);
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			messageList = myRoomMapper.getMessageListById(vo);
			int readCheck = 0;
			System.out.println("==for문 전까지");
			if(messageList.size() != 0) {
				for(int i = 0; i < messageList.size(); i++) {
					if(messageList.get(i).getIsRead() == 0 ||
					   messageList.get(i).getIsRead() == 2 ||
					   messageList.get(i).getIsRead() == 4 ) {
						readCheck= messageList.get(i).getIsRead()+1;
						System.out.println("=="+readCheck);
						messageList.get(i).setIsRead(readCheck);
						myRoomMapper.updateMessage(messageList.get(i));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("==DAO updateReadMessage out");
	}
	
	@Override
	public TeamInfoVO getRole(TeamInfoVO vo) {
		System.out.println("DAO getRole IN");

		TeamInfoVO teamVO = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			teamVO = myRoomMapper.getRole(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DAO getRole out");
		return teamVO;
	}

	@Override
	public ArrayList<TeamInfoVO> getTeamList(String id) {
		System.out.println("DAO getTeamList IN");

		ArrayList<TeamInfoVO> teamList = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			teamList = myRoomMapper.getTeamList(id);
			teamList = deleteValByTeamName(teamList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("DAO getTeamList end");
		return teamList;
	}

	@Override
	public ArrayList<TeamInfoVO> getPlanListById(String id) {
		System.out.println("DAO getPlanListById IN");

		ArrayList<TeamInfoVO> teamList = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			teamList = myRoomMapper.getTeamList(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < teamList.size(); i++) {
			System.out.println(teamList.get(i).getPlanNo());
		}
		System.out.println("DAO getPlanListById Out");
		return teamList;
	}

	@Override
	public void deleteNullPlanTeaminfo(String teamName) {
		System.out.println("DAO getPlanListById IN");
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			myRoomMapper.deleteNullPlan(teamName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("DAO getPlanListById OUT");
	}

	// 처음 myroom으로 진입 시, 같은 팀인지를 확인 후에 웹소켓으로 메시지 뿌려줄 수 있다.
	@Override
	public ArrayList<TeamInfoVO> getTeamMember(UserVO vo) {
		System.out.println("DAO getTeamMember IN");
		ArrayList<TeamInfoVO> teamList = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			teamList = myRoomMapper.getTeamMember(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("DAO getTeamMember Out");
		return teamList;
	}

	@Override
	public ArrayList<PlanVO> getPlanList(TeamInfoVO team) {
		System.out.println("DAO getPlanList IN");
		ArrayList<PlanVO> planList = new ArrayList<PlanVO>();
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			planList = myRoomMapper.getPlanList(team);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("DAO getPlanList OUT");
		return planList;
	}

	// 일정에서 변동사항이 있는지 확인한다.
	@Override
	public ArrayList<PlanVO> checkUpdate(UserVO vo) {
		System.out.println("DAO checkUpdate IN");
		PlanVO plan = null;
		ArrayList<PlanVO> planList = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			ArrayList<PlanUpdateVO> updateList = myRoomMapper.checkUpdate(vo);
			planList = new ArrayList<PlanVO>();

			if (updateList != null) {
				// 변동사항 있는 일정들의 번호를 불러온다.
				for (PlanUpdateVO planUpdate : updateList) {
					// 불러온 일정번호들을 하나하나 plan의 planNo에 맵핑.
					plan.setPlanNo(planUpdate.getPlanNo());
					// 맵핑된 plan으로 해당 planNo를 가진 일정을 찾는다.
					planList.add(searchPlan(plan));
				}
			} else {
				planList.add(null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("DAO checkUpdate Out");
		return planList;
	}

	@Override
	public UserVO getUserInfo(UserVO vo) {
		System.out.println("DAO getUserInfo in");
		UserVO userVO = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			userVO = myRoomMapper.getUserInfo(vo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("DAO getUserInfo out");
		return userVO;
	}

	@Override
	public ArrayList<MessageVO> getMessageList(MessageVO vo) {
		System.out.println("DAO getMessageList in");

		ArrayList<MessageVO> messageList = null;
		try {
			messageList = myRoomMapper.getMessageListById(vo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("DAO getMessageList out");
		return messageList;
	}

	@Override
	public int insertApplyMessage(String id, MessageVO message) {
		System.out.println("DAO insertApplyMessage in");
		// 이미 가입된 팀 0, 이미 신청함 1, 신청 저장 2;
		int check = 0;
		String sender = id;
		String teamName = message.getTeamName();
		TeamInfoVO team1 = null;
		ArrayList<TeamInfoVO> teamList = null;
		message.setSender(sender);
		System.out.println("insertApplyMessage teamName :" + teamName);
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			// 신청자가 해당 팀에 이미 신청했는지를 판단
			team1 = new TeamInfoVO();
			team1.setId(id);
			team1.setTeamName(teamName);
			teamList = myRoomMapper.getTeamInfo(team1);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		if (teamList.size() != 0) {
			System.out.println("insertApplyMessage : 이미 팀원임");
			check = 0;
		} else {
			System.out.println("insertApplyMessage : 팀원은 아님");
			// 이전에 신청을 했는지 확인
			ArrayList<MessageVO> messageList = myRoomMapper.checkApplyMessage(message);

			if (messageList.size() != 0) {
				System.out.println("insertApplyMessage : 근데 이미 신청 함");
				check = 1;
			} else {
				check = 2;
				System.out.println("insertApplyMessage : 팀원도 아니고 신청도 안함");
				String receiver = "";
				team1 = new TeamInfoVO();
				team1.setTeamName(teamName);
				team1.setRole(0);
				try {
					// 받는 사람을 설정
					teamList = myRoomMapper.getTeamReceiver(team1);
					receiver = teamList.get(0).getId();
					System.out.println("insertApplyMessage 리더 아이디 가져오기 성공");
				} catch (Exception e) {
					System.out.println("insertApplyMessage 리더 아이디 가져오기 실패");
					e.printStackTrace();
				}
				// 0일때 읽지 않은 메세지
				message.setIsRead(0);
				message.setReceiver(receiver);
				message.setSender(sender);
				message.setTeamName(teamName);

				try {
					// 메세지 디비에 해당 신청 메세지 저장
					myRoomMapper.insertApplyMessage(message);
					System.out.println("insertApplyMessage 삽입 성공");
				} catch (Exception e) {
					System.out.println("insertApplyMessage 삽입 실패");
					e.printStackTrace();
				}
			}
		}
		System.out.println("DAO insertApplyMessage IN");
		return check;
	}

	@Override
	public int deleteCansleMessage(MessageVO vo) {
		System.out.println("DAO deleteCansleMessage in");

		ArrayList<TeamInfoVO> teamList = null;
		// 이미 가입된 팀 0, 이미 신청함 1, 신청 저장 2;
		int check = 0;
		String sender = vo.getSender();
		String teamName = vo.getTeamName();
		System.out.println("deleteCansleMessage teamName" + teamName);

		// 신청자가 해당 팀에 이미 신청했는지를 판단
		TeamInfoVO team1 = new TeamInfoVO();
		team1.setId(sender);
		team1.setTeamName(teamName);

		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			teamList = myRoomMapper.getTeamInfo(team1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (teamList.size() != 0) {
			System.out.println("deleteCansleMessage : 이미 팀원임");
			check = 0;
		} else {
			System.out.println("deleteCansleMessage : 팀원은 아님");
			// 이전에 신청을 했는지 확인
			ArrayList<MessageVO> messageList = myRoomMapper.checkApplyMessage(vo);

			if (messageList.size() != 0) {
				System.out.println("deleteCansleMessage : 근데 이미 신청 함");
				try {
					myRoomMapper.deleteCansleMessage(vo);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("신청 데이터 지움");
				check = 1;
			} else {
				System.out.println("팀원도 아니고 신청도 안함");
				check = 2;
			}
		}
		System.out.println("DAO deleteCansleMessage out");
		return check;
	}

	@Override
	public ArrayList<TeamInfoVO> searchTeam(TeamInfoVO team) {
		System.out.println("DAOS searchTeam IN");
		ArrayList<TeamInfoVO> teamInfo = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			teamInfo = myRoomMapper.searchTeam(team);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		if (teamInfo.get(0).getTeamName() != null) {
			System.out.println("searchTeam : teamInfo에 데이터 저장됨");
			System.out.println(teamInfo.get(0).getTeamName());
			System.out.println("searchTeam SUCCESS");
			// 중복된 팀명 제거 - plan에 의해 같은 팀명이 중복이 됨
			try {
				teamInfo = deleteValByTeamName(teamInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("DAOS searchTeam out NULL");
			teamInfo = null;
		}
		System.out.println("DAOS searchTeam out");
		return teamInfo;
	}

	// 변동된 일정을 찾기 위한 메서드
	@Override
	public PlanVO searchPlan(PlanVO plan) {
		System.out.println("DAOS searchPlan in");
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			if (myRoomMapper.searchPlan(plan) != null) {
				plan = myRoomMapper.searchPlan(plan);
			} else {
				plan = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			plan = null;
		}
		System.out.println("DAOS searchPlan out");
		return plan;
	}

	@Override
	public int makeTeam(TeamInfoVO team) {
		System.out.println("makeTeam IN");
		System.out.println(team.getId());
		int check = 0;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			check = myRoomMapper.makeTeam(team);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("DAO makeTeam OUT");
		return check;
	}

	@Override
	public int insertPlan(TeamInfoVO vo) {
		System.out.println("DAO insertPlan IN");
		int check = 0;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			myRoomMapper.insertPlan(vo);
			check = 1;
			System.out.println("insertPlan : insert suc");
			// 성공시 1
		} catch (Exception e) {
			// TODO: handle exception
			// 실패시 0;
			System.out.println("insertPlan : insert fail");
			check = 0;
		}
		System.out.println("insertPlan out");
		return check;
	}

	@Override
	public ArrayList<TeamInfoVO> getTeamInfo(TeamInfoVO teamVO) {
		System.out.println("getTeamInfo in");
		ArrayList<TeamInfoVO> vo = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			vo = myRoomMapper.getTeamInfo(teamVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getTeamInfo out");
		return vo;
	}

	@Override
	public int getMaxPlanNo() {

		System.out.println("DAO getMaxPlanNo in");
		Object maxNo = null;
		int max = 0;

		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			maxNo = myRoomMapper.getMaxPlanNo();

			if (maxNo == null) {
				max = 0;
			} else {
				max = (int) maxNo;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("DAO getMaxPlanNo out");
		return max;
	}

	@Override
	public String validationTeamName(TeamInfoVO team) {

		System.out.println("DAO validatinoTeamName IN");
		String validationTeamName = null;
		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);

			if (myRoomMapper.validationTeamName(team) != null) {
				System.out.println("DAOService method validationTeamName out FAIL");
				validationTeamName = "FAIL";
				return validationTeamName;
			} else {
				validationTeamName = "SUCCESS";
				System.out.println("DAOService method validationTeamName out SUCCESS");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("DAO validatinoTeamName out");
		return validationTeamName;
	}

	@Override
	public Object deleteTeam(TeamInfoVO team) {
		System.out.println("DAO deleteTeam int");

		checkMap = new HashMap<String, Object>();
		int check = 0;

		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			check = myRoomMapper.deleteTeam(team);
			if (check == 1) {
				checkMap.put("check", Integer.valueOf(check));
			} else {
				checkMap.put("check", null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("DAO deleteTeam out");
		return checkMap;
	}

	@Override
	public Object deletePlan(PlanVO plan) {
		System.out.println("DAO deletePlan int");
		checkMap = new HashMap<String, Object>();

		int check = 0;

		try {
			myRoomMapper = sqlSession.getMapper(MyRoomMapper.class);
			check = myRoomMapper.deletePlan(plan);
			if (check == 1) {
				checkMap.put("check", Integer.valueOf(check));
			} else {
				checkMap.put("check", null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("DAO deletePlan out");
		return checkMap;
	}

	public ArrayList<TeamInfoVO> deleteValByTeamName(ArrayList<TeamInfoVO> teamList) {
		System.out.println("DAO deleteValByTeamName method in");
		int k = 0;
		int size = teamList.size();
		// 팀명을 출력 할 때 중복 vo 중복 제거
		for (int i = 0; i < teamList.size(); i++) {
			for (int j = 0; j < teamList.size(); j++) {
				if (i != j) {
					if (teamList.get(i).getTeamName().equals(teamList.get(j).getTeamName())) {
						// System.out.println("remove : " + voList.get(j).getSelectDate());
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
		System.out.println("DAO deleteValByTeamName method out");
		return teamList;
	}

}
