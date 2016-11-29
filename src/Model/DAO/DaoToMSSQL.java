package Model.DAO;


import com.microsoft.sqlserver.jdbc.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    private static String connectionUrl = setURL();
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver.class";

    private static String setURL(){
        String id = "";
        String pw = "";

        try {
            URL url = new URL("file:\\C:\\Users\\jerry\\Documents\\MSTEST.xml");
            URLConnection connection = url.openConnection();
            Document document = parseXML(connection.getInputStream());
            NodeList nodeList = document.getElementsByTagName("DBMS_INFO");
            id = document.getElementsByTagName("id").item(0).getTextContent(); //id
            pw = document.getElementsByTagName("pw").item(0).getTextContent(); //pw
            System.out.println(id+"@@"+pw);
          /*//각 하위 태그별 구분시 필요
          for(int i=0;i<nodeList.getLength();i++){
                for(Node node = nodeList.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
                    if(node.getNodeName().equals("id")){
                        id = node.getTextContent();
                    }else if(node.getNodeName().equals("pw")){
                        pw = node.getTextContent();
                    }
                }
            }*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url2 = "jdbc:sqlserver://webboard.database.windows.net:1433;database=WebBoard;user="+id+"@webboard;password={"+pw+"};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;useUnicode=true;characterEncoding=UTF-8;";
        String result = url2;
        return result;
    }
    //XML 파서
    private static Document parseXML(InputStream stream) throws Exception{

        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;

        try{

            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);

        }catch(Exception ex){
            throw ex;
        }
        return doc;
    }

    public List<String> select(String select, String from) throws SQLException {
        String SQL = "SELECT " + select + "FROM " + from;
        return (List<String>) runSQL(SQL,"R");
    }

    public List<String> select(String select, String from, String whereColumn, String whereValue) throws SQLException {
        String SQL = "SELECT " + select + " FROM " + from + " Where " + whereColumn+"="+"\'"+ whereValue +"\'";
        System.out.println(SQL);
        return (List<String>) runSQL(SQL,"R");
    }

   /* tableName 는 삽입할 대상의 테이블 명,
    columnNames은 컬럼명과 각 엔티티별 컬럼의 실제 값으로 이루어진 HashMap
    컬럼명 중복이 되지 않기에, set으로 표현가능
    Map은 중복가능, Set은 중복불가
    */

    public boolean insert(String tableName, Map<String, String> columnNames) {
        String column = columnNames.keySet().toString();
        String values = columnNames.values().toString();
        column = column.replaceAll("\\[", "").replaceAll("\\]", "").trim();//대괄호삭제
        values = values.replaceAll("\\[", "\'").replaceAll("\\]", "\'").replaceAll("\\,", "\'\\,").replaceAll(" ", "\'").trim();//대괄호삭제 및 따옴표 추가
        System.out.println(column);
        System.out.println(values);
        String SQL = "insert into " + tableName + "(" + column + ")" + "values" + "(" + values + ")";
        System.out.println(SQL);

        try {
            return (boolean)runSQL(SQL,"C");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
