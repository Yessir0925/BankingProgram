import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Userdata {
    private String Username;
    private String Password;
    private int Usercode;
    private LocalDateTime time;
    private double balance;

    //Setters
    public void newUserData(String Username, String Password, int Usercode, LocalDateTime time, double balance){
        this.Username = Username;
        this.Password = Password;
        this.Usercode = Usercode;
        this.time = time;
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTime(LocalDateTime time) {
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

    public String getNameandPassword() {
        return "Username: " + Username + ", Password: " + Password ;
    }

    public String getallUserData() {
        return "Username: " + Username + ", Password: " + Password + ", Usercode: " + Usercode + ", Time: " + time + ", Balance: " + balance;
    }

    public String getUsername() {
        return Username;
    }

    public int getUsercode() {
        return Usercode;
    }

    public String getPassword() {
        return Password;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public double getBalance() {
        return balance;
    }

    public void pushCSV(String Username, String Password, int Usercode, String time, double balance) {
        try {
            FileWriter pushUsrtoCSV = new FileWriter("Bankdata.csv", true);
            pushUsrtoCSV.write(Username + "," + Password + "," + Usercode + "," + time + "," + balance + "\n");
            pushUsrtoCSV.close();
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }

    //Pull from csv file
}