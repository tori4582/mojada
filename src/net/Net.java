package net;

import bin.BinPort;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sys.Log;

public class Net {
    
    Log l = new Log();
    BinPort bport = new BinPort();
    Desktop platform = Desktop.getDesktop();
        
    public Net() {
        
    }
    
    public void getIPConfig(String name){
        try {
            bport.bmod.sysExec(new String[]{"","ipconfig"});
        } catch (Exception ex) {
            Logger.getLogger(Net.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readHTML(String[] args) {
        try {
            String r = args[0]; if (!r.startsWith("http://")||!r.startsWith("https://")) { r = "http://"+r; }
            URL page = new URL(r);
            BufferedReader in = new BufferedReader(new InputStreamReader(page.openStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (Exception ex) {
            l.__except__(ex);
        }
    }
    
    public void visitDIR(String url) {
        if (url.startsWith("http")) {
            try {
                platform.browse(new URI(url));
            } catch (Exception ex) {
                l.__log__(l.uninit_url, ex.toString());
            }
        } else {
            try {
                platform.browse(new URI("http://"+url));
            } catch (Exception ex) {
                l.__log__(l.uninit_url, ex.toString());
            }
        }
    }
    
}
