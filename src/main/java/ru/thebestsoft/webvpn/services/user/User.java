package ru.thebestsoft.webvpn.services.user;

public class User {

    String login;
    String typeVPN = "pptpd";
    String Password;
    String host = "*";
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTypeVPN() {
        return typeVPN;
    }

    public void setTypeVPN(String typeVPN) {
        this.typeVPN = typeVPN;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }




}
