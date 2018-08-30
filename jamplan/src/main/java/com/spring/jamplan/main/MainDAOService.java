package com.spring.jamplan.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

		
@Service   
public class MainDAOService {
		
	@Autowired
	private SqlSession sqlSession;// mybatis(ibatis) 라이브러리가 제공하는 클래스
	@Autowired
	TeamInfoVO teamVO;
		
	private ArrayList<TeamInfoVO> list;
		
	@Inject
	private JavaMailSender mailSender;
		
	@Transactional
	
	public void sendFindId(String[] id) throws Exception{
	MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
	UserVO vo=mainMapper.getUserInfo(id[0]);
	String email=vo.getEmail();
	MailHandler sendMail = new MailHandler(mailSender);
	sendMail.setSubject("[JAM Planner 아이디 찾기]");	
	sendMail.setFrom("wodud6349@gmail.com", "jamplanner");
	StringBuffer sb=new StringBuffer();
	sb.append("<h4>가입하신 ID LIST 입니다.</h4>");
	for(int i=0; i<id.length; i++)
	{
			sb.append("<br>"+id[i]+"</br>");
	}
	sendMail.setText(sb.toString());
	sendMail.setTo(email);
	sendMail.send();
		}
	public void sendFindPw(UserVO uservo) throws Exception{
		String email=uservo.getEmail();
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[JAM Planner 비밀번호 찾기]");	
		sendMail.setFrom("wodud6349@gmail.com", "jamplanner");
		StringBuffer sb=new StringBuffer();
		sb.append("<h4>가입하신"+uservo.getId()+ "에 대한password 입니다.</h4>");
		sb.append("<br>"+uservo.getPass()+"</br>");
		sendMail.setText(sb.toString());
		sendMail.setTo(email);
		sendMail.send();
			}

	
	public void create(UserVO vo) throws Exception {
	MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);	
	mainMapper.insertUser(vo); //회원가입 
	String key = new TempKey().getKey(50, false); // 인증키 생성
	//키값 이메일값, vo 객체에 저장
	vo.setAuthCode(key);
	mainMapper.createAuthKey(vo); // 인증키 DB저장
	MailHandler sendMail = new MailHandler(mailSender);
	sendMail.setSubject("[JAM Planner 서비스 이메일 인증]");
	
	sendMail.setFrom("wodud6349@gmail.com", "jamplanner");
	sendMail.setTo(vo.getEmail());
	sendMail.setText(
			new StringBuffer().append("<h1>클릭하시면 이메일 인증이 완료됩니다.</h1>")
			.append("<a href='http://localhost:8800/jamplan/emailConfirm.do?email=")
			.append(vo.getEmail()).append("&authCode=")
			.append(key).append("&id=")
			.append(vo.getId())
			.append("' target='_blenk'>이메일 인증 확인</a>")
			.toString());
	sendMail.send();
	}
	
	public String[] getIdInfo(UserVO vo) {
		
		System.out.println("getIdInfo start");
		System.out.println(sqlSession);
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class); 
		
		String[] id = mainMapper.getIdInfo(vo); 
		System.out.println("dao.id"+id);
		return id;
	}	
	public UserVO getUserInfo(String id) {
		
		System.out.println("getUserInfo start");
		System.out.println(sqlSession);
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class); 
		
		
		UserVO vo = mainMapper.getUserInfo(id); 

		return vo;
	}	
		
	public ArrayList<TeamInfoVO> getTeamInfo(String id) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class); 
		list = mainMapper.getTeamInfo(id);
		
		return list;
	}	
		
	public List<PlanVO> getPlanjson() {
		List<PlanVO> planList = null;
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		// System.out.println("planList11="+planList);
		planList = mainMapper.getPlanList();
		//System.out.println("planList(DB갓다오고나서)="+planList);
		return planList;
	}
	public void insertUser(UserVO vo) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		mainMapper.insertUser(vo);
	}
	
	
	public int fileUpload(PlanVO planVO) {
		System.out.println("image");
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		int check = mainMapper.fileUpload(planVO);
		System.out.println("returnfile");
		return check;
	}
	
	public int idcheck(String id) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		int count= mainMapper.idcheck(id);
		return count;
		
	}


	public void update(UserVO vo) {
		MainMapper mainMapper =sqlSession.getMapper(MainMapper.class);
		mainMapper.createAuthKey(vo);
		
	}

	public UserVO checkIdEmail(UserVO vo) {
		// TODO Auto-generated method stub
		MainMapper mainMapper =sqlSession.getMapper(MainMapper.class);
		UserVO uservo = mainMapper.checkIdEmail(vo);
		return uservo;
	}


		
}
		