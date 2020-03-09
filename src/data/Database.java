package data;

import java.util.ArrayList;

import vo.UserVO;

public class Database {
	
private static Database instance;
	
	private Database(){}
	
	public static Database getInstance(){
		if(instance == null){
			instance = new Database();
		}
		return instance;
	}
	
	public ArrayList<UserVO> tb_user = new ArrayList<>(); // user 테이블이 되는 거
	{
		UserVO user = new UserVO();
		user.setId("admin");
		user.setPw("admin");
		user.setName("관리자");
		tb_user.add(user);
	}
	
}
