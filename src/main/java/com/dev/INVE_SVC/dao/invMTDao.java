package com.dev.INVE_SVC.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.dev.INVE_SVC.model.Criteria;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev.INVE_SVC.model.invMTModel;


@Mapper
public interface invMTDao {

	 public List<Map<String, Object>> getList(@RequestBody invMTModel model);

	 //insert 테스트
	public int insert(invMTModel model);

	//delete 테스트
	public int delete(invMTModel model);

	//update 테스트
	public int update(invMTModel model);

	//pagingTest 테스트
	public List<Map<String, Object>> pagingTest(Criteria cri);
	
}
