package edu.innotech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        UserService userService = context.getBean(UserService.class);

        System.out.println("Context создан, dataSource открыт !");

        System.out.println("Добавляем одного пользователя --------------");
        userService.createUser("Шариков Полиграф Полиграфович");
        userService.getAllUsers().stream().forEach(System.out::println);

        System.out.println("Добавляем несколько пользователей --------------");
        userService.createUsers(Arrays.asList( "Преображенский Филипп Филлипович",
                                               "Борменталь Иван Арнольдович",
                                               "Иванова Дарья Петровна",
                                               "Бунина Зинаида Прокопьевна",
                                               "Швондер - домком",
                                               "Вяземская - заведующая культотделом дома",
                                               "тов. Пеструхин",
                                               "тов. Жаровкин" ));
        userService.getAllUsers().stream().forEach(System.out::println);

        System.out.println("Извлекаем пользователя по имени -----------------");
        System.out.println(userService.getUserByName("Борменталь Иван Арнольдович"));

        System.out.println("Удаляем пользователя по имени ------------------");
        userService.deleteUserByName("Швондер - домком");
        userService.getAllUsers().stream().forEach(System.out::println);

        System.out.println("Удаляем всех пользователей --------------------");
        userService.deleteAllUsers();
        userService.getAllUsers().stream().forEach(System.out::println);
    }
}