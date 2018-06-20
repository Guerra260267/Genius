package Genius;

import java.util.Random;

public class Sequencer {

    private static Sequencer instance = null;
    private static final int SIZE = 30;
    private int numberOfButtons = NUM_BUTTONS; //number of buttons to draw
    private int gameTurn = 0; //first turn is always zero
    private String commputerSequence[] = new String[SIZE];
    private String userSequence[] = new String[SIZE];
    private Random randomizer = new Random();


    private static final int INVALID_SEQUENCE = -1;
    private static final int INVALID_COLOR = -2;
    private static final int NUM_BUTTONS = 4;

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int YELLOW = 2;
    private static final int BLUE = 3;

    //this method returns a sequence
    public static Sequencer getInstance(int numberOfButtons){
        if(instance == null){
            instance = new Sequencer(numberOfButtons);
            return instance;
        }

        return instance;
    }
    public int getSize(){
        return this.SIZE;
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public boolean checkUserMove(String buttonColor){
        addUserMove(buttonColor);
        return compareSequence();
    }

    public int getButtonOfSequence(int buttonIndex){
        return buttonColorToIndex(this.commputerSequence[buttonIndex]);
    }


    private Sequencer(int numberOfButtons){
        this.numberOfButtons = numberOfButtons;
        this.gameTurn = 0;
        generateSequence();
        eraseUserSequence();
    }

    private void generateSequence(){
        for(int i=0; i < this.SIZE; i++){
            String newButton = randomToButton(this.randomizer.nextInt(this.numberOfButtons));

            this.commputerSequence[i] = newButton;
        }
    }

    private String randomToButton(int random){
        switch(random){
            case RED:
                return("red");
            case GREEN:
                return("green");
            case YELLOW:
                return("yellow");
            case BLUE:
                return("blue");
        }
        return null;
    }



    private void eraseUserSequence(){
        for(int i = 0; i<SIZE; i++)
            this.userSequence[i] = "";
    }

    private boolean compareSequence(){
        int lastIndex = UserSequenceLastIndex() - 1;
        if(this.commputerSequence[lastIndex].equals(this.userSequence[lastIndex])){
            if(this.gameTurn == lastIndex){
                this.gameTurn++; //next turn
                eraseUserSequence(); //reestart user sequence
            }
            return(true);
        }
        return false;
    }

    public int UserSequenceLastIndex(){
        for(int index = 0; index < SIZE; index++)
            if(this.userSequence[index] == "")
                return index;
        return INVALID_SEQUENCE;
    }

    private void addUserMove(String buttonColor){
        int lastIndex = UserSequenceLastIndex();
        this.userSequence[lastIndex] = buttonColor;
    }


    private int buttonColorToIndex(String buttonColor){
        if(buttonColor.equals("red"))
            return(RED);
        if(buttonColor.equals("green"))
            return(GREEN);
        if(buttonColor.equals("yellow"))
            return(YELLOW);
        if(buttonColor.equals("blue"))
            return(BLUE);

        return INVALID_COLOR;
    }





}




