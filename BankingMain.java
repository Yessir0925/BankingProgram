public class BankingMain{
    public static void main(String[] args){
        System.out.println("Hello Banking Program");
        Userdata User1 = new Userdata();
        User1.setallUserData("Caleb", "password123", 12345, 10, 1000.0);
        System.out.println(User1.getallUserData());
        UserDetails userDetails = User1.getUsernameAndUsercode();
        System.out.println(userDetails);
    }
}