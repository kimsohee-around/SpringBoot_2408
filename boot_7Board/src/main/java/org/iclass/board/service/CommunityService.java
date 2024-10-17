package org.iclass.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.board.dao.CommunityCommentsMapper;
import org.iclass.board.dao.CommunityMapper;


import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.dto.PageRequestDTO;
import org.iclass.board.dto.PageResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

//@Component
@Service
@Slf4j
//@RequiredArgsConstructor  //final 변수 커스텀 생성자를 만듭니다.
public class CommunityService {
	//게시판 기능 서비스 - 메인글과 댓글은 하나의 기능.서비스도 1개로 했습니다.
	// 자동 주입 - final 로 하고 생성주입을 했습니다.
	private final CommunityMapper mainDao;
	private final CommunityCommentsMapper cmtDao;
	
	//생성자 주입
	public CommunityService(CommunityMapper mainDao,CommunityCommentsMapper cmtDao) {
		// TODO Auto-generated constructor stub
		this.mainDao = mainDao;
		this.cmtDao = cmtDao;
	}
	
	//페이지네이션+검색 기능으로 글목록 생성 : 검색 없는 버전
	// 지웠음. spring_4Board 프로젝트 참고

	// 검색 기능을 포함하는 글목록과 페이지 처리
	public PageResponseDTO getList(PageRequestDTO requestDTO){
		requestDTO.setTypes();    //검색 컬럼에 대한 배열 생성하기
		int totalCount = mainDao.getCount(requestDTO);
		log.info("requestDTO:{} {}",requestDTO,totalCount);
		int pageSize = requestDTO.getSize();
		double temp = (double)totalCount/pageSize;
		int totalPages = (int) Math.ceil(temp);

		int currentPage =requestDTO.getPage();
		// currentPage는 1보다 작을 수 없다.
		currentPage = Math.max(1,currentPage);

		// currentPage는 totalPages 보다 클수 없다.
		currentPage = Math.min(totalPages, currentPage);
		int startNo = (currentPage-1)*pageSize +1;
		int endNo = startNo + (pageSize-1);
		requestDTO.setStartNo(startNo);
		requestDTO.setEndNo(endNo);

		//requestDTO 에서 getList 정의된 SQL 실행에 필요한 파라미터는 모두 저장되었습니다.
		List<CommunityDTO> list = mainDao.getList(requestDTO);
		PageResponseDTO responseDTO = new PageResponseDTO();

		//현재 페이지를 기준으로 페이지리스트 시작번호
		int startPage = (currentPage-1)/pageSize*pageSize+1;
		//페이지리스트 크기가 10이로 했을때,10 대신에 pageSize
		//현재페이지-1을 10으로 나눈 몫*10 +1
		int endPage =  Math.min(totalPages, startPage+(pageSize-1));
		//9 대신에 pageSize-1 로 하세요. endPage는 totalPages를 초과할수 없음.

		// 응답으로 화면에서 필요한 값들 저장.
		responseDTO.setTotalCount(totalCount);
		responseDTO.setTotalPages(totalPages);
		responseDTO.setStartPage(startPage);
		responseDTO.setEndPage(endPage);
		responseDTO.setList(list);

		return responseDTO;
	}


	//글상세보기 : select, 조회수 증가 update
	@Transactional   //트랜잭션 단위에 해당하는 sql 특히 insert,update,delete를
	// 알아서 commit 또는 rollback 합니다.
	public CommunityDTO read(int idx) {
		mainDao.setReadCount(idx);	//테이블의 해당 행 카운트 증가
		CommunityDTO dto = mainDao.selectByIdx(idx);
		return dto;
	}
	
	// 글 수정할 때 조회수 증가 없이 select 합니다.
	public CommunityDTO selectByIdx(int idx) {
		return mainDao.selectByIdx(idx);
	}

	public void write(CommunityDTO dto) {
		mainDao.insert(dto);
	}

	public void modify(CommunityDTO dto) {
//		if(!dto.getTitle().startsWith("(수정)")){
//			dto.setTitle("(수정)"+dto.getTitle());
//		}
		mainDao.update(dto);
		
	}

	public void remove(int idx) {
		mainDao.delete(idx);
	}
	
	

}
