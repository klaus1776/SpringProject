package edu.innotech.service;

import edu.innotech.entity.User;
import edu.innotech.repository.UserJDBCTemplateRepository;
import edu.innotech.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements CommandLineRunner {

    private final UserJDBCTemplateRepository userJDBCTemplateRepository;
    private final UserRepository userRepository;

    public List<User> findUsersByUsername(List<String> usernames) {
        List<User> users = userRepository.findUsersByUsername(usernames).
                orElseThrow(EntityNotFoundException::new);
        return users;
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).
                orElseThrow(EntityNotFoundException::new);
        return user;
    }

    @Override
    public void run(String... args) throws Exception {
        // Добавляем одного пользователя
        userRepository.createUser("Шариков Полиграф Полиграфович");

        // Добавляем нескольких пользователей
        // Добавление нескольких пользователей одним запросом
        userJDBCTemplateRepository.createUsers(Arrays.asList( "Преображенский Филипп Филлипович",
                                                              "Борменталь Иван Арнольдович",
                                                              "Иванова Дарья Петровна",
                                                              "Бунина Зинаида Прокопьевна",
                                                              "Швондер - домком",
                                                              "Вяземская - заведующая культотделом дома",
                                                              "тов. Пеструхин",
                                                              "тов. Жаровкин" ));

        // Добавление каждого пользователя одним запросом
        userRepository.createUsers(Arrays.asList( "тов. Ленин",
                                                  "тов. Сталин",
                                                  "тов. Зиновьев",
                                                  "тов. Каменев",
                                                  "тов. Бухарин - буржуйский прихвостень и перерожденец",
                                                  "тамбовский Волк тебе товарищ",
                                                  "тов. Троцкий - нам не товарищ",
                                                  "тов. Крупская" ));

        // Удаляем лодного пользователя по имени из таблицы
        userRepository.deleteUserByUsername("Шариков Полиграф Полиграфович");

        // Удаление нескольких пользователей из таблицы
        userRepository.deleteUsersByName(Arrays.asList( "Швондер - домком",
                                                        "Вяземская - заведующая культотделом дома",
                                                        "тов. Пеструхин",
                                                        "тов. Жаровкин" ));

        // Выбрать одну записи из таблицы пользователей
        User user = findUserByUsername("тов. Ленин");
        log.info("Founded item with id: {} and username: {}", user.getId(), user.getUsername());

        // Выбрать несколько записей одним запросом
        List<User> users = findUsersByUsername(Arrays.asList( "Преображенский Филипп Филлипович",
                                                              "Борменталь Иван Арнольдович",
                                                              "Иванова Дарья Петровна",
                                                              "Бунина Зинаида Прокопьевна" ));
        users.stream().forEach(record -> log.info("Founded item with id: {} and username: {}", record.getId(), record.getUsername()));

        // Выбрать все записи из таблицы пользователей
        userRepository.findAll().stream().forEach(record -> log.info("Founded item with id: {} and username: {}", record.getId(), record.getUsername()));

        // Удаление всех пользователей из таблицы
        userRepository.deleteAllUsers();

    }
}
