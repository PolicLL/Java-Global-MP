package task5.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import task5.model.Account;

public class AccountDAO {
  private static final String FILE_PATH = "./accounts/";

  public static void saveAccount(Account account) throws IOException {
    File file = new File(FILE_PATH + account.username() + ".dat");
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(account);
    }
  }

  public static Account loadAccount(String username) throws IOException, ClassNotFoundException {
    File file = new File(FILE_PATH + username + ".dat");
    if (file.exists()) {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        return (Account) ois.readObject();
      }
    }
    return null;
  }
}
