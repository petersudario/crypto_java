// Desenvolvido por Pedro Henrique Sudario da Silva
// PUCPR - Curitiba - 2024

package com.example;

import java.util.*;
import java.io.*;

public class User {
  protected String username;
  protected String password;
  protected boolean authenticated = false;
  protected String permission;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.permission = this.getPermissionFromCSVString(this.username);
  }

  public void escrever() {
    if (!this.isEditor()) {
      System.out.println("Acesso negado: Você não tem permissão para escrever artigos.");
      return;
    }
    System.out.println("Acesso concedido: Você pode escrever artigos.");

    Scanner sc = new Scanner(System.in);
    sc.useDelimiter("\n");
    System.out.println("Dê um nome para seu artigo: ");
    String nome = sc.next();
    System.out.println("Escreva seu artigo: ");
    String texto = sc.next();
    sc.close();

    String file_name = nome + ".txt";
    File file = new File(file_name);

    try (FileWriter writer = new FileWriter(file)) {
      writer.write(texto);
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public String getPermission() {
    return this.permission;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPermissionFromCSVString(String csvString) {
    try (BufferedReader br = new BufferedReader(new FileReader("permissions.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] data = line.split(",");
        if (data.length == 2 && data[0].equals(csvString)) {
          return data[1];
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading permissions.csv file.");
      e.printStackTrace();
    }
    return null;
  }

  public boolean isAuthenticated() {
    return this.authenticated;
  }

  public boolean isEditor() {
    if (permission.equals("editor")) {
      return true;
    }
    return false;
  }

}
