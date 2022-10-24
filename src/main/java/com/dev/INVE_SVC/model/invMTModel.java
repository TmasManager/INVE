package com.dev.INVE_SVC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TEST_ITEM_INV_MT")
public class invMTModel {

	@Id
	@Column (name="TEST_ITIM_IDX")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String TESTITIMIDX; // 재고마스터
	
	@Column (name="TEST_ITIM_PART_NO")
	private String TESTITIMPARTNO; // 품목번호
	
	@Column (name="TEST_ITIM_CORP_CD")
	private String TESTITIMCORPCD; // 법인(회사)구분코드

	@Column (name="TEST_ITIM_BSNS_CD")
	private String TESTITIMBSNSCD; // 사업장구분코드
	
	@Column (name="TEST_ITIM_QTY_OH")
	private int TESTITIMQTYOH; // 현재고량
	
	@Column (name="TEST_ITIM_QTY_AVAIL")
	private int TESTITIMQTYAVAIL; // 가용수량
	
	@Column (name="TEST_ITIM_QTY_ALL")
	private int TESTITIMQTYALL; // 예약수량
	
	
	
	
	
	
	
	
	
}
