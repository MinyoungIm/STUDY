package com.spring.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.board.model.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Inject
	private SqlSession sqlSesstion;
//	SqlSession 객체를 통해 boardMapper 에 작성해 놓은 SQL문을 실행
	

	@Override
	public List<BoardVO> getBoardList() throws Exception {
		return sqlSesstion.selectList("com.spring.board.boardMapper.getBoardList");
	}

	@Override
	public BoardVO getBoardContent(int bid) throws Exception {
		return sqlSesstion.selectOne("com.spring.board.boardMapper.getBoardContent", bid);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		return sqlSesstion.insert("com.spring.board.boardMapper.insertBoard", boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {
		return sqlSesstion.update("com.spring.board.boardMapper.updateBoard", boardVO);
	}

	@Override
	public int deleteBoard(int bid) throws Exception {
		return sqlSesstion.insert("com.spring.board.boardMapper.deleteBoard", bid);
	}

	@Override
	public int updateViewCnt(int bid) throws Exception {
		return sqlSesstion.update("com.spring.board.boardMapper.updateViewCnt", bid);
	}
	
	
	
}
