package dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import vo.BusVO;

public class BusDAOTest {
	// 	역으로 테스트 데이터를 통해 운영데이터를 만드는 과정
	// TDD : Test Driven Development
	
	@Test
	public void BusListtest() {
		//given
		BusDAO busDAO = new BusDAO();
		
		//when
		List<BusVO> busList = busDAO.busList();
		
		//then
		assertNotNull(busList);
	}

}
