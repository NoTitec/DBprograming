import persistence.MyBatisconnectionFactory;
import persistence.dao.BoardDAO;
import persistence.dao.MyBoardDAO;
import persistence.dto.BoardDTO;
import service.BoardService;
import view.BoardView;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyBoardDAO myBoardDAO=new MyBoardDAO(MyBatisconnectionFactory.getSqlSessionFactory());
        String title = "TEST2";
        String writer = "kim";
        Map params = new HashMap<String, Object>();
        params.put("title",title);
        params.put("writer",writer);
        List<BoardDTO> posts = myBoardDAO.findpostWithTitlelike(params);
        System.out.println("posts.size() = " + posts.size());
        posts.stream().forEach(p -> System.out.println(p.toString()));

        /*MyBoardDAO myBoardDAO=new MyBoardDAO(MyBatisconnectionFactory.getSqlSessionFactory());//dependancy injection
        List<BoardDTO> boardDTOS = myBoardDAO.findpostWithTitlelike("TEST3");
        System.out.println("size"+ boardDTOS.size());
        boardDTOS.stream().forEach(p-> System.out.println("p.toString="+p.toString()));*/
        /*for(BoardDTO dto:boardDTOS){
            System.out.println("dto.toString()="+dto.toString());
        }*/
        /*BoardDAO boardDAO=new BoardDAO();//이때 한번 만들고 생성 안함 유사 싱글톤방식
        BoardView boardView= new BoardView();
        BoardService boardService= new BoardService(boardDAO);

        //사용자가 controll에서 전체출력 요청한것 가정
        List<BoardDTO> all= boardService.findall();
        boardView.printAll(all);*///mybatis사용하며 주석처리


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
