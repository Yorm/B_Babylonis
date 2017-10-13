package events;

import rpgtest.Event;

/**
 *
 * @author walte
 */
public class NewRoomEvent extends Event{
    public NewRoomEvent(){
        this.give=false;
        addTrigger();
    }
    
    private void addTrigger(){
        this.trigger.add("Обнаружил какое то хорошо освещенное помещение...");//0
        this.trigger.add("На полу лежат кучи хлама и старых досок");//1
        this.trigger.add("Кажется здесь кто то до меня был");//2
    }
    public void p(){
        System.out.println(trigger.get(0)+"\n"+trigger.get(1)+"\n"+trigger.get(2)+"\n");
    }
}
