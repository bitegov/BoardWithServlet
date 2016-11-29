package Model.Manage;

import Model.DAO.DaoToMSSQL;
import Model.DTO.Writer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jerry on 2016-11-13.
 */
public class BoardManager {

    private static BoardManager ourInstance = new BoardManager();
    public static BoardManager getOurInstance(){return ourInstance;}

    private DaoToMSSQL daoToMSSQL = DaoToMSSQL.getInstance();
    private DTOfactory dtOfactory = DTOfactory.getInstance();

    public List<Writer> SelectAllOfWriter() throws SQLException {
        List<Writer> writers= dtOfactory.makeDTOFromStringList("Writer",daoToMSSQL.select("*", "writer"));
        return writers;
    }

    public Writer selectWriterByID(String writerId) throws SQLException {
        Writer writer = null;
        List <String> tempList = daoToMSSQL.select("*", "writer","id", writerId);
        if(!tempList.isEmpty()){ new Writer(Integer.parseInt(tempList.get(0)),tempList.get(1),tempList.get(2),tempList.get(3));}
        return writer;
    }

    public Writer selectWriterByEmail(String email) throws SQLException {
        System.out.println("selectWriterByEmail 실행 중");
        Writer writer = null;
        List <String> tempList = daoToMSSQL.select("*", "writer","email",email);
        if(!tempList.isEmpty()){writer = new Writer(Integer.parseInt(tempList.get(0)),tempList.get(1),tempList.get(2),tempList.get(3));}
        System.out.println("selectWriterByEmail 종료");
        return writer;
    }

    public boolean insertWriter(Writer writer) throws SQLException{
        return this.insertWriter(writer.getEmail(),writer.getName(),writer.getPassword());
    }

    public boolean insertWriter(String emailAddress, String name, String password) throws SQLException {
        System.out.println("insertWriter 실행");
        Map<String,String>map = new HashMap<String, String>();
        map.put("EMAIL",emailAddress);
        map.put("NAME",name);
        map.put("PASSWORD",password);
        return daoToMSSQL.insert("writer",map);
    }

    public boolean insertArticle(String title, String body, String email, String password)  {
        Boolean returnValue = false;
        System.out.println("insertArticle 실행 중");
        Writer writer = null;
        try {
            writer = selectWriterByEmail(email);
            if(writer==null){
                insertWriter(email,email,password);
                writer = selectWriterByEmail(email);
            }
            Map<String,String>map = new HashMap<String, String>();
            map.put("writerID",Integer.toString(writer.getId()));
            map.put("title",title);
            map.put("body",body);
            map.put("writingdate","20161129");
            map.put("Article_NO","0");
            map.put("Article_version","0");
            System.out.println(title+body+email+password);
            returnValue = daoToMSSQL.insert("article",map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
