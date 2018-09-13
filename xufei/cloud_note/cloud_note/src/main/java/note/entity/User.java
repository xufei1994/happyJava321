package note.entity;



import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class User implements Serializable {
    private String id;
    private String name;
    private String password;
    private String token;
    private String nick;
    private static final long serialVersionUID = -7888228721482579736L;
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getNick() {
        return nick;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public User(String id, String name, String password, String token, String nick) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.token = token;
        this.nick = nick;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }


}
