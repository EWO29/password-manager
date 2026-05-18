public class PasswordEntry {
    private String service;
    private String login;
    private String password;

    public static int countPasswords = 0;

    public PasswordEntry (String service, String login, String password) {

        if (service.isBlank()) {
            throw new IllegalArgumentException("Поле сервис не может быть пустое");
        } else{
            this.service = service;
        }

        if (login.isBlank()) {
            throw new IllegalArgumentException("Поле логин не может быть пустое");
        }else {
            this.login = login;
        }

        setPassword(password);

        countPasswords++;
    }

    public void setPassword (String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Пароль должен содержать больше 6 символов");
        } else {
            this.password = password;
        }
    }

    public String getService() {
        return service;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public boolean equals(Object other){
        if (getClass() != other.getClass()){
            return false;
        }
        PasswordEntry otherEntry = (PasswordEntry) other;
        return this.service.equals(otherEntry.service);
    }

    @Override
    public String toString(){

        return service + " " + login;

    }


}

