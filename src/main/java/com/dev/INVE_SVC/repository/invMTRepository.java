package com.dev.INVE_SVC.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.INVE_SVC.model.invMTModel;

public interface invMTRepository extends JpaRepository<invMTModel, Long>{

	
	public List<invMTModel> findAll();
	
	public Page<invMTModel> findAll(Pageable pageable);
	
	public List<invMTModel> findByTESTITIMIDXContains(String word);
	 
}
