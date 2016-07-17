/**
 * Package : sys
 * Class   : Log
 * 
 * This is the class contain all content of communication between user and shell
 *  include Notifications, Errors, Logs, Warning, etc.
 * This is also contain copyright information of program.
 * 
 * [CAUTION]: This is independent class, do not create objects form other class in this class.
 */
package sys;

import java.awt.Toolkit;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Log {
  
    public final String AUTHOR  = "Pham Quoc Hung ( Steve Alan )";
    public final String NAME    = "Mojada Shell";
    public final String VERSION = "1.0";
    public final String LASTUPD = "13 July 2016";
    public final String COPYR   = "(C) Steve Alan 2016";
    public final String PROJECT = "(P) MOJADA - 2016";        

    public void __log__     (String logname, String cmdInput) {
        if (cmdInput==null) System.out.println(logname);
        else System.out.println("'"+cmdInput+"' : "+ logname);        
    }
    public void __files__   (String[] files, String dir) {
        System.out.println("There are "+files.length+" files or directories in "+ dir);
    }
    public void __except__  (Exception ex) {
        ex.printStackTrace();
    }
    public void __load__    (String what, String where, String to) {
        if (to==null || where==null) {
            if (to == null && where == null) {
                System.out.println("Loading: "+what+" ...");
            }else if (to == null) {
                System.out.println("Loading: "+what+" from "+where+" ...");
            } else if (where == null){
                System.out.println("Loading: "+what+" and uploading to "+to+" ...");
            } else {
                System.out.println("Loading: "+what+" ...");
            }
        } else {
            System.out.println("Loading: "+what+" form "+where+" and uploading to "+to+" ...");
        }
    }
    public boolean guiWarning(String content) {
        Toolkit.getDefaultToolkit().beep();
        int state = JOptionPane.showConfirmDialog(null, "Warning: "+content+" !", "Warning", JOptionPane.OK_OPTION + JOptionPane.WARNING_MESSAGE);
        if (state == 0) { return true; } else { return false; }
    }
    public void guiError(String content) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, "Error: "+content, "Error has occured", JOptionPane.OK_OPTION + JOptionPane.ERROR_MESSAGE);
    }
    
    // ------------ MESSAGES CONTENT ---------------
    
    public String command_invalid     = "invalid command";
    public String command_unsupported = "command unsupported because old or might make a damage on your system or return unexpectedly results.";
    public String command_denied  = "permission denied";
    public String command_need_root   = "root authorization required";
    public String command_unexpected  = "command returned undefined errors";
    
    public String loss_require_file   = "requiring files or directories not found";
    public String loss_require_pack   = "requiring packages not found";
    public String loss_require_module = "requiring modules not found";
    public String loss_args           = "requires arguments not found";
    public String loss_port           = "requires sent via invalid port";
    public String loss_dir            = "no such file or directory";
        
    public String crash_system        = "something went wrong in system";
    public String crash_io            = "in and out activities went wrong";
    
    public String file_not_found      = "file's location is wrong";
    
    public String uninit_packages     = "invalid or uninitialized packages";
    public String uninit_url          = "invalid or uninitialized url/uri address";
    public String unitit_filetype     = "invalid or uninitialized file type";
    
    public String close               = "closing...";
    public String close_port          = "closing port...";
    public String close_queue         = "closing queue...";
    
    
    
}
