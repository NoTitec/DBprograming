package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.BoardDTO;
import persistence.mapper.BoardMapper;

import java.util.List;
import java.util.Map;

public class MyBoardDAO {
    private SqlSessionFactory sqlSessionFactory=null;

    public MyBoardDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    public List<BoardDTO> selectall(){
        List<BoardDTO> list=null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        try{
           list = session.selectList("mapper.BoardMapper.selectAll");//mapper.BoardMapper의 selectall sql가져옴
        }finally {
            session.close();
        }
        return list;
    }
    public List<BoardDTO> findpostWithTitlelike(Map<String,Object>params){//이름이 params인 map 파라미터
        List<BoardDTO> list=null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        try{
            list = session.selectList("mapper.BoardMapper.findPostWithTitleLike",params);
        }finally {
            session.close();
        }
        return list;
    }
    public void insertBoard(BoardDTO b){//이름이 b인 boarddto파라미터
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("mapper.BoardMapper.insertBoard",b);
            session.commit();
        }finally{
            session.close();
        }
    }
    public List<BoardDTO> selectallWithAnnotation(){
        List<BoardDTO> list=null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        BoardMapper mapper=session.getMapper(BoardMapper.class);
        try {
            list = mapper.getAll();
            session.commit();
        }   catch(Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return list;
    }
    public BoardDTO selectoneWithAnnotation(long num){

        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        BoardMapper mapper=session.getMapper(BoardMapper.class);
        BoardDTO boardDTO=new BoardDTO();
        try {
            boardDTO = mapper.selectById(num);
            session.commit();
        }   catch(Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return boardDTO;
    }

    public List<BoardDTO> selectresentWithAnnotation(int num){
        List<BoardDTO> list=null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        BoardMapper mapper=session.getMapper(BoardMapper.class);
        try {
            list = mapper.selectRecentpost(num);
            session.commit();
        }   catch(Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return list;
    }

    public List<BoardDTO> selectTitleWriterLikeAnnotation(BoardDTO boardDTO){
        List<BoardDTO> list=null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        BoardMapper mapper=session.getMapper(BoardMapper.class);
        try {
            list = mapper.selectTitleWriterLike(boardDTO);
            session.commit();
        }   catch(Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return list;
    }
}
