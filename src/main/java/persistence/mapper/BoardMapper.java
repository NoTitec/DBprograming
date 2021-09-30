package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {
    final String getAll="select * from board";

    @Select(getAll)
    @Results(id="boardResultSet",value ={
            @Result(property = "id",column = "board_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "writer",column = "writer"),
            @Result(property = "contents",column = "contents"),
            @Result(property = "regDate",column = "regdate"),
            @Result(property = "hit",column = "hit")
    })
    List<BoardDTO> getAll();

    @Select("select * from board where board_id=#{id}")
    @ResultMap("boardResultSet")//위의 Results정의id를 이용해 가져옴
    BoardDTO selectById(@Param("id") Long id);//받아온 id파라미터는 @select문안의 #{id}에 매핑

    @SelectProvider(type=persistence.mapper.BoardSql.class,method="selectRecent")//띄어쓰기 조심
    @ResultMap("boardResultSet")
    List<BoardDTO> selectRecentpost(int day);

    @SelectProvider(type=persistence.mapper.BoardSql.class,method="selectLike")
    @ResultMap("boardResultSet")
    List<BoardDTO> selectTitleWriterLike(BoardDTO boardDTO);
}
