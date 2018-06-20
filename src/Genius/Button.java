package Genius;

import javax.swing.*;
import org.jfugue.player.*;
import org.jfugue.pattern.*;

import java.awt.*;

public class Button extends JButton {

    private boolean enable;
    private String color;
    private String sound; //The sound is defined by a musical note {C, D, E, F, G, A, B}
    private static final int WAITBUTTON = 200;


    public Button (String color, String sound) {
        super(new ImageIcon("images/" + color + "_off.jpg"));
        setRolloverIcon(new ImageIcon("images/"+ color + "_on.jpg"));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        this.color = color;
        this.sound = sound;
    }

    public String getSound() {
        return this.sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void enableButton(){
        this.enable = true;
    }
    public void disableButton(){
        this.enable = false;
    }

    public boolean isEnabled(Button button) {
        return this.enable;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void turnButtonOn(){
        this.setIcon(new ImageIcon("images/"+this.getColor()+"_on.jpg"));
        this.setRolloverIcon(new ImageIcon("image/"+this.getColor()+"_off.jpg"));
    }

    public void turnButtonOff(){

        this.setIcon(new ImageIcon("images/" + this.getColor()+"_off.jpg"));
        this.setRolloverIcon(new ImageIcon("images/" + this.getColor()+ "_on.jpg"));
    }

    public void playSound()
    {
        Player player =  new Player();
        player.play(this.sound + "h");

    }



    public void blinkButton(Button button){
        button.turnButtonOn();
        button.playSound();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(WAITBUTTON);
                }catch(Exception exception){ }
                EventQueue.invokeLater(new Runnable(){
                    public void run(){
                        button.turnButtonOff();
                    }
                });
            }
        }).start();
    }

}


