package com.simple.basic.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.basic.command.BoardVO;
import com.simple.basic.service.board.BoardServiceImpl;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	@Qualifier("z")
	BoardServiceImpl boardService;
	
	// 등록
	@RequestMapping("/boardRegister")
	public String boardRegist() {
		return "board/boardRegister";
	}
	
	// 폼 요청
	@RequestMapping(value="/boardForm", method=RequestMethod.POST)
	public String boardForm(BoardVO vo) {
		boardService.regist(vo);
		return "board/boardResult";
	}
	
	// 목록
	@RequestMapping("/boardList")
	public String boardList(Model model) {
		
		ArrayList<BoardVO> list = boardService.getList();
		model.addAttribute("list", list);
		return "board/boardList";
	}
	
	// 삭제
	@RequestMapping("/boardDelete")
	public String boardDelete(int num) {
		boardService.delete(num);
		return "redirect:/board/boardList";
	}

}
