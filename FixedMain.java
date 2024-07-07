import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FixedMain{
    static Scanner scanner = new Scanner(System.in);

    static void CreateUser(){
        //Create User
        System.out.println("1. Continue Create new user");
        System.out.print("2. Back to Menu\n-  ");
        int usrinp = scanner.nextInt();

        switch(usrinp){
            case 1:
                boolean Finish = false;
                while(Finish == false)
                {    int usercodeinp = 0;
                    int usercodemax = 0;
                    try {
                        File readCSVfirstLine = new File("Bankdata.csv");
                        Scanner Reader = new Scanner(readCSVfirstLine);
                        if (Reader.hasNextLine()) {
                            String line = Reader.nextLine();
                            usercodeinp = Integer.parseInt(line);
                            usercodemax = usercodeinp + 1;
                        }
                        Reader.close();
                    } catch (IOException e) {
                        System.out.println("IO Error");
                    }
                    Userdata newUser = new Userdata();
                    System.out.print("Enter Name - ");
                    scanner.nextLine(); // Consume newline left-over
                    String usernameinp = scanner.nextLine();

                    //password validation
                    System.out.print("Enter Password - ");
                    String userpasswordinp = scanner.nextLine();

                    LocalDateTime currentDateandTime = LocalDateTime.now();
                    newUser.newUserData(usernameinp, userpasswordinp, usercodemax, currentDateandTime, 0.00);
                    System.out.println("\n" + newUser.getallUserData());
                    //Final want to print name and password only
                    System.out.println("Is all information correct?");
                    System.out.print("1. Yes\n2. No\n-  ");
                    int usrinp2 = scanner.nextInt();
                    switch(usrinp2){
                        case 1:
                            //newUser.pushCSV(usernameinp, userpasswordinp, usercodemax, currentDateandTime.toString(), 0.00);
                            System.out.println("Appended");
                            Finish = true;
                            break;
                        case 2:
                            System.out.println("Re enter Credentials");
                            Finish = false;
                            break;
                    }
                }
                break;

            case 2:
                System.out.println("Menu");
                break;
        }
    }
    static void Login(){
        //Login
        System.out.println("Login");
        System.out.println("Username - ");
        String loginusrinp = scanner.nextLine();
        System.out.println("Password - ");
        String loginpassinp = scanner.nextLine();
        //if username and password match
        //if appdata file doesn't exist
        System.out.println("Save Login information? - ");
        String saveappdatausrinp = scanner.nextLine();
        if(saveappdatausrinp.equals("yes") || saveappdatausrinp.equals("y")){
            //Create file with appdata
            System.out.println("Login information saved");
        }
        System.out.println("Update Account Info");
        System.out.println("Remove Account");
        System.out.println("Moderator Access");
    }
    
    public static void main(String[] args) {
        boolean finishmenu = false;
        do{
            System.out.println("1. Create User");
            System.out.println("2. Login");
            System.out.print("3. Exit\n-  ");
            int menuinpint = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch(menuinpint){
                case 1:
                    System.out.println("\nCreate User");
                    CreateUser();
                    finishmenu = false;
                    break;
                case 2:
                    System.out.println("\nLogin");
                    Login();
                    finishmenu = false;
                    break;
                case 3:
                    System.out.println("\nShut Down");
                    finishmenu = true;
                    break;
            }
        }while(finishmenu == false);
        scanner.close();
    }
}