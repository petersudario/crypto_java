// Desenvolvido por Pedro Henrique Sudario da Silva
// PUCPR - Curitiba - 2024

package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Auth auth = new Auth();
        System.out.println("Bem-vindo ao nosso sistema");

        User global_user = null;

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        boolean authenticated = false;

        while (!exit) {            
            System.out.println("---- MENU ----");

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Registrar");
            System.out.println("2 - Autenticar");
            System.out.println("0 - Sair");

            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Digite o nome de usuário:");
                    String username = sc.next();
                    System.out.println("Digite a senha:");
                    String password = sc.next();
                    User user = new User(username, password);
                    global_user = user;
                    auth.register(user);
                    auth.authenticate(user);
                    if (auth.isAuthenticated(user)) {
                        authenticated = true;
                    }
                    break;
                case 2:
                    System.out.println("Digite o nome de usuário:");
                    username = sc.next();
                    System.out.println("Digite a senha:");
                    password = sc.next();
                    user = new User(username, password);
                    global_user = user;
                    auth.authenticate(user);
                    if (auth.isAuthenticated(user)) {
                        authenticated = true;
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            while (authenticated) {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Escrever artigo");
                System.out.println("2 - Deletar artigo");
                System.out.println("3 - Ler artigo");

                System.out.println("0 - Sair");
                Integer option = sc.nextInt();
                switch (option) {
                    case 1:
                        global_user.escrever();
                        break;
                    case 2:
                        global_user.deletar();
                        break;
                    case 3:
                        global_user.ler();
                        break;
                    case 0:
                        auth.logout(global_user);
                        authenticated = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }

        sc.close();
    }
}
