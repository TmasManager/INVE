package com.dev.INVE_SVC.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev.INVE_SVC.dao.invMTDao;
import com.dev.INVE_SVC.exception.CustomException;
import com.dev.INVE_SVC.model.Criteria;
import com.dev.INVE_SVC.model.invMTModel;

@Service
@Transactional
public class invMTService {
	
	@Autowired
	invMTDao dao;

	@Transactional
	public List<Map<String, Object>> getList(@RequestBody invMTModel model) throws Exception {
		return dao.getList(model);
	}

	//insert 테스트
	@Transactional
	public Map<String, Object> insert(invMTModel model) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		System.out.println("service.insert 호출");
		int result = 0;
		result = dao.insert(model);
		
		if( result < 1 ) {
			throw new CustomException("err", "등록오류");	
		}

		returnMap.put("code","ok");
		returnMap.put("message","등록완료");
		
		return returnMap;
	}

	//delete 테스트
	public Map<String, Object> delete(invMTModel model) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		System.out.println("service.delete 호출");
		int result = 0;
		result = dao.delete(model);
		
		if(result <1) {
			throw new CustomException("err", "삭제오류");	
		}
		returnMap.put("code","ok");
		returnMap.put("message","삭제완료");
		
		return returnMap;
	}

	//update 테스트
	public  Map<String, Object> update(invMTModel model)  throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		System.out.println("service.update 호출");
		int result = 0;
		result = dao.update(model);
		
		if(result <1) {
			throw new CustomException("err", "업데이트오류");	
		}
		returnMap.put("code","ok");
		returnMap.put("message","업데이트완료");
		
		return returnMap;
	}
	
	//pagingTest 테스트
	@Transactional
	public List<Map<String,Object>> pagingTest(Criteria cri) {
		System.out.println("Service.pagingTest 호출");
		
		return dao.pagingTest(cri);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
