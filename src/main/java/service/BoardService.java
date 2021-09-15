package service;

import persistence.dao.BoardDAO;
import persistence.dto.BoardDTO;

import java.util.List;

public class BoardService {//boarddao쓰는데 인스턴스만들때 외부에서 boarddao가져와서 채워줌
    private final BoardDAO boardDAO ;

    public BoardService(BoardDAO boardDAO){
        this.boardDAO=boardDAO;
    }

    public List<BoardDTO> findall(){
        List<BoardDTO> all = boardDAO.findAll();
        return all;
    }
    //새로운 생성-> insert//중복체크
    //select-> 중복확인후 없으면 -> insert
}
