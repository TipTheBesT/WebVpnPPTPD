package ru.thebestsoft.webvpn.services;

import ru.thebestsoft.webvpn.services.user.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    final String path = "/etc/ppp/chap-secrets";
    private ArrayList<User> userList = new ArrayList<>();

    public UserService() {
        refresh();
        System.out.println("Файл успешно прочитан.");
    }

    private void refresh() {
        try {
            userList = new ArrayList<>();
            FileReader fr= new FileReader(path);
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                String str = scan.nextLine();
                if (!str.startsWith("#")) {
                    String[] values = str.split(" ");
                    if (values.length>=2) {
                        User user = new User();
                        user.setLogin(values[0]);
                        user.setTypeVPN(values[1]);
                        user.setPassword(values[2]);
                        user.setHost(values[3]);
                        userList.add(user);
                    }
                }
            }
            scan.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void save() {
        try {
            FileWriter writer = new FileWriter(path, false);
            String text = "";
            for (User user : userList) {
                text += user.getLogin() + " " + user.getTypeVPN() + " " + user.getPassword() + " " + user.getHost() + "\n";
            }
            writer.write(text);
            writer.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addUser(User user) throws IOException {
        userList.add(user);
        save();
    }

    public void delUser(String login) {
        for (int i = 0; i<userList.size(); i++) {
            if (userList.get(i).getLogin().equals(login)) {
                userList.remove(i);
                try {
                    save();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        userList = userList;
    }
}
