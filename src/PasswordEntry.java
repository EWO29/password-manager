import java.util.Arrays;
import java.util.Objects;

public class PasswordEntry {
    private String service;
    private String login;
    private char[] password;

    public static int countPasswords = 0;

    public PasswordEntry (String service, String login, char[] password) {

        if (service == null || service.isBlank()) {
            throw new IllegalArgumentException("Поле сервис не может быть пустое");
        } else{
            this.service = service;
        }

        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Поле логин не может быть пустое");
        }else {
            this.login = login;
        }

        setPassword(password);

        countPasswords++;
    }

    public void setPassword (char[] password) {

        if (this.password != null) {
            Arrays.fill(this.password, '\0');

        }

        if (password.length < 6) {

            throw new IllegalArgumentException("Пароль должен содержать больше 6 символов");

        } else {

            this.password =  Arrays.copyOf(password, password.length);

        }
    }

    public String getService() {
        return service;
    }

    public String getLogin() {
        return login;
    }

    public char[] getPassword(){
        return password;
    }

    @Override
    public boolean equals(Object other){

        if (other != null && getClass() == other.getClass()) {
            PasswordEntry otherEntry = (PasswordEntry) other;
            return this.service.equals(otherEntry.service) && this.login.equals(otherEntry.login);
        } else {
            return false;
        }
    }

    @Override
    public String toString(){

        return service + " " + login;

    }

    @Override
    public int hashCode() {

        return Objects.hash(service, login);

    }


}

