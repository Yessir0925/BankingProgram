public class BankingMain{
    public static void main(String[] args){
        System.out.println("Main Menu");

        System.out.println("Add User");
        Userdata User1 = new Userdata();
        User1.newUserData("Caleb", "password123", 12345, 10, 1000.0);
        System.out.println(User1.getallUserData());
        
        System.out.println("Login");

            System.out.println("Update Account Info");
            

            System.out.println("Remove Account");


            System.out.println("Moderator Access");



    }
}