package Screens;

import Buttons.menuButton;
import main.gamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class deathScreen extends Screens {

    String textDeath = "BUSTED";
    String exitGame = "BACK";
    String newGame = "TRY AGAIN";

    public deathScreen(gamePanel gp) {
        super(gp);
        buttons = new ArrayList<>();
        loadButtons();
    }

    public void loadButtons() {
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int) (gp.getTileSize() * 2.5), (int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int) (gp.getTileSize() * 2), (int) (gp.getTileSize() * 2.5)));
    }
    public void draw(Graphics2D g2) { //UI

        //g2.setColor(Color.black);

        //g2.fillRect(0,0,gp.screenWidth,gp.screenHeight); //metto tutto nero ogni volta così se torno al menu di inizio
        // torna tutto nero

        super.draw(g2);

        g2.setFont(Font_50);
        length = getLength(textDeath, g2);
        /*
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(textDeath, gp.getScreenWidth() / 2 - length / 2 + 5, gp.getScreenHeight() / 2 + 5 - gp.getTileSize());
        */
        g2.setColor(Color.white);
        g2.drawString(textDeath, gp.getScreenWidth() / 2 - length / 2, gp.getScreenHeight() / 2 - gp.getTileSize());

        //BOTTONI
        if(buttons.get(0).isButtonPressed()) {
            gp.reSetUpGame();
            gp.setGameState(gp.getPlayState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(1).isButtonPressed()) {
            gp.setGameState(gp.getDataState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }

        //FINEBOTTONI



        //MENU
        if(gp.getCommandNum() == 0 || buttons.get(0).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(0);
        }
        else {
            g2.setColor(Color.white);
        }
        g2.setFont(Font_30);
        length = getLength(newGame, g2);
        g2.drawString(newGame, gp.getScreenWidth() / 2 - length / 2 , gp.getScreenHeight() - gp.getTileSize() * 2);

        if(gp.getCommandNum() == 1 || buttons.get(1).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(1);
        }
        else {
            g2.setColor(Color.white);
        }
        //già impostato
        length = getLength(exitGame, g2);
        g2.drawString(exitGame, gp.getScreenWidth() / 2 - length / 2 , gp.getScreenHeight() - (int)(gp.getTileSize() * 1.5));

        //g2.drawImage(waltersus,100,100,200,200,null);
        /*for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(g2);
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        menu(code, 1);

        if(code == KeyEvent.VK_ENTER) {
            if(gp.getCommandNum() == 0) {
                gp.reSetUpGame();
                gp.setGameState(gp.getPlayState());
            }
            else if(gp.getCommandNum() == 1) {
                gp.setGameState(gp.getDataState());
            }
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