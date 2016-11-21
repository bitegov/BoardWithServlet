package Model.DTO;

/**
 * Created by jerry on 2016-11-13.
 */
public class Article {
    private String title;
    private String body;
    private String date;
    private String ArticleNo;
    private String ArticleVersion;
    private Writer writer;

    public Article(String title, String body, String date, String articleNo, String articleVersion, Writer writer) {
        this.title = title;
        this.body = body;
        this.date = date;
        ArticleNo = articleNo;
        ArticleVersion = articleVersion;
        this.writer = writer;
    }

    public String getArticleNo() {
        return ArticleNo;
    }

    public void setArticleNo(String articleNo) {
        ArticleNo = articleNo;
    }

    public String getArticleVersion() {
        return ArticleVersion;
    }

    public void setArticleVersion(String articleVersion) {
        ArticleVersion = articleVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
