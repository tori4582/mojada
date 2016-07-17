package var;

public class TermVar {
    public  String OS      = System.getProperty("os.name");
    public  String USER    = System.getProperty("user.name");
    public  String DESKTOP = System.getProperty("sun.desktop");
    public  String ARCH    = System.getProperty("os.arch");
    
    public  static final  char TERM_CHAR = '>';
    public  final  String HOME           = System.getProperty("user.home");
}
