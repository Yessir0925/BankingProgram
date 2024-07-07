import java.util.Scanner;
public class BankingMain{
    public static void main(String[] args){
        System.out.println("Main Menu");

        System.out.println("Create User");
        Userdata User1 = new Userdata();
        User1.newUserData("Caleb", "password123", 12345, 10, 1000.0);
        
        System.out.println("Login");
            Scanner login = new Scanner(System.in);
            System.out.println("Username - ");
            String loginusrinp = login.nextLine();
            System.out.println("Password - ");
            String loginpassinp = login.nextLine();
            //if username and password match
            login.close();


            //if appdata file doesn't exist
                Scanner saveappdata = new Scanner(System.in);
                System.out.println("Save Login information? - ");
                String saveappdatausrinp = saveappdata.nextLine();
                if(saveappdatausrinp.equals("yes") || saveappdatausrinp.equals("y")){
                    //Create file with appdata
                    System.out.println("Login information saved");
                }
                saveappdata.close();

            System.out.println("Update Account Info");
            

            System.out.println("Remove Account");


            System.out.println("Moderator Access");




    }
}