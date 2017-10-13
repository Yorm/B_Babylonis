package rpgtest;

import enemies.*;
import events.NewRoomEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GamePlay extends NPC{
    
    Map NPCs; 
    
    public GamePlay(){
        this.NPCs = new HashMap<Integer,NPC>();
        this.mind = new ArrayList<>();
        mindAdd();
        npcsAdd();
    }
    
    public int start() throws InterruptedException{
        
        System.out.println(mind.get(0));
        //Теперь нужен рандомайзер
        //событие - обнаружение чего либо
        //оценка события
        //ожидаемый результат события, мнение о событии
        //результат
        for(int i=1; i<=60; i++){
           System.out.println(i);
           Thread.sleep(1000);
        }  
        
        return 0;
    } 
    private void npcsAdd(){
        NPCs.put(1, new Rat());
        NPCs.put(2, new Worm());
        NPCs.put(3, new NewRoomEvent());
        NPC r=(NPC) NPCs.get(3);
        r.p();
    }
    private void mindAdd(){
        this.mind.add("Вы вошли в подземелье - сырость и мрак окружили вас. Позади гулко грохнула дверь.");//0
    }
}
