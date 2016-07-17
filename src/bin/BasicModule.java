
package bin;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.abs;
import java.net.URI;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JOptionPane;
import sys.Log;
import var.TermVar;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicModule {
    
    TermVar       var       = new TermVar();
    Scanner       reader    = new Scanner(System.in);
    Log           log       = new Log();    
    Desktop       desk      = Desktop.getDesktop();
    Calendar      calendar  = Calendar.getInstance();
    
    public       String DIR = var.HOME;
    
    public String makeString(String args[]) {
        String reString = "";
        if (args == null) { return reString; }
        else {
            for (String s : args) {
                reString += s + " ";
            }
        }
        return reString;
    }    
    public void printArray(String args[]) {
        if (args == null) { System.out.println(); }
        else {
            for (String s : args) {
                if (s != null) {System.out.print(s + " "); }
            }
        }
    }    
    public String fixPath(String path){
        return new File(path).toString();
    }
    
    public void sysExec(String args[]) throws Exception {
        String p = makeString(args);
        ProcessBuilder ps = new ProcessBuilder(p);

        ps.redirectErrorStream(true);

        Process pr = ps.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        pr.waitFor();

        in.close();
    }
    
    public void GUIRunner() {
        String r = JOptionPane.showInputDialog(null,"Type the path of file, folder, program, files\n or internet andress for us open it for you" ,"GUI-Executer", JOptionPane.INFORMATION_MESSAGE);
        if (!(r == null)) {
            if (r.startsWith("http")) {
                try {
                    desk.browse(URI.create(r));
                } catch (IOException ex) {
                    log.guiError(log.crash_io);
                }
            } else {
                File f = new File(r);
                if(f.exists()) {
                    try {
                        desk.open(f);
                    } catch (IOException ex) {
                        log.guiError(log.crash_io);
                    }
                } else {
                    log.guiError(log.loss_dir);
                }
                
            }
        }
    }
    public void mkdir(String args[]) {
        new File(makeString(args)).mkdir();
    }
    public void move(String args[]) {
        copy(args);
        new File(args[0]).delete();
    }
    public void copy(String[] args) {
        try {
            Runtime.getRuntime().exec(new String[] {"cp",args[0],args[1]});
        } catch (Exception ex) {
            log.__except__(ex);
        }
    }
    public void rename(String[] args){
        File f = new File(args[0]);
        if (f.exists()) {
            f.renameTo(new File(args[1]));
        } else {
            log.__log__(log.loss_dir, args[0]);
        }
    }
    public void echo(String[] args) {
        System.out.println(makeString(args));
    } 
    public String[] getProp(String args[]) {
        String[] props = new String[512];
        if (args[0].equals("")) {
            String prop  = System.getProperties().toString();
            props = prop.split(", ");
        } else {
            for (int i = 0; i<args.length;i++) {
                if (!args[i].equals("")) { props[i] = args[i] + " = " + System.getProperty(args[i]); }
            }
        }  
        return props;
    }
    public void viewList() {
        File file = new File(DIR);
        String[] files = file.list();
        for (String j : files) {
                System.out.print(j);
                System.out.print("\t");
            }
            System.out.println();
        
        log.__files__(files, DIR);
    }
    public void execute(String files) {
        if (files == null) {
            try {
                desk.open(new File(DIR));
            } catch (IOException ex) {
                log.__except__(ex);
            }
        } else {
        try {
            desk.open(new File(files));
        } catch (Exception ex) {
            try {
                files = fixPath(DIR) + "/" + files;
                desk.open (new File(files));
            } catch(Exception e) {
                log.__log__(log.loss_dir, files);
            }
        } }
    }
    public void clearScreen() {
        for (int i=0; i<70; i++) {
            System.out.println();
        }
    }
    public void remove(String dir) {
        File fdir = new File(dir);
        if (fdir.exists()) {
            fdir.delete();
        } else {
            log.__log__(log.loss_dir, dir);
        }
    }
    public void cd(String dir)  {
        if (dir.equals("")) {
            DIR = dir;           
            System.out.println(DIR);
        } else {  
            if (dir.equals("~")) {
                cd("");
            } else {
            File temp = new File(dir);
            if (temp.isDirectory()) {
            DIR = dir;}
            else {
                File f = new File (this.fixPath(DIR)+"/"+dir);
                if (f.exists()) {
                try {
                    DIR = f.getCanonicalPath();
                } catch(IOException e) {
                    log.__except__(e);
                }
                } else {
                    log.__log__(log.loss_dir, f.toString());
                }
            }
        }
        }
    }
    public void today() {
        System.out.println(calendar.getTime());
    }  //need
    public void timezone() {
        System.out.println(calendar.getTimeZone().toZoneId().toString());   //need
    }
    public String[] readFile(String[] args){  //need
        ArrayList al = new ArrayList();
            FileReader r = null;
            try {
                r = new FileReader(args[0]);
            } catch (FileNotFoundException ex) {
                try {
                    r = new FileReader(DIR+"/"+args[0]);
                } catch (FileNotFoundException ex1) {
                    log.__log__(log.file_not_found, "cat");
                }
            }
            BufferedReader reader = new BufferedReader(r);
            String readed = "";
            try {
                while ((readed=reader.readLine()) != null) {    
                    if (readed == null) { readed = ""; } 
                    al.add(readed);
                }    
            } catch (IOException ex) {
                    log.__except__(ex);
            }
        return Arrays.stream(al.toArray()).toArray(String[]::new);    
    }
    public String hash(String[] args) {
        String p = this.makeString(args);
        return new String(new StringBuilder(abs(p.hashCode())));
    }
    public void showProcess() {
        try {
            this.sysExec(new String[] {"tasklist"});
        } catch (Exception ex) {
            log.__except__(ex);
        }
    }
    public void killProcess(String[] args) {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM "+args[0]);
        } catch (IOException ex) {
            log.__except__(ex);
        }
    }
    
    public void showHelp() {
            System.out.println();
            System.out.println("MOJADA SHELL HELP");
            System.out.println("=================");
            System.out.println("Mojada Shell is a faster and more convenience way to system administration or working with system");
            System.out.println();
            System.out.println("{  UPDATE INFORMATION  }===================================");
            System.out.println(" Current version = [ " + log.VERSION + " ] \n");
            System.out.println("+ Change the architechture.");
            System.out.println("+ Performance optimizing");
            System.out.println("+ Update the system");
            System.out.println("+ Add more functions and fix bugs");
            System.out.println("===========================================================");
            System.out.println();
            System.out.println(" * About The Prompt:");
            System.out.println("<username> > ");
            System.out.println("Shell have two level to interactive with system is root and user permission");
            System.out.println(" + USER: you only work with non-root-require commands and directories");
            System.out.println(" + ROOT: you can work with all commands and directories with the highest level of administrator");
            System.out.println();
            System.out.println(" * About The Commands Packages:");
            System.out.println("Commands package is a unit that contained all necessary application and files for command execution");
            System.out.println("Have some packages already implemented: bin, src, util, lib, sys, var");
            System.out.println("Other packages want to implement with shell system need to implement via 'package-installer' command");
            System.out.println("In fact, 'bin', 'lib' and 'sys' alway loaded as default.");
            System.out.println("Another package need to load for use, use 'loader-package' from utility 'loader'");
            System.out.println("Commands contained in loaded packages can use directly. If a command contained in the unloaded package. That command can't execute.");
            System.out.println("Use a command in loaded package: use 'loader-module' from utility 'loader'");
            System.out.println();
            System.out.println(" * About basic commands");
            System.out.println("<command> [<key>] [<arguments>]");
            System.out.println("command is uncase-sensitived");
            System.out.println("NOTE: Some command will unsupport key");
            System.out.println();
            System.out.println("author   -  See the information of Shell's authors");
            System.out.println("ver      -  See the shell's version");
            System.out.println("cpright  -  See the shell's copyright");
            System.out.println("project  -  See the project name");
            System.out.println("this?    -  See the shell's name");
            System.out.println("lastupd  -  See the shell's updated date");
            System.out.println("echo     -  Print a line on shell");
            System.out.println("getinfo  -  Get the information of system and Java");
            System.out.println("where    -  Get the current location");
            System.out.println("cd       -  changes directory");
            System.out.println("home     -  Come back to HOME Directory");
            System.out.println("ls       -  Get all files and folders in current directory");
            System.out.println("run      -  Execute a executable file");
            System.out.println("visit    -  Browse a website");
            System.out.println("clrscr   -  Clear screen of terminal");
            System.out.println("rename   -  Change file or folder name");
            System.out.println("mkdir    -  Create a empty folder");
            System.out.println("rm       -  Remove file or folder");
            System.out.println("today    -  Get the current time and date");
            System.out.println("timezone -  Get the current location timezone");
            System.out.println("cp       -  copy file (Please type full path of files)");
            System.out.println("move     -  move file (Please type full path of files)");
            System.out.println("ps       -  Get all running processes as list");
            System.out.println("tkill    -  Kill a specificated process");
            System.out.println("cat      -  Reading content of specificated file on disk");
            System.out.println("hash     -  Get hash code of arguments");
            System.out.println("ipconfig -  Get Windows ethernet information");
            System.out.println();
            System.out.println(" * About the basic modules");
            System.out.println(" Modules is the small applications installed to shell system or");
            System.out.println("procedures. Module is a independent part and places in diffirence");
            System.out.println("packages. Module cannot run directly from console (in Kernel) for");
            System.out.println("secure reasons. So, you must run them with Mediate-Transporter");
            System.out.println("called 'loader' (in CommunityPort)");
            System.out.println(" Modules has placed in diffirence packages and these packages will");
            System.out.println("not loaded as default for memory saving. So, you need to initialize");
            System.out.println("these packages before use any module in them.");
            System.out.println(" Although packages need to initialize before using, some Modules don't");
            System.out.println("need it. Because it is System-Modules or already contained in default");
            System.out.println("packages.");
            System.out.println();
            System.out.println(" We need to initialize a Package or run a Module via 'loader'");
            System.out.println("INITIALIZE A PACKAGES: loader-package <packages-name>");
            System.out.println("RUN        A MODULE  : loader-module <initialized-package> <module>");
            System.out.println();
            System.out.println("We already have some basic modules here...");
            System.out.println("KEYNOTE : # - already loaded , !! - unsupported or not work correctly , % - invalid or not found");
            System.out.println();
            System.out.println("MODULE           | PACKAGE    | AUTHOR   | FUNCTION                         | NOTE");
            System.out.println("-----------------+------------+----------+----------------------------------+-----");
            System.out.println("run-gui          | sys/modules|@system   | This is a way to exec 'run' and  | #   ");
            System.out.println("                 |            |          |'visit' command with GUI-Dialog   |");
            System.out.println("dev-solver       | util       |@SteveAlan| Solve a exception, bug or problem| #");
            System.out.println("                 |            |          |- a effective tool for developers.|");
            System.out.println("readsite         | net        |@system   | Get HTML source of specificated  | # !!");
            System.out.println("                 |            |          |webpage and show them to screen   |");
            System.out.println("moj              | sys/io     |@SteveAlan| Interpreting a .moj file as list | #");
            System.out.println("                 |            |          |of commands                       |");
            System.out.println();
    }
}
