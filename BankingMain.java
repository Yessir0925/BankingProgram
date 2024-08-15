import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class BankingMain{
    static void CreateUser(Scanner usrinpsc){
        //Create User
        System.out.println("1. Continue Create new user");
        System.out.print("2. Back to Menu\n-  ");
        int usrinp = usrinpsc.nextInt();
        usrinpsc.nextLine(); //Clear scanner buffer
        switch(usrinp){
            case 1:
                boolean Finish = false;
                Userdata newUser = new Userdata();
                do
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

                    
                    System.out.print("Enter Name - ");
                    String usernameinp = usrinpsc.nextLine();
                    System.out.print("Enter Password - ");
                    String userpasswordinp = usrinpsc.nextLine();
                    System.out.print("Re-enter password - ");
                    String userpasswordinp2 = usrinpsc.nextLine();
                    if(userpasswordinp.length() >= 8 && userpasswordinp.equals(userpasswordinp2) == true){
                        LocalDateTime currentDateandTime = LocalDateTime.now();
                        newUser.newUserData(usernameinp, userpasswordinp, usercodemax, currentDateandTime, 0.00);
                        System.out.println("\n" + newUser.getNameandPassword());
                        System.out.println("Is all information correct?");
                        System.out.print("1. Yes\n2. No\n-  ");
                        int usrinp2 = usrinpsc.nextInt();
                        usrinpsc.nextLine();
                        switch(usrinp2){
                            case 1:
                                //newUser.pushCSV(usernameinp, userpasswordinp, usercodemax, currentDateandTime.toString(), 0.00);
                                System.out.println("Appended\n");
                                Finish = true;
                                break;
                            case 2:
                                System.out.println("Re enter Credentials\n");
                                Finish = false;
                                break;
                        }
                    } else if(userpasswordinp.length() < 8){
                        System.out.println("Password must be at least 8 characters long\n");
                    } else if(userpasswordinp.equals(userpasswordinp2) == false){
                        System.out.println("Passwords do not match\n");
                    }
                }while(Finish == false);
                break;

            case 2:
                System.out.println("\nMenu");
                break;
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------s
    static void Login(){
        //Login
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
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        Scanner usrinpsc = new Scanner(System.in);
        boolean finishmenu = false;
        do{
            System.out.println("1. Create User");
            System.out.println("2. Login");
            System.out.print("3. Exit\n-  ");
            int menuinpint = usrinpsc.nextInt();
            switch(menuinpint){
                case 1:
                    System.out.println("\nCreate User");
                    CreateUser(usrinpsc);
                    finishmenu = false;
                    break;
                case 2:
                    System.out.println("\nLogin");
                    finishmenu = false;
                    break;
                case 3:
                    System.out.println("\nShut Down");
                    finishmenu = true;
                    break;
            }
        }while(finishmenu == false);
        usrinpsc.close();
    }
}