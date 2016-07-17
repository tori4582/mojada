package var;

import bin.BinPort;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import net.NetPort;
import sys.SysPort;
import utils.UtilsPort;

public class FunctionName {
    
    Field[]  clbin, cllib, clnet, clutils;
    Method[] mbin , mlib , mnet,  mutils;
    
    SysPort   sysPort = new SysPort();
    BinPort   binPort = new BinPort();
    NetPort   netPort = new NetPort();
    
    public FunctionName() {
        initContainingFields();
        System.out.println(Arrays.toString(loadMethods(clbin)));
    }
    
    private void initContainingFields() {
        clbin   = BinPort.class.getFields();
        clutils = UtilsPort.class.getFields();
        clnet   = NetPort.class.getFields();
    }
    
    private Method[] loadMethods(Field[] fields) {
        Method[][] methods = new Method[500][500];
        Method[] t = new Method[512];
        int count = 0;
        for (int i =0 ; i< fields.length; i++){
            methods[count] = fields[i].getClass().getMethods();
            count+=1;
        }
        for (int i = 0; i< methods.length;i++) {
            for (int j = 0; j< methods[i].length; i++) {
                t[i+j] = methods[i][j];
            }
        }
        return t;
    }
   
    
    public static void main(String[] args) throws Exception {
        FunctionName f = new FunctionName();
        
    }
    
}
