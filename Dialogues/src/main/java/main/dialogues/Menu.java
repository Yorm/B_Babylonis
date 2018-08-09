package main.dialogues;

import java.util.ArrayList;
import java.util.List;


public class Menu {
    List<String> filling;

    public Menu() {}
    
    public Menu(ArrayList filling) {
        this.filling = new ArrayList<>();
        this.filling.addAll(filling);
    }
    
    public Menu(String title, String p1, String p2, String p3, String p4) {
        filling = new ArrayList<>();
        filling.add(title);
        filling.add(p1);
        filling.add(p2);
        filling.add(p3);
        filling.add(p4);
    }
    
    public void printMenu(){
        System.out.println("|  >«"+filling.get(0)+"»");
        System.out.println("|                       ");
        System.out.println("|  >«"+filling.get(1)+"»");
        System.out.println("|  >«"+filling.get(2)+"»");
        System.out.println("|  >«"+filling.get(3)+"»");
        System.out.println("|  >«"+filling.get(4)+"»");
    }
}
