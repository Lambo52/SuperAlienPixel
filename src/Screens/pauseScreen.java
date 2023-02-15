package Screens;

import Buttons.menuButton;
import main.gamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class pauseScreen extends Screens{

    String Pausa = "GAME PAUSED";
    String Riprendi = "CONTINUE";
    String reset = "TRY AGAIN";
    String Indietro = "BACK";

    public pauseScreen(gamePanel gp) {
        super(gp);
        buttons = new ArrayList<>();
        loadButton();
    }
    public void loadButton() {
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int)(gp.getTileSize() * 3),(int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int)(gp.getTileSize() * 2.5),(int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int)(gp.getTileSize() * 2),(int) (gp.getTileSize() * 2.5)));
    }
    public void update() {

    }

    public void draw(Graphics2D g2) {
        super.draw(g2);

        g2.setFont(Font_40);
        length = getLength(Pausa, g2);

        /*
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(Pausa, gp.getScreenWidth() / 2 - length / 2 + 5, gp.getScreenHeight() / 2 + 5 - gp.getTileSize());
        */

        g2.setColor(Color.white);
        g2.drawString(Pausa, gp.getScreenWidth() / 2 - length / 2, gp.getScreenHeight() / 2 - gp.getTileSize());


        //BOTTONI

        if(buttons.get(0).isButtonPressed()) {
            gp.setGameState(gp.getPlayState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(1).isButtonPressed()) {
            gp.reSetUpGame();
            gp.setGameState(gp.getPlayState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(2).isButtonPressed()) {
            gp.setGameState(gp.getDataState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }

        //FINEBOTTONI


        if(gp.getCommandNum() == 0 || buttons.get(0).getHover() == true) {
            gp.setCommandNum(0);
            g2.setColor(Color.GREEN);
        }
        else {
            g2.setColor(Color.white);
        }
        g2.setFont(Font_30);
        length = getLength(Riprendi, g2);
        g2.drawString(Riprendi, gp.getScreenWidth() / 2 - length / 2,
                gp.getScreenHeight() - (int) (gp.getTileSize() * 2.5));

        if(gp.getCommandNum() == 1 || buttons.get(1).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(1);
        }
        else {
            g2.setColor(Color.white);
        }
        g2.setFont(Font_30);
        length = getLength(reset, g2);
        g2.drawString(reset, gp.getScreenWidth() / 2 - length / 2, gp.getScreenHeight() - (int) (gp.getTileSize() * 2));

        if(gp.getCommandNum() == 2 || buttons.get(2).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(2);
        }
        else {
            g2.setColor(Color.white);
        }
        //font già messo
        length = getLength(Indietro, g2);
        g2.drawString(Indietro, gp.getScreenWidth() / 2 - length / 2, gp.getScreenHeight() - (int)(gp.getTileSize() * 1.5));

        /*for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(g2);
        }*/

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        menu(code,2);

        if(code == KeyEvent.VK_ENTER) {
            if(gp.getCommandNum() == 0) {
                gp.setGameState(gp.getPlayState());
            }
            else if(gp.getCommandNum() == 1) {
                gp.reSetUpGame();
                gp.setGameState(gp.getPlayState());
            }
            else if(gp.getCommandNum() == 2) {
                gp.setGameState(gp.getDataState());
            }
            gp.setCommandNum(0);
        }
        else if(code == KeyEvent.VK_ESCAPE) {
            gp.setGameState(gp.getPlayState());
            gp.setCommandNum(0);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
