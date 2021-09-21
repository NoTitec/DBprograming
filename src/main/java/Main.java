import persistence.dao.BoardDAO;
import persistence.dto.BoardDTO;
import service.BoardService;
import view.BoardView;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BoardDAO boardDAO=new BoardDAO();//이때 한번 만들고 생성 안함 유사 싱글톤방식
        BoardView boardView= new BoardView();
        BoardService boardService= new BoardService(boardDAO);

        //사용자가 controll에서 전체출력 요청한것 가정
        List<BoardDTO> all= boardService.findall();
        boardView.printAll(all);

        /*Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String selectquery = "SELECT * FROM BOARD";
        String insertquery = "INSERT INTO board(title,writer,contents,regdate,hit) VALUES(?,?,?,?,?)";//board idsms auto increment라 자동증가
        try{
            //Class.forName("com.mysql.jdbc.Driver"); 구버전
            //Class.forName("com.mysql.cj.jdbc.Driver"); 신버전 추가안해도 자동으로 추가함
            //String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false"; ?뒤 옵션 &옵션추가
            String url = "jdbc:mysql://localhost/mydb";
            conn = DriverManager.getConnection(url, "root", "root");
            conn.setAutoCommit(false);//autocommit해제

            pstmt= conn.prepareStatement(insertquery);//insert,update,delete문
            pstmt.setString(1,"preparedStatement");
            pstmt.setString(2,"kwon");
            pstmt.setString(3,"preparedStatement test");
            pstmt.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));//db datetime 변수넣기
            pstmt.setInt(5,0);
            int count = pstmt.executeUpdate();//preparedstatement는 그냥 execute sql1
            System.out.println("count = " + count);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectquery);//statment는 execute문에 query문 넣음 sql2 select문
            while(rs.next()) {
                String id = rs.getString("board_id");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String contents = rs.getString("contents");
                LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();
                int hit= rs.getInt(6);
                System.out.printf("%s | %s | %s | %s | %s | %s\n", id,title,writer,contents,regdate.toString(),hit);
                System.out.println("-------------------------------------");

                conn.commit();//여기까지 문제없으면 commit
            }
        //} / (ClassNotFoundException e) {
        //    e.printStackTrace();
        //}
        catch(SQLException e){
            System.out.println("error : " + e);
            try {
                conn.rollback();//err발생시 rollback
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
        }*/
    }
}
