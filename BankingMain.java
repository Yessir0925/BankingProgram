import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;


public class BankingMain{

    static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }

    static void CreateUser(Scanner usrinpsc){
        //Create User
        System.out.println("1. Continue Create new user");
        System.out.print("2. Back to Menu\n-  ");
        int usrinp = usrinpsc.nextInt();
        usrinpsc.nextLine();
        switch(usrinp){
            case 1:
                boolean Finish = false;
                Userdata newUser = new Userdata();
                int usercodeinp = 0;
                int usercodemax = 0;    

                if (Checkempty()) {
                    usercodemax = 1;
                } else {
                    try (ObjectInputStream fileinput = new ObjectInputStream(new FileInputStream("Bankdata.ser"))) {
                        while (true) {
                            try {
                                Userdata lastUser = (Userdata) fileinput.readObject();
                                usercodeinp = lastUser.getUsercode();
                                usercodemax = usercodeinp + 1; 
                            } catch (EOFException e) {
                                break; 
                            }
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        if(Checkempty() == false);
                            System.out.println("IO Exception - " + e.getMessage());
                    }
            }

                do
                {                    
                    System.out.print("Enter username - ");
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
                                try{
                                    FileOutputStream fos = new FileOutputStream("Bankdata.ser", true);
                                    ObjectOutputStream oos;
                                    if (new File("Bankdata.ser").length() == 0) {
                                        oos = new ObjectOutputStream(fos);
                                    } else {
                                        oos = new AppendableObjectOutputStream(fos);
                                    }
                                    oos.writeObject(newUser);
                                    oos.close();
                                    System.out.println("Appended\n");
                                } catch (IOException e) {
                                    if(Checkempty() == false);
                                        System.out.println("IO Exception - " + e.getMessage());
                                }
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
    
    
    static void Admin(Scanner usrinpsc) {
        System.out.println("1. Global Fileview");
        System.out.println("2. Wipe Data file");
        System.out.println("3. Query User\n");
        int adminmenu = usrinpsc.nextInt();
        usrinpsc.nextLine();
        switch(adminmenu){
            case 1:
                System.out.println("Global Fileview");
                try (ObjectInputStream fileinput = new ObjectInputStream(new FileInputStream("Bankdata.ser"))) {
                    while (true) {
                        try {
                            Userdata user = (Userdata) fileinput.readObject();
                            System.out.println(user.getallUserData());
                        } catch (EOFException e) {
                            break;
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    if(Checkempty() == false){
                        System.out.println("IO Exception - " + e.getMessage());
                    } else {
                        System.out.println("Database Empty");
                    }
                }
                System.out.print("\n");
                break;
            case 2:
                try (FileOutputStream fos = new FileOutputStream("Bankdata.ser")) {
                    fos.write(new byte[0]);
                    if(Checkempty() == true){
                        System.out.println("Successfully Cleared\n");
                    } else {
                        System.out.println("Error clearing\n");
                    }
                } catch (IOException e) {
                    if(Checkempty() == false){
                        System.out.println("IO Exception - " + e.getMessage());
                    }
                }
                break;
            case 3:
                System.out.print("Enter Query - ");
                String Queryname = usrinpsc.nextLine();
                if(isPresent(Queryname) == true){
                    System.out.println("Exists");
                } else {
                    System.out.println("Does not exists");
                }
                System.out.print("\n");
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    static boolean Checkempty(){
        File isempty = new File("Bankdata.ser");
        if(isempty.length() == 0){
            return true;
        } else {
            return false;
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    static boolean isPresent(String Queryname){
        try (ObjectInputStream fileinput = new ObjectInputStream(new FileInputStream("Bankdata.ser"))) {
            while (true) {
                try {
                    Userdata user = (Userdata) fileinput.readObject();
                    if(Queryname.equals(user.getUsername())){
                        return true;
                    }
                } catch (EOFException e) {
                    break;
                }
            }   
        } catch (IOException | ClassNotFoundException e) {
            if(Checkempty() == false){
                System.out.println("IO Exception - " + e.getMessage());
            } else {
                System.out.println("Database Empty");
            }
        }
        return false;
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
                    File appdataFile = new File("Appdata.txt");
                    if(appdataFile.length() == 0){
                        System.out.println("\nLogin\n");
                        //Login
                        finishmenu = false;
                    } else {
                        //Transactions(); [Query]
                    }
                    break;
                case 3:
                    System.out.println("\nShut Down");
                    finishmenu = true;
                    break;
                case 1423:
                    System.out.println("\nAdmin");
                    Admin(usrinpsc);
                    finishmenu = false;
            }
        }while(finishmenu == false);
        usrinpsc.close();
    }
}