package Screens;

import Buttons.menuButton;
import main.gamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class dataScreen extends Screens{

    String title;
    String start = "START";
    String exit = "BACK";
    String reset = "RESET SAVE";

    public dataScreen(gamePanel gp) {
        super(gp);
        buttons = new ArrayList<>();
        loadButtons();
    }
    public void loadButtons() {
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int) (gp.getTileSize() * 3),(int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int) (gp.getTileSize() * 2.5),(int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int) (gp.getTileSize() * 2),
                (int) (gp.getTileSize() * 3)));
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.black);
        gp.getBg().drawSingleBg(g2);
        //g2.fillRect(0,0,gp.getScreenWidth(),gp.getScreenHeight()); //metto tutto nero ogni volta così se torno al
        // menu di
        // inizio torna tutto nero
        super.draw(g2);
        if(gp.getUser().equals("user1")) {
            title = "USER 1, LEVEL " + gp.getLevel();
        }
        else if(gp.getUser().equals("user2")) {
            title = "USER 2, LEVEL " + gp.getLevel();
        }
        else if(gp.getUser().equals("user3")) {
            title = "USER 3, LEVEL " + gp.getLevel();
        }

        g2.setFont(Font_40);
        length = getLength(title, g2);
        g2.setColor(Color.white);
        g2.drawString(title, gp.getScreenWidth() / 2 - length / 2, gp.getScreenHeight() / 2 - gp.getTileSize());


        //BOTTONI
        if(buttons.get(0).isButtonPressed()) {
            gp.reSetUpGame();
            gp.setGameState(gp.getPlayState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(1).isButtonPressed()) {
            gp.setGameState(gp.getTitleScreen());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(2).isButtonPressed()) {//reset del salvataggio
            gp.getDb().storeDataBase(1,"LEVEL 1",gp.getUser()); //facendo così si resetta il salvataggio del
            // livello dato che prima carico nel database 1 poi facendo loadmapbyuser il livello in gamepanel
            // si aggiorna in automatico nel metodo
            gp.getTitle().updateNames();
            gp.loadMapByUser();

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        //FINE BOTTONI



        //MENU
        if(gp.getCommandNum() == 0 || buttons.get(0).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(0);
        }
        else {
            g2.setColor(Color.white);
        }
        g2.setFont(Font_30);
        length = getLength(start, g2);
        g2.drawString(start, gp.getScreenWidth() / 2 - length / 2 ,gp.getScreenHeight() - (int) (gp.getTileSize() * 2.5));

        if(gp.getCommandNum() == 1 || buttons.get(1).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(1);
        }
        else {
            g2.setColor(Color.white);
        }
        //FONT GIà IMPOSTATO
        length = getLength(exit, g2);

        g2.drawString(exit, gp.getScreenWidth() / 2 - length / 2 , gp.getScreenHeight() - (int)(gp.getTileSize() * 2));

        if(gp.getCommandNum() == 2 || buttons.get(2).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(2);
        }
        else {
            g2.setColor(Color.white);
        }
        //FONT GIà IMPOSTATO
        length = getLength(reset, g2);

        g2.drawString(reset, gp.getScreenWidth() / 2 - length / 2 , gp.getScreenHeight() - (int)(gp.getTileSize() * 1.5));

        /*for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(g2);
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        menu(code, 2);
        if(code == KeyEvent.VK_ENTER) {
            if(gp.getCommandNum() == 0) {
                gp.reSetUpGame();
                gp.setGameState(gp.getPlayState());
            }
            else if(gp.getCommandNum() == 1) {
                gp.setGameState(gp.getTitleScreen());
            }
            else if(gp.getCommandNum() == 2) { //resetta il salvataggio
                gp.getDb().storeDataBase(1,"LEVEL 1",gp.getUser()); //facendo così si resetta il salvataggio del
                // livello dato che prima carico nel database 1 poi facendo loadmapbyuser il livello in gamepanel
                // si aggiorna in automatico nel metodo
                gp.getTitle().updateNames();
                gp.loadMapByUser();
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
