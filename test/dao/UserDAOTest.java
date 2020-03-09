package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import vo.UserVO;
import data.Database;

public class UserDAOTest {
	
	// 테스트 메소드 설정 방법
	// 메소드에 @Test 어노테이션을 붙인다. ==> 안붙이면 테스트 메소드로 인식을 못함
	// @Test 어노테이션이 붙은 테스트 메소드는
	// 접근제어자 : public
	// 리턴타입 : void
	// -> 위의 설명은 고정임
	
	//JUnit 프레임워크의 실행순서
	//@Before --> @Test --> @After
	
	UserDAO userDAO;
	
	@Before
	public void setup(){
		userDAO = UserDAO.getInstance();
	}
	
	
	
	// 테스트 메소드 이름 : 운영메소드 명 + Test
	// insertUser 메소드 테스트
	@Test
	public void insertUserTest() {
		System.out.println("DD");
		// insertUser 메소드를 실행하기 위해 필요한 것
		// 1. userDAO 인스턴스 필요
		// 2. insertUser 메소드 인자(UserVO)
		
		
		// 테스트 코드 짜는 형식
		
		// given : 주어진 상황
		UserDAO userDAO = UserDAO.getInstance();
		UserVO userVO = new UserVO();
		
		//사용자로부터 입력받았다고 가정한 값을 userVO에 넣어준다.
		
		userVO.setId("brown");
		userVO.setPw("brown_pass");
		userVO.setName("브라운");
		
		
		// when : 행동(메소드 실행) * 어떤것을 했을 때
		userDAO.insertUser(userVO);
		
		
		// then : 그 결과
		// assertEquals(기대값, 실행구문)
		// 한건의 사용자를 추가 했으므로, tb_user 데이터는 원래한건 + 추가 한건 = 2건이 된다.
		assertEquals(2,Database.getInstance().tb_user.size());
		
	
		
	}
	/**
	 * 
	 */
	@Test
	public void selecUsertListTest(){
		//given
		UserDAO userDAO = UserDAO.getInstance();
		
		
		//when
		ArrayList<UserVO> userList = userDAO.selectUserList();
		
		//then
		assertNotNull(userList);
		assertTrue(userList.size() >=1);
		assertEquals(1, Database.getInstance().tb_user.size());
	}
	
	@Test
	public void selectUserSuccesTest(){
		//given 
		// 1. UserDAO 인스턴스 --> @Before에서 처리
		// 2. 인자 (HashMap<String, String> param)
		HashMap<String, String> paramMap = new HashMap<String,String>();
		
		//Database가 초기화 될때 admin이라는 사용자가 tb_user 리스트에 들어가 있다.
		paramMap.put("ID","admin");
		
		
		//when
		UserVO userVO = userDAO.selectUser(paramMap);
		
		//then
		UserVO expectedUserVO = new UserVO();
		expectedUserVO.setId("admin");
		expectedUserVO.setPw("admin");
		expectedUserVO.setName("관리자");
		
		assertNotNull(userVO);
		assertEquals("admin", userVO.getPw());
		assertEquals("관리자", userVO.getName());
		assertEquals(expectedUserVO, userVO);
		
		
		//객체의 값을 비교하기 위해서는 equals 메소드를 override 해야한다.
		//객체 동일(동일이면 동치), 동치(동치이면 동일?? X)
		//객체 동일, 동치
		//UserVO userVO = new UserVO();
		//UserVO uservo2 = userVO;
		//userVO와 userVO2는 동일
		
		//UserVO userVO = new UserVO();
		//userVO.setId("admin");
		//userVO.setPw("admin");
		//userVO.setName("관리자");
		
		//UserVO userVO2 = new UserVO();
		//userVO2.setId("admin");
		//userVO2.setPw("admin");
		//userVO2.setName("관리자")
		
		// userVO와 userVO2는 같은 값을 갖는다.(동치)
		// if(userVO == userVO2) ==> 동일에 대한 대교
		// if(userVO.equals(userVO2)) ==> 동치에 대한 비교
	
	}
	
	//selectUser 실패 케이스
	@Test
	public void selectUserFailTest(){
		//given
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("ID", "NOT_EXISTS_ID");
		
		//when
		UserVO userVO = userDAO.selectUser(paramMap);

		//then
		assertNull(userVO);
	}
	
	
	
	@Test
	public void selectUserNotExistsKEYTest(){
		//given
				HashMap<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("NONE", "NOT_EXISTS_ID");
				
				//when
				UserVO userVO = userDAO.selectUser(paramMap);

				//then
				assertNull(userVO);
		
	}
	

}
