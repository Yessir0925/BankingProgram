import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Userdata implements Serializable {
    private String Username;
    private String Password;
    private int Usercode;
    private LocalDateTime time;
    private double balance;
    private String transnote;

    //Setters
    public void newUserData(String Username, String Password, int Usercode, LocalDateTime time, double balance, String transnote){
        this.Username = Username;
        this.Password = Password;
        this.Usercode = Usercode;
        this.time = time;
        this.balance = balance;
        this.transnote = transnote;
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

    public void settransnote(String transnote) {
        this.transnote = transnote;
    }

    //Getters
    public String getTransTimeCode() {
        return "Code: " + Usercode + ", Time: " + time + ", Balance: " + balance;
    }

    public String getNameandPassword() {
        return "Username: " + Username + ", Password: " + Password ;
    }

    public String getNameTimeBalance() {
        return "Username: " + Username + ", Time: " + time + ", Balance: " + balance;
    }

    public String getallUserData() {
        return "Username: " + Username + ", Password: " + Password + ", Usercode: " + Usercode + ", Time: " + time + ", Balance: " + balance + ", Transaction Note: " + transnote;
    }

    public String getprivateUserData() {
        return "Time: " + time + ", Balance: " + balance + ", Transaction Note: " + transnote;
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

    public String gettransnote() {
        return transnote;
    }
}