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

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    static void CreateUser(Scanner usrinpsc){
        System.out.println("1. Continue Create new user");
        System.out.print("2. Back to Menu\n-  ");
        int usrinp = usrinpsc.nextInt();
        usrinpsc.nextLine();
        switch(usrinp){
            case 1:
                boolean Finish = false;
                Userdata newUser = new Userdata();
                
                int usercodemax = getUserCodeMax();

                do
                {    
                    Boolean SingularSum = false;                
                    System.out.print("Enter username - ");
                    String usernameinp = usrinpsc.nextLine();
                    System.out.print("Enter Password - ");
                    String userpasswordinp = usrinpsc.nextLine();
                    System.out.print("Re-enter password - ");
                    String userpasswordinp2 = usrinpsc.nextLine();
                    if(userpasswordinp.length() >= 8 && userpasswordinp.equals(userpasswordinp2) == true && isPresent(usernameinp, SingularSum) == -1){
                        LocalDateTime currentDateandTime = LocalDateTime.now();
                        newUser.newUserData(usernameinp, userpasswordinp, usercodemax, currentDateandTime, 0.00, "Create account");
                        System.out.println("\n" + newUser.getNameandPassword());
                        System.out.println("Is all information correct?");
                        System.out.print("1. Yes\n2. No\n-  ");
                        int usrinp2 = usrinpsc.nextInt();
                        usrinpsc.nextLine();
                        switch(usrinp2){
                            case 1:
                                String filename = "Bankdata.ser";
                                Addtofile(newUser, filename);
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
                    } else if(isPresent(usernameinp, SingularSum) != -1){
                        System.out.println("Select another username\n");
                    }
                }while(Finish == false);
                    break;

            case 2:
                System.out.println("\nMenu");
                break;
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------


    static void Login(Scanner usrinpsc){
        System.out.println("Login");
            usrinpsc.nextLine();
            System.out.print("Username - ");
            String loginusrinp = usrinpsc.nextLine();
            System.out.print("Password - ");
            String loginpassinp = usrinpsc.nextLine();

            boolean Usrpassmatch = false;
            Userdata loggedInUser = null;

            try (ObjectInputStream fileinput = new ObjectInputStream(new FileInputStream("Bankdata.ser"))) {
                Boolean SingularSum = false;
                for(int i = 0   ; i < isPresent(loginusrinp, SingularSum); i++){
                    try {
                        Userdata user = (Userdata) fileinput.readObject();
                        if(loginusrinp.equals(user.getUsername())){
                            if(loginpassinp.equals(user.getPassword())){
                                Usrpassmatch = true;
                                loggedInUser = user;
                            } else {
                                Usrpassmatch = false;
                            }
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
    
            if(Usrpassmatch == true){
                try (FileWriter fileWriter = new FileWriter("Appdata.csv")) {
                    String Logusername = loggedInUser.getUsername();
                    String Logpassword = loggedInUser.getPassword();
                    int Logusercode = loggedInUser.getUsercode();
                    fileWriter.append(Logusername);
                    fileWriter.append(",");
                    fileWriter.append(Logpassword);
                    fileWriter.append(",");
                    fileWriter.append(String.valueOf(Logusercode));
                    Boolean SingularSum = true;
                    String Queryname = loginusrinp;
                    isPresent(Queryname, SingularSum);
                    System.out.println("Logged In\n");
                } catch (IOException e) {
                    System.out.println("IO Exception - " + e.getMessage());
                }
            } else {
                System.out.println("Wrong Credentials\n");
            }

    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    static void Admin(Scanner usrinpsc) {
        System.out.println("1. Global Fileview");
        System.out.println("2. Wipe Data file");
        System.out.print("3. Query User\n - ");
        int adminmenu = usrinpsc.nextInt();
        usrinpsc.nextLine();
        switch(adminmenu){
            case 1:
                System.out.println("Global Fileview");
                String filename = "Bankdata.ser";
                Boolean isAdmin = true;
                Readfullfile(isAdmin,filename);
                break;
            case 2:
                wipeFile("Bankdata.ser");
                break;
            case 3:
                System.out.print("Enter Query - ");
                String Queryname = usrinpsc.nextLine();
                Boolean SingularSum = false;
                if(isPresent(Queryname, SingularSum) != -1){
                    System.out.println("Exists at - " + isPresent(Queryname, SingularSum));
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

    static Double Lastobject(){
    
        Double SavedBalance = 0.0;
        try (ObjectInputStream fileinput = new ObjectInputStream(new FileInputStream("PersonalBankdata.ser"))) {
            Userdata lastUser = null;
            while (true) {
                try {
                    lastUser = (Userdata) fileinput.readObject();
                } catch (EOFException e) {
                    break;
                }
            } if (lastUser != null) {
                SavedBalance = lastUser.getBalance();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("IO Exception - " + e.getMessage());
        }
        return SavedBalance;
    }




    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    static void Readfullfile(Boolean isAdmin,String filename){
        try (ObjectInputStream fileinput = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Userdata user = (Userdata) fileinput.readObject();
                    if(isAdmin == true){
                        System.out.println(user.getallUserData());
                    } else {
                        System.out.println(user.getprivateUserData());
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
        System.out.print("\n");
    }




    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------


    static void wipeFile(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(new byte[0]);
            if(Checkempty() == true){
                System.out.println("Successfully Cleared");
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("IO Exception - " + e.getMessage());
        }
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    static int getUserCodeMax(){
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
                return usercodemax;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------


    static int isPresent(String Queryname, Boolean SingularSum){
        int NameLocation = -1;
        int UnconfirmLocation = 1;
        boolean eof = true;
        boolean Addtofile = false;
        if(SingularSum == true){
            Addtofile = true;
        }
        try (ObjectInputStream fileinput = new ObjectInputStream(new FileInputStream("Bankdata.ser"))) {
            String filename = "PersonalBankdata.ser";
            while (eof == true) {
                try {
                    Userdata user = (Userdata) fileinput.readObject();
                    if(Queryname.equals(user.getUsername())){
                        NameLocation = UnconfirmLocation;
                        if(Addtofile == true){
                            Addtofile(user, filename);
                        }
                    }
                    UnconfirmLocation++;
                } catch (EOFException e) {
                    eof = false;
                }
            } 
        } catch (IOException | ClassNotFoundException e) {
            if(Checkempty() == false){
                System.out.println("IO Exception - " + e.getMessage());
            } else {
                System.out.println("Database Empty");
            }
        }
        return NameLocation;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------


    static void Addtofile(Userdata newUser, String filename){
        try{
            FileOutputStream fos = new FileOutputStream(filename, true);
            ObjectOutputStream oos;
            if (new File(filename).length() == 0) {
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
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    static void Transactions(Scanner usrinpsc){
        System.out.println("1. View Transaction");
        System.out.println("2. Add Transaction");
        int Transactionmenu = usrinpsc.nextInt();
        usrinpsc.nextLine();
        if(Transactionmenu == 1){
            String filename = "PersonalBankdata.ser";
            Boolean isAdmin = false;
            Readfullfile(isAdmin,filename);
        } else if(Transactionmenu == 2){
            System.out.print("Enter Transaction Amount - ");
            double Addtransactions = usrinpsc.nextDouble();


            String SavedLogin = null;
            String SavedPassword = null;
            int Usercode = 0;

            try (Scanner scanner = new Scanner(new FileReader("Appdata.csv"))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] values = line.split(",");
                    SavedLogin = values[0];
                    SavedPassword = values[1];
                    Usercode = Integer.parseInt(values[2]);
                }
            LocalDateTime currentDateandTime = LocalDateTime.now();



            double SavedBalance = 0.0;
            SavedBalance = Lastobject();

            
            SavedBalance = SavedBalance + Addtransactions;

            String usernameinp = SavedLogin;
            String userpasswordinp = SavedPassword;
            int usercodemax = Usercode++;
            Double balance = SavedBalance;
        
            Userdata newUser = new Userdata();
            usrinpsc.nextLine();
            System.out.println("Add note: ");
            String transnote = usrinpsc.nextLine();
            newUser.newUserData(usernameinp, userpasswordinp, usercodemax, currentDateandTime, balance, transnote);
            System.out.println("\n" + newUser.getNameTimeBalance());


            String filename = "Bankdata.ser";
            Addtofile(newUser, filename);
            filename = "PersonalBankdata.ser";
            Addtofile(newUser, filename);
            

            } catch (IOException e) {
                System.out.println("IO Exception - " + e.getMessage());
            }
        }
    }


    
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Scanner usrinpsc = new Scanner(System.in);
        boolean finishmenu = false;
        File appdataFile = new File("Appdata.csv");
        do{
            if(appdataFile.length() == 0){
                System.out.println("1. Create User");
                System.out.println("2. Login");
                System.out.print("3. Exit\n-  ");
            } else {
                System.out.println("Balance - $" + Lastobject());
                System.out.println("1. Logout");
                System.out.println("2. Transactions");
                System.out.print("3. Exit\n-  ");
            }
            int menuinpint = usrinpsc.nextInt();
            switch(menuinpint){
                case 1: 
                    if(appdataFile.length() == 0){
                        System.out.println("\nCreate User");
                        CreateUser(usrinpsc);
                        finishmenu = false;
                    } else {
                        wipeFile("Appdata.csv");
                        wipeFile("PersonalBankdata.ser");
                    }
                    break;
                case 2:
                    if(appdataFile.length() == 0){
                        Login(usrinpsc);
                        finishmenu = false;
                    } else {
                        System.out.println("\nTransactions");
                        Transactions(usrinpsc);
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