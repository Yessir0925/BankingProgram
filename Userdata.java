import java.io.FileWriter;
import java.io.IOException;

public class Userdata {
    private String Username;
    private String Password;
    private int Usercode;
    private int time;
    private double balance;

    //Setters
    public void newUserData(String Username, String Password, int Usercode, int time, double balance){
        this.Username = Username;
        this.Password = Password;
        this.Usercode = Usercode;
        this.time = time;
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setUsercode(int Usercode) {
        this.Usercode = Usercode;
    }

    //Getters
    
    public String getUsername() {
        return Username;
    }

    public int getUsercode() {
        return Usercode;
    }

    public String getPassword() {
        return Password;
    }

    public int getTime() {
        return time;
    }

    public double getBalance() {
        return balance;
    }

    public void pushCSV(String Username, String Password, int Usercode, int time, double balance) {
        try (FileWriter pushUsrtoCSV = new FileWriter("Bankdata.csv")) {
            pushUsrtoCSV.write(Username + "," + Password + "," + Usercode + "," + time + "," + balance + "\n");
        } catch (IOException e) {
            System.out.println("Io Error");
        }
    }

    //Pull from csv file
}