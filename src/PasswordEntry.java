import java.util.Arrays;
import java.util.Objects;

public class PasswordEntry {
    private String service;
    private String login;
    private char[] password;

    public static int countPasswords = 0;

    public boolean isValid (String entry) {

        if (entry == null || entry.isBlank()){
            return false;
        } else {
            return true;
        }

    }


    public PasswordEntry (String service, String login, char[] password) {

        if (service == null) {
            throw new NullPointerException("Service == null");
        }
        if (login == null) {
            throw new NullPointerException("Login == null");
        }


        if (isValid(service)) {
            this.service = service;
        } else{
            throw new IllegalArgumentException("Поле сервис не может быть пустое");
        }

        if (isValid(login)) {
            this.login = login;
        }else {
            throw new IllegalArgumentException("Поле логин не может быть пустое");
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
        return Arrays.copyOf(password, password.length);
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

