package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.UserVO;
import data.Database;

public class UserDAO {
	private static UserDAO instance;
	
	private UserDAO(){}
	
	static{ // 스테틱 블럭에서 이 클래스가 호출되면 일단 만들어서 실행이 빠르게 두번 되었을때 객체가 두번 생성되는 걸 원천봉쇄하는 코드
		instance = new UserDAO();
	}
	
	public static UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	public void insertUser(UserVO user){
		
		boolean check = true;
		
	for(int i =0 ; i< database.tb_user.size(); i++){
		if(database.tb_user.get(i).getId().equals(user.getId()) ){
			System.out.println("아이디가 이미 있습니다.");
			check = false;
		}
	}
		if(check){
		database.tb_user.add(user);
		}
	}
	

	public UserVO selectUser(HashMap<String, String> param) {
		UserVO rtnUser = null;
		for(int i = 0; i< database.tb_user.size(); i++){
			UserVO user = database.tb_user.get(i);
			boolean flag = true;
			for(String key : param.keySet()){
				String value = param.get(key);
				if(key.equals("ID")){
					if(!user.getId().equals(value)) flag = false;
				}else if(key.equals("PW")){
					if(!user.getPw().equals(value)) flag = false;
				}else if(key.equals("NAME")){
					if(!user.getName().equals(value)) flag = false;
				}else{
					flag = false;
				}
			}
			if(flag) rtnUser = user;
		}
		
		return rtnUser;
		
	}

	public ArrayList <UserVO> selectUserList() {
		
		return database.tb_user;
	}
}
