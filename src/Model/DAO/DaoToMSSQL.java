package Model.DAO;


import com.microsoft.sqlserver.jdbc.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by jerry on 2016-11-13.
 * 이 클래스는 MicroSoft 의 MSSQL 서버와 연동하는 Model.DAO
 * https://msdn.microsoft.com/ko-kr/library/ms378526(v=sql.110).aspx
 * https://azure.microsoft.com/ko-kr/documentation/articles/sql-database-develop-java-simple/
 * https://msdn.microsoft.com/library/mt720656.aspx
 * http://www.codejava.net/java-se/jdbc/connect-to-microsoft-sql-server-via-jdbc
 */
public class DaoToMSSQL {

    //싱글톤으로 만들기.
    private static DaoToMSSQL ourInstance = new DaoToMSSQL();

    public static DaoToMSSQL getInstance() {
        return ourInstance;
    }

    //Todo 아래 DB 비밀번호 암호화 및 처리방법 추가 하기 2016-11-13
    private static String connectionUrl = "jdbc:sqlserver://webboard.database.windows.net:1433;database=WebBoard;user=jb7959@webboard;password={};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;useUnicode=true;characterEncoding=UTF-8;";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver.class";

    public List<String> select(String select, String from) throws SQLException {
        String SQL = "SELECT " + select + "FROM " + from;
        return (List<String>) runSQL(SQL,"R");
    }

    public List<String> select(String select, String from, String where) throws SQLException {
        String SQL = "SELECT " + select + "FROM " + from + "Where " + where;
        return (List<String>) runSQL(SQL,"R");
    }

   /* tableName 는 삽입할 대상의 테이블 명,
    columnNames은 컬럼명과 각 엔티티별 컬럼의 실제 값으로 이루어진 HashMap
    컬럼명 중복이 되지 않기에, set으로 표현가능
    Map은 중복가능, Set은 중복불가
    */

    public boolean insert(String tableName, Map<String, String> columnNames) throws SQLException {
        String column = columnNames.keySet().toString();
        String values = columnNames.values().toString();
        column = column.replaceAll("\\[", "").replaceAll("\\]", "").trim();//대괄호삭제
        values = values.replaceAll("\\[", "\'").replaceAll("\\]", "\'").replaceAll("\\,", "\'\\,").replaceAll(" ", "\'").trim();//대괄호삭제 및 따옴표 추가
        System.out.println(column);
        System.out.println(values);
        String SQL = "insert into " + tableName + "(" + column + ")" + "values" + "(" + values + ")";
        System.out.println(SQL);

        return (boolean)runSQL(SQL,"C");
    }

    private Object runSQL(String SQL, String typeOfCRUD) throws SQLException {
        Object returnObject = null;
        Connection connection = this.connecting();
        Statement statement = connection.createStatement();
        if (typeOfCRUD.equals("R")) {
            List list = new ArrayList<String>();
            ResultSet resultSet = statement.executeQuery(SQL);
            //DB 레코드 전체 조회
            while (resultSet.next()) {
                int i = 1;
                //DB 컬럼갯수까지
                while (i <= resultSet.getMetaData().getColumnCount()) {
                    list.add(resultSet.getString(i));
                    i++;
                }
            }
            clossingContection(resultSet, statement, connection);
            returnObject = list;
        } else if (typeOfCRUD.equals("C")) {
            statement.execute(SQL);
            clossingContection(statement);
            clossingContection(connection);
            boolean r = true;
            returnObject = r;
        }
        return returnObject;
    }

    private Connection connecting() {
        Connection connection = null;
        try {
            //드라이버 등록
            //Class.for(driver) 이것과 아래 동일.
            DriverManager.registerDriver(new SQLServerDriver());
            //연결
            connection = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }


    private boolean clossingContection(ResultSet rs, Statement stmt, Connection conn) {
        boolean result = true;
        this.clossingContection(rs);
        this.clossingContection(stmt);
        this.clossingContection(conn);
        return result;
    }


    private boolean clossingContection(ResultSet rs) {
        boolean result = true;
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                result = false;
            }
        }
        return result;
    }

    private boolean clossingContection(Connection conn) {
        boolean result = true;

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                result = false;
            }
        }
        return result;
    }

    private boolean clossingContection(Statement stmt) {
        boolean result = true;
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                result = false;
            }
        }
        return result;
    }


}
