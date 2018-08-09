package main.dialogues;

import java.util.ArrayList;
import java.util.List;


public class Interlocutor {
    int count;
    int injured;
    int impressed;
    Boolean mood;
    List<String> filling;
    
    public Interlocutor() {}

    public Interlocutor(int count, int injured, int impressed,ArrayList <String> filling) {
        this.count = count;
        this.injured = injured;
        this.impressed = impressed;
        this.filling = filling;
        if(this.injured > this.impressed ){
            mood = false;       
        } else if(this.injured < this.impressed ){
            mood = true;       
        } else {
            mood = null;
        }
    }
    
    //TODO enum act? or something with id
    public Interlocutor(String name, String act, int count, int injured, int impressed) {
        this.count = count;
        this.injured = injured;
        this.impressed = impressed;
        filling = new ArrayList<>();
        filling.add(name);
        filling.add(act);
        if(this.injured > this.impressed ){
            mood = false;   
            filling.add("РАССЕРЖЕНЫ.");
        } else if(this.injured < this.impressed ){
            mood = true;       
            filling.add("слушают.");
        } else {
            mood = null;
            filling.add("выжидают.");
        }   
    }
    
    public void printInterlocutor(){
        System.out.println("...");
        System.out.println("   Ты "+filling.get(1)+" перед "+ filling.get(0));
        System.out.println("   )                    ");
        System.out.println("  (                     ");
        System.out.println("Ты ОЧАРОВАЛ "+impressed+" из "+count+""+filling.get(0)+".");
        System.out.println("Ты оскорбил "+injured+" из "+count+""+filling.get(0)+".");
        System.out.println("...");
        System.out.println(filling.get(0) + "" + filling.get(2));
    }
    
    public void printVoid(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
}
