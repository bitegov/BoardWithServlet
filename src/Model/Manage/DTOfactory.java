package Model.Manage;

import Model.DTO.Article;
import Model.DTO.Writer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 2016-11-16.
 */
public class DTOfactory {
    private static DTOfactory ourInstance = new DTOfactory();

    public static DTOfactory getInstance() {
        return ourInstance;
    }

    private static BoardManager boardManager = BoardManager.getOurInstance();

    private DTOfactory() {
    }

    public List makeDTOFromStringList(String dtoType,List<String> list) throws SQLException {
        dtoType = dtoType.toLowerCase(); //모두 소문자로 변경
        List responseList = null;
        if (dtoType.equals("article")) {
            responseList = new ArrayList<Article>();
            while (!list.isEmpty()) {
                Writer writer = boardManager.selectWriterByID(list.get(5));
                Article article = new Article(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),writer);
                responseList.add(article);
                //// TODO: 2016-11-17 DataStructure의 Queue가 필요한 상황!
                list.remove(0);
                list.remove(0);
                list.remove(0);
                list.remove(0);
                list.remove(0);
                list.remove(0);
            }
        } else if (dtoType.equals("writer")) {
            responseList = new ArrayList<Writer>();
            while (!list.isEmpty()) {
                System.out.println(list.toString());
                Writer writer = new Writer(list.get(1),list.get(2),list.get(3));
                responseList.add(writer);
                //// TODO: 2016-11-17 DataStructure의 Queue가 필요한 상황!
                list.remove(0);
                list.remove(0);
                list.remove(0);
                list.remove(0);
            }
        }
        return responseList;
    }
}
