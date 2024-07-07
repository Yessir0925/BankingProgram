import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class BankingMain{
    static boolean CreateUser(){
        //Create User
        Scanner usrinpsc = new Scanner(System.in);
        System.out.println("1. Continue Create new user");
        System.out.print("2. Back to Menu\n-  ");
        int usrinp = usrinpsc.nextInt();
        switch(usrinp){
            case 1:
                boolean Finish = false;
                Scanner namepasswordsc = new Scanner(System.in);
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
                    String usernameinp = namepasswordsc.nextLine();



                    //password validation
                    System.out.print("Enter Password - ");
                    String userpasswordinp = namepasswordsc.nextLine();
                    




                    LocalDateTime currentDateandTime = LocalDateTime.now();
                    newUser.newUserData(usernameinp, userpasswordinp, usercodemax, currentDateandTime, 0.00);
                    System.out.println("\n" + newUser.getallUserData());
                    //Final want to print name and password only
                    System.out.println("Is all information correct?");
                    System.out.print("1. Yes\n2. No\n-  ");
                    int usrinp2 = namepasswordsc.nextInt();
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
                namepasswordsc.close();
                





            case 2:
                System.out.println("Menu");
                break;
        }
        usrinpsc.close();
        return true;
    }
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
    
    public static void main(String[] args){
        System.out.println("Main Menu");
        Scanner menuInpSc = new Scanner(System.in);
        int menuInp = 0;
        while(menuInp != 3){
            System.out.println("1. Create User");
            System.out.println("2. Login");
            System.out.print("3. Exit\n-  ");
            if (menuInpSc.hasNextInt()) {
                menuInp = menuInpSc.nextInt();
                menuInpSc.nextLine();
                switch(menuInp){
                    case 1:
                        System.out.println("\nCreate User");
                        boolean userCreated = CreateUser();
                        if (userCreated) {
                            menuInp = 3;
                        }
                        break;


                    case 2:

                        System.out.println("\nLogin User");
                        //Login();
                        break;
                    
                    case 3:
                        System.out.println("Exiting Program");
                        break;
                }
            }
        }
        menuInpSc.close();
    }
}