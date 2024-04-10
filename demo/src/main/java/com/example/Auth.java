// Desenvolvido por Pedro Henrique Sudario da Silva
// PUCPR - Curitiba - 2024

package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Auth {
  protected Crypt crypt = new Crypt();
  private String generic_permission = "funcionario";

  public void register(User user) {
    if (userExists(user.getUsername())) {
      System.out.println("Usuário já existe.\n \n");
      return;
    }
    crypt.cryptPassword(user.getUsername(), user.getPassword());
    crypt.savePermissions(user.getUsername(), generic_permission);
    System.out.println("Usuário registrado.\n \n");
  }

  public void logout(User user) {
    user.authenticated = false;
    System.out.println("Usuário deslogado.\n \n");
  }

  public void authenticate(User user) {
    if (user == null) {
      System.out.println("Usuário não encontrado.\n \n");
      return;
    }
    if (crypt.verifyPassword(user.getUsername(), user.getPassword())) {
      System.out.println("Autenticação realizada com sucesso.\n \n");
      user.authenticated = true;
    } else {
      System.out.println("Falha na autenticação.\n \n");
    }
  }

  public boolean isAuthenticated(User user) {
    if (user.isAuthenticated()) {
      System.out.println("Usuário autenticado.\n \n");
      return true;
    } else {
      System.out.println("Usuário não autenticado.\n \n");
      return false;
    }
  }

  public boolean userExists(String username) {
    try (BufferedReader reader = new BufferedReader(new FileReader("dados.csv"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        if (data.length == 2 && data[0].equals(username)) {
          return true;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

}
