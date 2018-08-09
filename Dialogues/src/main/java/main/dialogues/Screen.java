package main.dialogues;


public class Screen {
    Menu menu;
    Interlocutor interlocutor;
    
    public Screen(){}
    
    public Screen(Menu menu,Interlocutor interlocutor){
        this.menu = menu;
        this.interlocutor = interlocutor;
    }
    
    public void printScreen(){
        interlocutor.printInterlocutor();
        menu.printMenu();
    }
    
    public void mainScreen(){
        interlocutor.printVoid();
        menu.printMenu();
    }
}
