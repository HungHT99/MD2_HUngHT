package ra.bussiness.imp;

import ra.bussiness.design.IUser;
import ra.bussiness.entity.User;

import java.util.List;
import java.util.Scanner;

public class UserImp implements IUser<User, String> {

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void displayData() {

    }

    @Override
    public User inputData(Scanner sc) {
        return null;
    }

    @Override
    public List<User> readformFile() {
        return null;
    }

    @Override
    public boolean writeToFile(List<User> list) {
        return false;
    }

    @Override
    public User checkLogin(String name, String pass) {
        return null;
    }
}
