package ciscotkm;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            if(args.length!=2){
                System.out.println("ERROR\nYou must specify 2 arguments - IP & Port");
                return;
            }
        }catch(Exception ex){
            System.out.println("args error");
            return;
        }
        SnmpHandler snmpManager = new SnmpHandler("udp:"+args[0]+"/"+args[1]); //127.0.0.1/175
        snmpManager.start();     
    }
}