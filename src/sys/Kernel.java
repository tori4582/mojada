/**
 * Package: sys
 * Class  : Kernel
 * 
 * This is the class contain everything important of program.
 * And it is the most important class of program, it is core and kernel.
 * It contained base ways to communicate with other class and user.
 * 
 * [CAUTION]: Don't let any class create instead of this class.
 */
package sys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import var.TermVar;


public class Kernel {         
    
    private SysPort   port = new SysPort()         ;
    private Log        log = new Log()             ;
    private TermVar    var = new TermVar()         ;
    private Scanner reader = new Scanner(System.in);
    private String  user   = ""                    ; 
    private String  input,cmd;
    private String  term     ;
    private String  inputs[] ;
    private String  option[] ;
    private String  args[]   ;
    
    private void write  (Object c) { if ( c != null ) { System.out.print(c); } else System.out.print(""); }
    private void writeln(Object c) { if (c != null ) { System.out.println(c); } else System.out.println(""); }
    
    public Kernel(){
        for (String s : var.USER.split(" ")) { user += s; }
        
        term = user + " " + var.TERM_CHAR + " "; //prompt
    }
    public void __init__() {
        
        writeln(log.NAME + " v"+ log.VERSION + " for "+var.OS+" "+var.ARCH);
        writeln("Created by " + log.AUTHOR);
        writeln(null);
        
        while(cmdCheck()){
            //nothing
        }
    }
    private boolean cmdCheck() {
        write(term);
        
        input  = reader.nextLine();
        inputs = input.split(" ");
        cmd    = inputs[0];
        
        ArrayList<String> alOptions = new ArrayList();
        ArrayList<String> alArgs    = new ArrayList();
        
        for (int i=1; i<inputs.length;i++) //init options
        {
            String word = inputs[i];
            if (word.startsWith("-")) {
                alOptions.add(word);
            } else {
                alArgs.add(word);
            }
        }
        
        option = Arrays.stream(alOptions.toArray()).toArray(String[]::new);
        args   = Arrays.stream(alArgs   .toArray()).toArray(String[]::new);
        
        if (input.equals("")) return true;
        else if (cmd.equals("exit")) {
            log.__log__(log.close, null);
            System.exit(0);
            return false;
        } else {
            if (inputs.length == 0) { cmdExec(cmd, null, null); }
            else cmdExec(cmd, option, args);
            return true;
        }
    }
    
    public void cmdExec(String cmdInput, String option[], String[] args) {
        if (!cmdInput.startsWith("//")) {
        switch(cmdInput.toLowerCase()) {
            
            //-----------INFORMATION COMMANDS-----------
            case "author"  : writeln(log.AUTHOR); break;
            case "ver"     : writeln(log.VERSION); break;
            case "cpright" : writeln(log.COPYR); break;
            case "project" : writeln(log.PROJECT); break;
            case "this?"   : writeln(log.NAME); break;
            case "lastupd" : writeln(log.LASTUPD); break;
            case "info"    : port.bin.bmod.printArray(port.bin.bmod.getProp(args)); break;
            case "where"   : writeln(port.bin.bmod.DIR); break;
            case "home"    : writeln(var.HOME); break;
            //-----------BASICALLY COMMANDS-------------
            case "echo"    : port.bin.bmod.echo(args); break;            
            case "clrscr"  : port.bin.bmod.clearScreen();  break;
            case "cd"      : port.bin.bmod.cd(args[0]); break;
            
            case "ls"      : port.bin.bmod.viewList(); break;
            case "run"     :  try {port.bin.bmod.execute(args[1]);} catch (ArrayIndexOutOfBoundsException e) {port.bin.bmod.execute(null);}; break;
            case "visit"   : try {port.net.visitDIR(args[0]);} catch (ArrayIndexOutOfBoundsException e) {log.__log__(log.loss_args, cmd);}; break;
            case "rename"  : port.bin.bmod.rename(args); break;
            case "mkdir"   : port.bin.bmod.mkdir(args); break;
            case "rm"      : try {port.bin.bmod.remove(args[1]);} catch (ArrayIndexOutOfBoundsException e) {log.__log__(log.loss_args, cmd);} break;
            case "run-gui" : port.bin.bmod.GUIRunner(); break;
            
            case "dev-solver": utils.DevProblem d = new utils.DevProblem(); d.__main__(); break;
            
            case "today"   : port.bin.bmod.today(); break;
            case "timezone": port.bin.bmod.timezone(); break;
            case "ps"      : port.bin.bmod.showProcess(); break;
            case "ipconfig": port.net.getIPConfig(this.user); break;
            
            case "cat"     : port.bin.bmod.printArray(port.bin.bmod.readFile(args)); System.out.println(); break;
            case "readsite": port.net.readHTML(args); break;
            
            case "moj"     : {
                try{
                if (args[1].endsWith(".moj")){
                File f = new File(args[1]);
                String current = "";
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(f));
                    while (current != null) {
                        current = reader.readLine();
                        String[] spl = current.split(" ");
                        this.cmdExec(spl[0], spl, spl);
                    }
                    break;
                } catch (Exception ex) {
                    try {
                        if (port.bin.bmod.DIR.endsWith("/") || port.bin.bmod.DIR.endsWith("\\")) { f = new File(port.bin.bmod.DIR+args[1]); }
                        else {f = new File(port.bin.bmod.DIR+"/"+args[1]);}
                       if(f.exists()){
                        BufferedReader reader = new BufferedReader(new FileReader(f));
                        while (current != null) {
                        current = reader.readLine();
                        String[] spl = current.split(" ");
                        this.cmdExec(spl[0], spl, spl);
                        }
                        break; } else {
                           log.__log__(log.loss_dir, args[1]);
                       }
                    } catch(Exception e) {
                    }
                }
                } else log.__log__(log.unitit_filetype, args[1]);}
                catch(ArrayIndexOutOfBoundsException e) {
                    log.__log__(log.loss_args, cmd);
                } 
                
            } break;
            
            case "sys": try {port.bin.bmod.sysExec(args);} catch (Exception e) {log.__except__(e);} break;
            
            case "tkill": port.bin.bmod.killProcess(args); break;
            case "cp"   : port.bin.bmod.copy(args); break;
            case "move" : port.bin.bmod.move(args); break;
            case "hash" : port.bin.bmod.hash(args); break;
            
            case "help": port.bin.bmod.showHelp(); break;
            default: log.__log__(log.command_invalid, cmdInput);
        }
        }
    }
    
    public static void main(String[] args) {
        
        Kernel kernel = new Kernel();
        kernel.__init__();
        
    }
    
}

