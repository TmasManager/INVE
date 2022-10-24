package com.dev.INVE_SVC.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dev.INVE_SVC.model.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.INVE_SVC.model.invMTModel;
import com.dev.INVE_SVC.repository.invMTRepository;
import com.dev.INVE_SVC.service.invMTService;

@RestController
@RequestMapping(value = "/item")
public class invMTController {
	
	@Autowired
	invMTService service;
	
	@Autowired
	invMTRepository repository;

	@GetMapping("/get")
	public void test(@RequestBody String test) {
		System.out.println(test);
	}

	//select 테스트
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> getList(@RequestBody invMTModel model) throws Exception {
		System.out.println("Controller.getList 호출 ");
		return ResponseEntity.ok(service.getList(model));
		
	}
	
	//JPA select 테스트
	@RequestMapping(value = "/jpaGetList", method = RequestMethod.GET)
	public ResponseEntity<List<invMTModel>> jpaGetList(@RequestBody invMTModel model) throws Exception {
		System.out.println("Controller.JPA_getList 호출");
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	//insert 테스트
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> insert(invMTModel model) throws Exception {
		model.setTESTITIMPARTNO("51");
		model.setTESTITIMCORPCD("test51");
		System.out.println("model : "+ model);
		System.out.println("테스트중 : "+model.getTESTITIMPARTNO());
		System.out.println("Controller.insert 호출");
		return ResponseEntity.ok(service.insert(model));
		
	}	
	
	//delete 테스트
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> delete(invMTModel model) throws Exception {
		System.out.println("Controller.delete 호출");
		model.setTESTITIMQTYAVAIL(0);	
		return ResponseEntity.ok(service.delete(model));		
	}
	
	//update 테스트
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> update(invMTModel model) throws Exception {
		System.out.println("Controller.update 호출");
		
		model.setTESTITIMIDX("45");
		model.setTESTITIMPARTNO("test_PART_NO45");
		model.setTESTITIMCORPCD("test_CORP_CD45");
		model.setTESTITIMBSNSCD("test_BSNS_CD45");
		model.setTESTITIMQTYOH(45);
		model.setTESTITIMQTYAVAIL(45);
		model.setTESTITIMQTYALL(45);

		return ResponseEntity.ok(service.update(model));		
	}
	
	//jpaPaging 테스트
	@RequestMapping(value = "jpaPagingTest", method = RequestMethod.GET)
	
	 	public Map<String, Object> jpaPagingTest(String page){
		
		page="0"; // postman으로 안되서 임의로 넣어줌
		
		System.out.println(page);
		int pages = Integer.parseInt(page);
		int size = 5;
		PageRequest pageRequest = PageRequest.of(pages, size,Sort.by("TESTITIMIDX").descending());
		Map<String, Object> result = new HashMap<>();
		result.put("test", "ok");
		result.put("list", repository.findAll(pageRequest));
		result.put("wordlist", repository.findByTESTITIMIDXContains("7"));
			
		return result;
		 
	} 
	
	//pagingTest 테스트
	@RequestMapping(value = "pagingTest", method = RequestMethod.GET)
	
 	public Map<String, Object> pagingTest(@RequestBody String page){
	Criteria cri = new Criteria();
	
	int pages = Integer.parseInt(page);
	cri.setPage(pages);
	System.out.println(cri);
	Map<String, Object> result = new HashMap<>();
	result.put("test", "ok");
	result.put("list", service.pagingTest(cri));
	System.out.println("Controller.pagingTest 호출");
	
	return result;
	
} 

	
	 
	


}
