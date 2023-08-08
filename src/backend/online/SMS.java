package backend.online;

import backend.User;

import java.util.Objects;

public class SMS {
    static final String SPACE = "!%#$";
    static final String LINE = "*%^&%*";
    public final User user;
    public final String text;

    public SMS(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public static String makeInLine(String text) {
        text = text.replace(" ", SPACE);
        text = text.replace("\n", LINE);
        return text;
    }

    public static String makeRegular(String text) {
        text = text.replace(SPACE, " ");
        text = text.replace(LINE, "\n");
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SMS sms)) return false;
        return user.equals(sms.user) && text.equals(sms.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, text);
    }
}
