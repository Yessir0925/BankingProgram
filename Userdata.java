public class Userdata {
    private String Username;
    private String Password;
    private int Usercode;
    private int time;
    private double balance;

    public void setallUserData(String Username, String Password, int Usercode, int time, double balance){
        this.Username = Username;
        this.Password = Password;
        this.Usercode = Usercode;
        this.time = time;
        this.balance = balance;
    }

    public String getallUserData() {
        return "Username: " + Username + ", Password: " + Password + ", Usercode: " + Usercode + ", Time: " + time + ", Balance: " + balance;
    }

    public int getUsernameandUsercode() {
        return Usercode;
    }

    public UserDetails getUsernameAndUsercode() {
        return new UserDetails(Username, Usercode);
    }

}