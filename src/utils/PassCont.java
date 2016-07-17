package utils;

import static java.lang.Thread.sleep;
import java.util.Scanner;

public class PassCont {
    
    protected PassCont() {}
    
    public void main() throws Exception {
        PassCore p = new PassCore();
        
        System.out.println("PASSWORD STORAGE CONSOLE v5.0");
        System.out.println("Created by @stevealan");
        System.out.println();
        while (!p.login()) {
            System.out.println("[ A C C E S S   D E N I E D ]\n");
        }
        System.out.println("\n [ A C C E S S   G R A N T E D ]\n");
        while (!p.cmdGet()) {
            
        }
    }
}

class PassCore {
    
    protected PassCore() {
        
    }
    
    Scanner Read = new Scanner(System.in);
    
    private final String PIN = "4582";
    private String cmd, inputPIN;    
    private boolean connectedSQL = false;
    
    public boolean login() {
        System.out.print("Your PIN key : ");
        inputPIN = Read.nextLine();        
        if (inputPIN.equals(PIN)) return true;
        else return false;
    }
            
    private void checkPass (String cmd) throws Exception {
        switch(cmd) {
            case "facebook"   : System.out.println("[ thailanmuonnam ] \n[ HungLinux ]"); break;
            case "google"     : System.out.println(" [ hungtran.6360@gmail.com ] \n[ Mwg@01268896360 ]"); break; 
            case "microsoft"  : System.out.println("[ hungtran.6360@gmail.com ] \n[ Password66891153 ]"); break;
            case "dnh"        : System.out.println("[ conan4582 ] \n[ quochung ]"); break;
            case "github"     : System.out.println("[ hungtran.6360@gmail.com ] \n[ HungLinux4582 ]"); break;
            case "uit-forum"  : System.out.println("[ stevealan ] \n[ HungLinux ]"); break;
            case "000webhost" : System.out.println("[ hungtran.6360@gmail.com ] \n[ phamquochung ]"); break;
            case "wordpress"  : System.out.println("[ hungtran.6360@gmail.com ] \n[ HungLinux ]"); break;    
            case "linux-root" : System.out.println("[ ### ] \n[ 4582 ]"); break;    
            case "windows"    : System.out.println("[ ### ] \n[ 4582 ]"); break;    
            case "c9.io"      : System.out.println("[ hungtran.6360@gmail.com  ] \n[  ]"); break;    
            case "amazon"     : System.out.println("[ hungtran.6360@gmail.com  ] \n[ HungLinux ]"); break;    
            case "linkedin"   : System.out.println("[ conan1571694@gmail.com ] \n[ HungLinux ]"); break;    
            case "wikipedia"  : System.out.println("[ conan66891153] \n[ quochung ]"); break;    
            
            case "sudo sql-dtbs-get connect drive-loc": {
                System.out.println("Connecting SQL Drives via ROOT user..."); sleep(200);
                System.out.println("Reading Database..."); sleep(400);
                System.out.println("Selecting elements in Database and uploading via ROOT user"); sleep(600);
                System.out.println("Reload temperate SQL-container-memory\n"); sleep(300);
                
                System.out.println("Drive Static: google-drive 15gb reg-via-gmail");
                System.out.println("INFORMATION : DRIVER D <port> | PhoneDir: [+84]1268896360 | Date: [20][07][1978]");
                System.out.println("AUTHOR SPECIFICATED: author-drivers = [ parent -> hungtran.6360@gmail.com ] ");
                System.out.println("AUTHOR DECLARATED  : author-drivers = [ parent -> ::before ]");
                System.out.println("DRIVE-AVATAR: DTBS256.jpg");
                System.out.println("Returned values: [ Drive: <drive-name> . <port> ... <states> ]\n");
                System.out.println("Drive: hungtran.6360 ... USING      - Password::Undirected(~via parent)");
                System.out.println("Drive: driver.4582   ... ACTIVATED");
                System.out.println("Drive: driver.5948   ... ACTIVATED");
                System.out.println("Drive: driver.6689   ... ACTIVATED");
                System.out.println("Drive: driver.4918   ... ACTIVATED");
                System.out.println("Drive: driver.7327   ... ACTIVATED");
                System.out.println("Drive: driver.1571694... ACTIVATED  - comment(recovery-driver) un Parent(~via $Parent)");
                System.out.println("Drive: driver.4130   ... ACTIVATED  - executed[failed] ~via Replace[execute:<+84|1268077327>]");                
                System.out.println("Drive: driver.0000");                
                System.out.println("Drive: driver.2007");
                System.out.println("\nSTORAGE MAXIMUM = [activated-drivers~root] -> (  105  ) GB ) ");
                System.out.println("RECOVERY DRIVER: [ driver.1571694 ] storaged [  15  | GB ]");
                System.out.println("DRIVE ACCESS KEY - [ Driver-Password ]\n");                
            } break;
            
            case "sudo sql-dtbs-get connect pass" : {
                System.out.println("Importing SQL-dtbs SQL-bridge SQL-drive SQL-main packages"); sleep(1200);
                System.out.println("Connecting SQL via ROOT user..."); sleep(400);
                System.out.println("Connecting SQL Drives via ROOT user..."); sleep(200);
                System.out.println("Reading Database..."); sleep(400);
                System.out.println("Selecting elements in Database and uploading via ROOT user"); sleep(600);
                System.out.println("Reload temperate SQL-container-memory"); sleep(300);
                System.out.println("[ DONE ]\n");
            } break;
            
            default: System.out.println("Nothing to return");
        }
    }
    
    public boolean cmdGet() throws Exception {
        System.out.print("Console::Container@admin:~$ ");
        cmd = Read.nextLine();
        if (cmd.equals("sudo sql-dtbs-get connect pass")) connectedSQL = true;
        if (connectedSQL){
            if (cmd.equals("exit")) return true;
            else {checkPass(cmd); return false;}
        } else {
            System.out.println("Database not found");
            return false;
        }
    }
}