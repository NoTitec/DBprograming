import java.sql.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String selectquery = "SELECT * FROM BOARD";
        String insertquery = "INSERT INTO board(title,writer,contents,regdate,hit) VALUES(?,?,?,?,?)";//board idsms auto increment라 자동증가
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            String url = "jdbc:mysql://localhost/mydb";
            conn = DriverManager.getConnection(url, "root", "root");
            conn.setAutoCommit(false);

            pstmt= conn.prepareStatement(insertquery);
            pstmt.setString(1,"preparedStatement");
            pstmt.setString(2,"kwon");
            pstmt.setString(3,"preparedStatement test2");
            pstmt.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(5,0);
            int count = pstmt.executeUpdate();//preparedstatement는 그냥 execute sql1
            System.out.println("count = " + count);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectquery);//statment는 execute문에 query문 넣음 sql2
            while(rs.next()) {
                String id = rs.getString("board_id");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String contents = rs.getString("contents");
                LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();
                int hit= rs.getInt(6);
                System.out.printf("%s | %s | %s | %s | %s | %s\n", id,title,writer,contents,regdate.toString(),hit);
                System.out.println("-------------------------------------");

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
    }
}
