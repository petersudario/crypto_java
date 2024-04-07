// Desenvolvido por Pedro Henrique Sudario da Silva
// PUCPR - Curitiba - 2024

package com.example;

import java.io.*;
import org.mindrot.jbcrypt.BCrypt;

public class Crypt {
  
  public void cryptPassword(String user, String password) {
    String cryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.csv", true))) {
      writer.write(user + "," + cryptedPassword);
      writer.newLine(); 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void savePermissions(String user, String permissions) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("permissions.csv", true))) {
      writer.write(user + "," + permissions);
      writer.newLine(); 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public Boolean verifyPassword(String user, String password) {
    try (BufferedReader reader = new BufferedReader(new FileReader("dados.csv"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        if (data.length == 2 && data[0].equals(user) && BCrypt.checkpw(password, data[1])) {
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
