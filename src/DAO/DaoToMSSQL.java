package DAO;


import com.microsoft.sqlserver.jdbc.*;

import java.sql.*;

/**
 * Created by jerry on 2016-11-13.
 * 이 클래스는 MicroSoft 의 MSSQL 서버와 연동하는 DAO
 * https://msdn.microsoft.com/ko-kr/library/ms378526(v=sql.110).aspx
 * https://azure.microsoft.com/ko-kr/documentation/articles/sql-database-develop-java-simple/
 * https://msdn.microsoft.com/library/mt720656.aspx
 * http://www.codejava.net/java-se/jdbc/connect-to-microsoft-sql-server-via-jdbc
 *
 */
public class DaoToMSSQL {
    String connectionUrl = "jdbc:sqlserver://webboard.database.windows.net:1433;database=WebBoard;user=jb7959@webboard;password={*****};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;;";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver.class";
    Connection connection = null;


    public void connecting() {
        try {
            //드라이버 등록
            //Class.for(driver) 이것과 아래 동일.
            DriverManager.registerDriver(new SQLServerDriver());
            //연결
            connection = DriverManager.getConnection(connectionUrl);
            //Statement 객체 생성
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from writer");
            while (rs.next()) {
                  System.out.println(rs.getString(1));
                  System.out.println(rs.getString(2));
                  System.out.println(rs.getString(3));
                  System.out.println(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch(Exception e) {}
        }
    }
}
