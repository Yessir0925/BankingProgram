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

    public int getTime() {
        return time;
    }

    public double getBalance() {
        return balance;
    }

    //Push to csv file
    //Pull from csv file
}