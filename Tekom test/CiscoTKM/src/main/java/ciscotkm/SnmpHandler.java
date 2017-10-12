package ciscotkm;
/*
Требуется использовав сторонние библиотеки (к примеру snmp4j) 
написать java программу, которая принимая на вход IP адрес и порт 
будет выводить информацию о интерфейсах
IF-MIB(http://net-snmp.sourceforge.net/docs/mibs/interfaces.html)
 
Вывод в файл Interfaces.txt.
 
Формат:
 
Interface 1: Description=”значение” Type = “” MTU = “”Speed = “” и так все поля из IF-MIB 
(кроме Deprecated)
…..
Interface 37: …..
*/
import java.io.FileWriter;
import java.io.IOException;
import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.asn1.BERInputStream;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpHandler {

    Snmp snmp = null;
    String address = null;
    FileWriter writer; 
    
    public SnmpHandler(String address) throws IOException {
        this.address = address;
        writer = new FileWriter("interfaces.txt", false);
    }

    public void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
        fileBuild();
    }

    public String getAsString(OID oid) throws IOException {
        ResponseEvent event = get(new OID[] { oid });
        return event.getResponse().get(0).getVariable().toString();
    }


    private ResponseEvent get(OID oids[]) throws IOException {
        PDU pdu = new PDU();
        
        for (OID oid : oids) {
            pdu.add(new VariableBinding(oid));
        }
        
        pdu.setType(PDU.GET);
        ResponseEvent event = snmp.send(pdu, getTarget(), null);

        if(event != null) {
            return event;
        }
        throw new RuntimeException("GET timed out");
    }

    private Target getTarget() {
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(GenericAddress.parse(address));
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        
        return target;
    }
    
    public void fileBuild() throws IOException  {
        StringBuilder sb = new StringBuilder("\n");
        System.out.println("Creature interfaces.txt...");
        for(int i=1;i<=37;i++){
            if(i==31) i=37;
            //Depricated 12 18 21 22
            sb.append("+----------Interface "+  getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 1,i}))+"----------+\n");
            sb.append("Description=\""+         getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 2,i}))+"\"\n");
            sb.append("Type=\""+                getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 3,i}))+"\"\n");
            sb.append("MTU=\""+                 getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 4,i}))+"\"\n");
            sb.append("Speed=\""+               getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 5,i}))+"\"\n");
            sb.append("PhysAddress=\""+         getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 6,i}))+"\"\n");
            sb.append("AdminStatus=\""+         getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 7,i}))+"\"\n");
            sb.append("OperStatus=\""+          getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 8,i}))+"\"\n");
            sb.append("LastChange=\""+          getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 9,i}))+"\"\n");
            sb.append("InOctets=\""+            getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 10,i}))+"\"\n");
            sb.append("InUcastPkts=\""+         getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 11,i}))+"\"\n");
            sb.append("InDiscards=\""+          getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 13,i}))+"\"\n");
            sb.append("InErrors=\""+            getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 14,i}))+"\"\n");
            sb.append("InUnknownProtos=\""+     getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 15,i}))+"\"\n");
            sb.append("OutOctets=\""+           getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 16,i}))+"\"\n");
            sb.append("OutUcastPkts=\""+        getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 17,i}))+"\"\n");
            sb.append("OutDiscards=\""+         getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 19,i}))+"\"\n");
            sb.append("OutErrors=\""+           getAsString(new OID(new int[] {1,3,6,1,2,1,2,2,1, 20,i}))+"\"\n");
            sb.append("\n");         
        }
        writer.write(sb.toString());
        writer.flush();
        System.out.println("Sucsess!");
    }
}