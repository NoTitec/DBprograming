package persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import persistence.dto.BoardDTO;

public class BoardSql {
    public String selectRecent(int day){
        SQL sql= new SQL(){{
            SELECT("*");
            FROM("BOARD");
            WHERE("datediff(NOW(),regdate)<#{day}");
        }};
        return sql.toString();
    }
    public String selectLike(BoardDTO boardDTO){
        SQL sql= new SQL(){{
            SELECT("*");
            FROM("BOARD");
            if(boardDTO.getTitle()!=null)
            WHERE("writer like concat('%',#{writer},'%')");//파라미터 dto의 writer properties
            OR();
            if(boardDTO.getWriter()!=null)
            WHERE("title like concat('%',#{title},'%')");//파라미터 dto의 title properties
        }};
        return sql.toString();
    }
}
