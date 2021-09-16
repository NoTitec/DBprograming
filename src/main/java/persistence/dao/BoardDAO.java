package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.BoardDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private final DataSource ds= PooledDataSource.getDataSource();//dbcp객체가져옴 static 이라new 필요없음

    public List<BoardDTO> findAll(){
        Connection conn = null;
        Statement stmt = null;
        //PreparedStatement pstmt = null;
        ResultSet rs = null;
        String selectquery = "SELECT * FROM BOARD";
        List<BoardDTO> boardDTOS=new ArrayList<>();//dto정보저장할 리스트
        //String insertquery = "INSERT INTO board(title,writer,contents,regdate,hit) VALUES(?,?,?,?,?)";//board idsms auto increment라 자동증가
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
           // String url = "jdbc:mysql://localhost/mydb";
            //conn = DriverManager.getConnection(url, "root", "root");
            conn= ds.getConnection();
            conn.setAutoCommit(false);

           /* pstmt= conn.prepareStatement(insertquery);//prepareStatement는 pstmt문인자로 쿼리줌
            pstmt.setString(1,"preparedStatement");
            pstmt.setString(2,"kwon");
            pstmt.setString(3,"preparedStatement test2");
            pstmt.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(5,0);
            int count = pstmt.executeUpdate();//preparedstatement는 그냥 execute sql1
            System.out.println("count = " + count);*/

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectquery);//statment는 execute문에 query문 넣음 sql2
            while(rs.next()) {
                BoardDTO boardDTO= new BoardDTO();//DTO객체생성해서 가져온 자료 DTO객체에 set
                Long id = rs.getLong("board_id");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String contents = rs.getString("contents");
                LocalDateTime regDate = rs.getTimestamp("regdate").toLocalDateTime();
                int hit= rs.getInt(6);
                boardDTO.setId(id);
                boardDTO.setTitle(title);
                boardDTO.setWriter(writer);
                boardDTO.setContents(contents);
                boardDTO.setRegDate(regDate);
                boardDTO.setHit(hit);
                boardDTOS.add(boardDTO);//set된 boarddto 리스트저장
               /* System.out.printf("%s | %s | %s | %s | %s | %s\n", id,title,writer,contents,regdate.toString(),hit);
                System.out.println("-------------------------------------");*/

                conn.commit();
            }
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        } */catch(SQLException e){
            System.out.println("error : " + e);
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }  finally{
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return boardDTOS;//set 완료된 boardDTO 리스트 리턴
    }
    //파라미터만 다름 오버로딩
    /*public select();
    public select(String keyword);
    public delete();
    public insert();
    */
    //싱글톤 패턴 적용시
   /* static BoardDAO boardDAO;//인스턴스 안만들어도 static 영역올라가 클래스 사용가능
    private BoardDAO(){//싱글톤
    BoardDAO getInstance(){
        return boardDAO;
    }*/

    //4개모두 connection,close 필요하므로 따로 함수 작성 혹은 객체를 공유
}
