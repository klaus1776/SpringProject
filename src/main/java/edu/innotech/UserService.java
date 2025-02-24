package edu.innotech;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(String username) {
        userDao.createUser(new User(null, username));
    }

    public void createUsers(List<String> usernames) {
        userDao.createUsers(usernames);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public void deleteUserByName(String username) {
        userDao.deleteUserByName(username);
    }

    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
