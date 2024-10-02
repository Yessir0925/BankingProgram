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
            // Do nothing to avoid writing a header when appending
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

                try (ObjectInputStream fileinput = new ObjectInputStream(new FileInputStream("Bankdata.ser"))) {
                    Userdata lastUser = (Userdata) fileinput.readObject();
                    usercodeinp = lastUser.getUsercode();
                    usercodemax = usercodeinp + 1;
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("IO Exception - " + e.getMessage());
                    usercodemax = 1;
                }

                do
                {                    
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
                                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Bankdata.ser", true))) {
                                    if (new File("Bankdata.ser").length() == 0) {
                                        oos = new ObjectOutputStream(fos); // Write header for the first time
                                    } else {
                                        oos = new AppendableObjectOutputStream(fos); // Skip header for appending
                                    }
                                    oos.writeObject(newUser);
                                    oos.close();
                                    System.out.println("Appended");
                                } catch (IOException e) {
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
    
    
    static void GlobalFileView() {
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
            System.out.println("IO Exception - " + e.getMessage());
        }
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
                case 4:
                    System.out.println("Global File View");
                    GlobalFileView();
                    finishmenu = false;
            }
        }while(finishmenu == false);
        usrinpsc.close();
    }
}