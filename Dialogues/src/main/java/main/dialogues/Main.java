package main.dialogues;


public class Main {
    public static void main(String args[]){
        Menu menu = new Menu("Ого, привет!","Привет", "Стоит РАЗОБРАТЬСЯ", "Найти ВЫХОД?", "");
        Interlocutor interlocutor = new Interlocutor();
        Screen screen = new Screen(menu, interlocutor);
        screen.mainScreen();
    }
}
