package Model.DTO;

import java.util.List;

/**
 * Created by jerry on 2016-11-13.
 */
public class Writer {
    private String name;
    private String email;
    private String password;


    public Writer( String email, String name, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
