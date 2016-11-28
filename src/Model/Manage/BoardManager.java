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
        List <String> tempList = daoToMSSQL.select("*", "writer","id="+writerId);
        return new Writer(tempList.get(0),tempList.get(1),tempList.get(2));
    }

    public boolean insertWriter(Writer writer) throws SQLException{
        return this.insertWriter(writer.getEmail(),writer.getName(),writer.getPassword());
    }

    public boolean insertWriter(String emailAddress, String name, String password) throws SQLException {
        Map<String,String>map = new HashMap<String, String>();
        map.put("EMAIL","ttteeess@naver.com");
        map.put("NAME","test1");
        map.put("PASSWORD","1234");
        return daoToMSSQL.insert("writer",map);
    }

    public boolean insertArticle(String title, String body, String email, String password){
        System.out.println(title+body+email+password);
        return true;
    }
}
