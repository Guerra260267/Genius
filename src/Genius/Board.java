package Genius;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Board extends JFrame{

    private Container gameWindow = getContentPane();

    private Button blueButton = new Button("blue", "c");
    private Button redButton = new Button("red", "d");
    private Button yellowButton = new Button("yellow", "e");
    private Button greenButton = new Button("green", "f");
    private boolean gameOver = false;
    private Sequencer sequence = Sequencer.getInstance(NUM_BUTTONS);
    private boolean gameIsRunning = true;

    private static final int NUM_BUTTONS = 4;
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int YELLOW = 2;
    private static final int BLUE = 3;
    private static final int WAITBUTTON = 500;


    public Board(){
        super("Genius");
        setLayout(null);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void activeGameWindow(){

        gameWindow.setBackground(Color.black);

        gameWindow.add(greenButton);
        redButton.setBounds(230, 130, 110, 110);

        gameWindow.add(yellowButton);
        yellowButton.setBounds(230, 250, 110, 110);

        gameWindow.add(redButton);
        greenButton.setBounds(350, 130, 110, 110);

        gameWindow.add(blueButton);
        blueButton.setBounds(350, 250, 110, 110);

        action();

        redButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if ((redButton.isEnabled()) && gameIsRunning){
                            disableAllButtons();
                            redButton.blinkButton(redButton);
                            gameIsRunning = sequence.checkUserMove(redButton.getColor());
                            action();
                        }
                    }
                });

        greenButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if ((greenButton.isEnabled()) && gameIsRunning) {
                            disableAllButtons();
                            greenButton.blinkButton(greenButton);
                            gameIsRunning = sequence.checkUserMove(greenButton.getColor());
                            action();
                        }
                    }
                });
                            /*
                            new Thread(new Runnable(){
                                public void run(){
                                    try{
                                        Thread.sleep(WAITBUTTON);
                                    } catch (Exception e){}
                                    EventQueue.invokeLater(new Runnable(){
                                        public void run(){
                                            action();
                                        }
                                    });
                                }
                            }).start();
                        }
                    }
                }
        );
*/
        yellowButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if ((yellowButton.isEnabled()) && gameIsRunning){
                            disableAllButtons();
                            yellowButton.blinkButton(yellowButton);
                            gameIsRunning = sequence.checkUserMove(yellowButton.getColor());
                            action();
                        }
                    }
                });


        blueButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if ((blueButton.isEnabled()) && gameIsRunning){
                            disableAllButtons();
                            blueButton.blinkButton(blueButton);
                            gameIsRunning = sequence.checkUserMove(blueButton.getColor());
                            action();
                        }
                    }
                });



    }





    public void showSequence(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int buttonIndex = 0; buttonIndex <= sequence.getGameTurn(); buttonIndex++) {
                        switch (sequence.getButtonOfSequence(buttonIndex)) {
                            case RED:
                                Thread.sleep(WAITBUTTON);
                                disableAllButtons();
                                redButton.blinkButton(redButton);
                                Thread.sleep(WAITBUTTON);
                                break;
                            case GREEN:
                                Thread.sleep(WAITBUTTON);
                                disableAllButtons();
                                greenButton.blinkButton(greenButton);
                                Thread.sleep(WAITBUTTON);
                                break;
                            case YELLOW:
                                Thread.sleep(WAITBUTTON);
                                disableAllButtons();
                                yellowButton.blinkButton(yellowButton);
                                Thread.sleep(WAITBUTTON);
                                break;
                            case BLUE:
                                Thread.sleep(WAITBUTTON);
                                disableAllButtons();
                                blueButton.blinkButton(blueButton);
                                Thread.sleep(WAITBUTTON);
                                break;

                        }
                    }
                    enableAllButtons();
                } catch (Exception exception) {
                }
            }
        }).start();
    }

    public void disableAllButtons(){
        blueButton.disableButton();
        redButton.disableButton();
        greenButton.disableButton();
        yellowButton.disableButton();
    }

    public void enableAllButtons(){
        blueButton.enableButton();
        redButton.enableButton();
        greenButton.enableButton();
        yellowButton.enableButton();
    }



    public void action(){
        if(gameOver){
            //verificar pontuacao do jogador e comparar com ultima posicao do ranking
            //caso sim, pedir nome, aguardar nome, adicionar no ranking, retornar ao menu inicial
            disableAllButtons();
            gameIsRunning = false;
        }
        if(sequence.getGameTurn() == sequence.getSize()){
            gameIsRunning = false;
            disableAllButtons();
        }
        if(gameIsRunning){
            if(sequence.UserSequenceLastIndex() == 0)
                showSequence();
            enableAllButtons();
        }

    }

}
