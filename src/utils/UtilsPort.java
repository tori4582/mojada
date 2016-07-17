package utils;

import sys.Log;

public class UtilsPort {
    
    public PassCont     passcont;
    
    private Log log = new Log();
    
    private boolean init = false;
    
    public void init() {
        log.__load__("modules", "'Util' packages", "queue");
        log.__load__("passcont.java", null, null);
        log.__load__("youtubeplan.java", null, null);
        log.__log__(log.close_port, null);
        
        passcont    = new PassCont();
        
        
        init = true;
    }
    
    public void execModule(String module) {
        if (init) {
            switch(module) {
                case "passcont" : try{ passcont.main(); } catch (Exception e) {}; break;
                
                default: log.__log__(log.loss_require_module, module);
            }
        } else log.__log__(log.uninit_packages, "util");
    }
    
}
