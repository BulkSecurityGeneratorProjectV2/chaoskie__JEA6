package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.*;

@Entity
public class Kweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    private Date date;
    private String message;

    @OneToMany(fetch = FetchType.LAZY)
    private List<User> likes;

    public Kweet() { }

    public Kweet(User user, String message) {
        this.user = user;
        this.date = new Date();
        this.message = message;
        this.likes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public List<User> getLikes() {
        return likes;
    }

    public List<String> getHashtags() {
        List<String> hashtags = new ArrayList<>();
        String regexPattern = "(#\\w+)";

        Pattern p = Pattern.compile(regexPattern);
        Matcher m = p.matcher(this.message);
        while (m.find()) {
            String hashtag = m.group(1).replace("#", "");
            hashtags.add(hashtag);
        }

        return hashtags;
    }
}