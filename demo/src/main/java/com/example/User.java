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
    String nome = sc.nextLine();
    System.out.println("Escreva seu artigo: ");
    String texto = sc.nextLine();

    String file_name = nome.replace(" ", "_") + ".txt";
    File file = new File("artigos/" + file_name);

    try (FileWriter writer = new FileWriter(file)) {
      writer.write("Autor: " + this.username + "\n");
      writer.write(texto);
      writer.close();
      System.out.printf("Artigo salvo com sucesso: %s", file_name);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void deletar() {
    if (!this.isEditor()) {
      System.out.println("Acesso negado: Você não tem permissão para deletar artigos.");
      return;
    }
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite o nome do artigo que deseja deletar: ");
    String input_file_name = sc.nextLine();
    String file_name = input_file_name + ".txt";
    File file = new File("artigos/" + file_name);

    if (file.exists()) {
      if (file.delete()) {
        System.out.println("Artigo deletado com sucesso.");
      } else {
        System.out.println("Falha ao deletar artigo.");
      }
    } else {
      System.out.println("Artigo não encontrado.");
    }
  }

  public void ler() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite o nome do artigo que deseja ler. Digite sem a extensão de arquivo (.txt, .csv, .pdf...):");
    String file_name = sc.nextLine() + ".txt";
    File file = new File("artigos/" + file_name);
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      System.out.printf("\nNome do artigo: %s\n", file_name);
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      System.out.println("\n");
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
    if (this.permission == null) {
      this.permission = this.getPermissionFromCSVString(this.username);
      if (this.permission.equals("editor")) {
        return true;
      }
      return false;
    } else {
      if (this.permission.equals("editor")) {
        return true;
      }
      return false;
    }
  }
}
