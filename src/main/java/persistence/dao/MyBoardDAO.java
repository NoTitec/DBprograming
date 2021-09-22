package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.BoardDTO;

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
}
