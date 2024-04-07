// Desenvolvido por Pedro Henrique Sudario da Silva
// PUCPR - Curitiba - 2024

package com.example;

public class Main {
    public static void main(String[] args) {
        Auth auth = new Auth();
        User user = new User("Akemi", "123456");
        User user2 = new User("Pepo", "123456");
        User user3 = new User("André", "123456");

        auth.isAuthenticated(user);

        auth.register(user);
        auth.authenticate(user);
        auth.isAuthenticated(user);

        auth.register(user2);
        auth.authenticate(user2);
        auth.isAuthenticated(user2);

        auth.register(user3);
        user3.setPassword("654321");
        auth.authenticate(user3);

        user.escrever();
        user2.escrever();

    }
}
