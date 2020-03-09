package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import vo.UserVO;
import dao.UserDAO;
import data.Session;

public class UserService {
	
		// 싱글톤 패턴으로 만들거임
		private static UserService instance;
		
		private UserService(){}
		
		public static UserService getInstance(){
			if(instance == null){
				instance = new UserService();
			}
			return instance;
		}
		
		UserDAO userDao = UserDAO.getInstance();
		
		
		//회원가입
		public void join(){
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("아이디 : ");
			String id = scan.nextLine();
			System.out.println("비밀번호 : ");
			String pw = scan.nextLine();
			System.out.println("이름 : ");
			String name = scan.nextLine();
			
			UserVO user = new UserVO();
			
			user.setId(id);
			user.setPw(pw);
			user.setName(name);
			userDao.insertUser(user);
			System.out.println("가입해주셔서 감사합니다.");
			
		}
		
		public void login(){
			Scanner scan = new Scanner(System.in);
			System.out.println("아이디를 입력해주세요 : ");
			String id = scan.nextLine();
			System.out.println("비밀번호를 입력해주세요 : ");
			String pw = scan.nextLine();
			
			//범용성을 위해 파라미터를 HashMap으로
			HashMap<String, String> param = new HashMap<>();
			param.put("ID", id);
			param.put("PW", pw);
			
			UserVO user = userDao.selectUser(param);
			
			if(user == null){
				System.out.println("아이디 혹은 비밀번호를 잘못입력하셨습니다.");
			}else{
				System.out.println("로그인 성공!");
				System.out.println(user.getName() + "님 환영합니다.");
				Session.loginUser = user;
			}
		}

		public void userList(){
			ArrayList <UserVO> UserList = userDao.selectUserList();
			
			System.out.println("--------------------------------");
			System.out.println("번호\t아이디\t이름");
			System.out.println("--------------------------------");
			for(int i = UserList.size() -1; 0<=i; i--){
				UserVO user = UserList.get(i);
				System.out.println(i + 1 + "\t" + user.getId() + "\t" + user.getName());		
			}
			System.out.println("---------------------------");
		}
}
