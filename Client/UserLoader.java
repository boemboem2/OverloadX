import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import sign.signlink;

public class UserLoader extends client
{
  public static String usernameh = "";
  public static String password = "";

  public static boolean charFileFound = false;

  public static void saveuser()
  {
    BufferedWriter localBufferedWriter = null;
    try {
      localBufferedWriter = new BufferedWriter(new FileWriter(signlink.findcachedir()+ "Data/login.ini"));
      localBufferedWriter.write("username = ", 0, 11);
      localBufferedWriter.write(myUsername, 0, myUsername.length());
      localBufferedWriter.newLine();
      localBufferedWriter.write("password = ", 0, 11);
      localBufferedWriter.write(myPassword, 0, myPassword.length());
      localBufferedWriter.newLine();
      localBufferedWriter.write("hitpointbar = ", 0, 14);
      localBufferedWriter.write(Integer.toString(HpBar), 0, Integer.toString(HpBar).length());
      localBufferedWriter.newLine();
      localBufferedWriter.write("hits = ", 0, 7);
      localBufferedWriter.write(Integer.toString(HitDamage), 0, Integer.toString(HitDamage).length());
      localBufferedWriter.newLine();
      localBufferedWriter.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void loaduser()
  {
    long l = System.currentTimeMillis();
    String str1 = "";
    String str2 = "";
    String str3 = "";
    String[] arrayOfString = new String[3];
    int i = 0;
    int j = 1;
    BufferedReader localBufferedReader = null;
    try
    {
      localBufferedReader = new BufferedReader(new FileReader(signlink.findcachedir()+ "Data/login.ini"));
      charFileFound = true;
    }
    catch (FileNotFoundException localFileNotFoundException) {
    }
    if (!charFileFound) {
      System.out.println("File could not be found.");
      saveuser();
      return;
    }

    while ((i == 0) && (str1 != null)) {
      str1 = str1.trim();
      int k = str1.indexOf("=");
      if (k > -1) {
        str2 = str1.substring(0, k);
        str2 = str2.trim();
        str3 = str1.substring(k + 1);
        str3 = str3.trim();
        arrayOfString = str3.split("\t");
        switch (j)
        {
        case 1:
          if (str2.equals("username"))
          {
            usernameh = str3;
          }

          if (str2.equals("password"))
          {
            password = str3;
          }
          if (str2.equals("hitpointbar"))
          {
            HpBar = Integer.parseInt(str3);
          }

          if (str2.equals("hits"))
          {
            HitDamage = Integer.parseInt(str3);
          }
        }

      }

      try
      {
        str1 = localBufferedReader.readLine();
      } catch (IOException localIOException2) {
        i = 1;
      }
    }
    try
    {
      localBufferedReader.close();
    } catch (IOException localIOException1) {
      System.out.println("");
    }
  }

  public static void finalload()
  {
toggles1();
toggles2();
    myUsername = usernameh;
    myPassword = password;
  }
}