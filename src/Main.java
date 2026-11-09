import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    private static int getValidPassword(){
        return 12345;
    }

    private static String getValidUsername(){
        return "Egor";
    }

    private static boolean authorizeUser(String name, int password){
        return (password == getValidPassword() && name.equals(getValidUsername()));
    }

    private static String outputMenu() {
        return "Добавить новую запись -  1, Посмотреть записи - 2, Изменить записи - 3, Удалить запись - 4, Завершить работу программы - e";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int password;
        String name;
        int attempts = 0;
        PasswordStore allEntries = new PasswordStore();



        do {
            if (attempts > 0){
                System.out.println("Неверный Логин или пароль");
            }

            System.out.println("Введите Логин");
            name = sc.nextLine();

            while (true) {
                try {
                    System.out.println("Введите пароль (только цифры)");
                    password = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Пароль должен состоять только из цифр");
                    sc.next();
                }
            }
            sc.nextLine();
            attempts++;
        } while ((!authorizeUser(name, password)) && (attempts < 3));

        if (authorizeUser(name, password)){
            System.out.println("Вы вошли в аккаунт");
            System.out.println(outputMenu());
            String answer = sc.nextLine();
            while (true){
                if (answer.equals("1")){



                    while (true){
                        char[] password1 = null;
                        try{
                            System.out.println("Введите название сервиса");
                            String service =  sc.nextLine();
                            System.out.println("Введите логин");
                            String login =  sc.nextLine();
                            System.out.println("Введите пароль");
                            password1 =  sc.nextLine().toCharArray();
                            PasswordEntry Password1 = new PasswordEntry(service, login, password1);
                            allEntries.addEntry(Password1);
                            System.out.println(Password1);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Введите заново сервис, логин и пароль");
                        } catch (DuplicateEntryException e) {
                            System.out.println("Такая запись уже существует");
                        }finally{
                            if (password1 != null){
                                Arrays.fill(password1, '\0');
                            }

                        }

                    }



                } else if (answer.equals("2")){
                    if (allEntries.size() > 0){
                        allEntries.outputEntries();
                    }else {
                        System.out.println("Записей ещё нет");
                    }


                } else if (answer.equals("3")){
                    if (allEntries.size() > 0){

                        int index = -1;

                        while (true) {
                            try{
                                allEntries.outputEntries();
                                System.out.println("Введите номер записи которую хотите изменить");
                                index = sc.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Номер должен состоять только из цифр");
                                sc.next();
                            }
                        }
                        sc.nextLine();

                        if ((index > -1) && (index < allEntries.size())) {
                            System.out.println(allEntries.get(index));
                            System.out.println("Для изменения пароля - p, для выхода из меню изменений - e");
                            String changeSelection = sc.nextLine();
                            if (changeSelection.equals("p")){
                                char[] newPassword = null;
                                int newAttempts = 0;
                                while (true) {
                                    try{
                                        System.out.println("Введите новый пароль (пароль должен содержать больше 6 цифр): ");
                                        newPassword = sc.nextLine().toCharArray();
                                        allEntries.updatePassword(newPassword, index);
                                        break;
                                    } catch(IllegalArgumentException e){
                                        System.out.println("Введите новый пароль (пароль должен содержать больше 6 цифр): ");
                                    }finally{
                                        if (newPassword != null){
                                            Arrays.fill(newPassword, '\0');
                                        }

                                    }
                                }
                                System.out.println("Пароль успешно изменен");
                            }
                        } else {
                            System.out.println("Неправильный номер записи");
                        }
                    } else {
                        System.out.println("Невозможно изменить запись. Записей нет");
                    }

                }else if (answer.equals("4")){
                    if (allEntries.size() > 0){

                        int index = -1;

                        while (true) {
                            try {
                                allEntries.outputEntries();
                                System.out.println("Введите номер записи которую хотите удалить");
                                index = sc.nextInt();
                                if (index > -1 & index < allEntries.size()){
                                    if(allEntries.removeEntry(allEntries.get(index))) {
                                        System.out.println("Удаление прошло успешно!");
                                    }else {
                                        System.out.println("Произошла ошибка");
                                    }
                                    break;
                                }else {
                                    System.out.println("Такого индекса не существует");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Номер должен состоять только из цифр");
                                sc.next();
                            }
                        }
                        sc.nextLine();
                        allEntries.outputEntries();
                    } else{
                        System.out.println("Удаление невозможно. Записей нету");
                    }

                }else if (answer.equals("e")){
                    break;
                }
                System.out.println(outputMenu());
                answer = sc.nextLine();
            }
        } else{
            System.out.println("Попробуйте ещё раз через 3 часа");
        }
    }
}
