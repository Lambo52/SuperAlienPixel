package Screens;

import Buttons.menuButton;
import main.gamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class titleScreen extends Screens{

    String Title = "SUPER ALIEN PIXEL";
    String newGame1; //preso dal database
    String newGame2;
    String newGame3;
    String exitGame = "EXIT";
    //menuButton[] buttons = new menuButton[4];
    public titleScreen(gamePanel gp) {
        super(gp);
        buttons = new ArrayList<>();
        loadButtons();
    }

    private void loadButtons() {
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int) (gp.getTileSize() * 3.5),(int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp, gp.getScreenHeight() - (int) (gp.getTileSize() * 3),(int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int) (gp.getTileSize() * 2.5),(int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int) (gp.getTileSize() * 2),(int) (gp.getTileSize() * 2.5)));
    }

    public void updateNames() {
        newGame1 = "1) " + gp.getDb().loadDataBaseName("user1");
        newGame2 = "2) " + gp.getDb().loadDataBaseName("user2");
        newGame3 = "3) " + gp.getDb().loadDataBaseName("user3");
    }
    public void update() {
        //NIENTE ANCHE SE BISOGNEREBBE AGGIORNARE LA POSIZIONE DEI BOTTONI MA VABE
    }

    public void draw(Graphics2D g2) { //UI

        super.draw(g2);

        //g2.setColor(Color.black);
        gp.getBg().drawSingleBg(g2);
        //g2.fillRect(0,0,gp.getScreenWidth(),gp.getScreenHeight()); //metto tutto nero ogni volta così se torno al
        // menu di
        // inizio torna tutto nero

        g2.setFont(Font_50);
        length = getLength(Title, g2);

        g2.setColor(new Color(191, 64, 191));
        g2.drawString(Title, gp.getScreenWidth() / 2 - length / 2 + 3, gp.getScreenHeight() / 2 + 3 - gp.getTileSize());

        g2.setColor(Color.white);
        g2.drawString(Title, gp.getScreenWidth() / 2 - length / 2, gp.getScreenHeight() / 2 - gp.getTileSize());

        //BOTTONISTICI
        if(buttons.get(0).isButtonPressed()) {
            gp.setUser("user1");
            gp.loadMapByUser();
            gp.setGameState(gp.getDataState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(1).isButtonPressed()) {
            gp.setUser("user2");
            gp.loadMapByUser();
            gp.setGameState(gp.getDataState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(2).isButtonPressed()) {
            gp.setUser("user3");
            gp.loadMapByUser();
            gp.setGameState(gp.getDataState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(3).isButtonPressed()) {
            gp.getDb().closeDataBase();
            System.exit(0);

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
        length = getLength(newGame1, g2);
        g2.drawString(newGame1, gp.getScreenWidth() / 2 - length / 2 ,
                gp.getScreenHeight() - (int) (gp.getTileSize() * 3));

        if(gp.getCommandNum() == 1 || buttons.get(1).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(1);
        }
        else {
            g2.setColor(Color.white);
        }
        g2.setFont(Font_30);
        length = getLength(newGame2, g2);
        g2.drawString(newGame2, gp.getScreenWidth() / 2 - length / 2 ,
                gp.getScreenHeight() - (int) (gp.getTileSize() * 2.5));

        if(gp.getCommandNum() == 2 || buttons.get(2).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(2);
        }
        else {
            g2.setColor(Color.white);
        }
        g2.setFont(Font_30);
        length = getLength(newGame3, g2);
        g2.drawString(newGame3, gp.getScreenWidth() / 2 - length / 2 ,
                (int) (gp.getScreenHeight() - gp.getTileSize() * 2));

        if(gp.getCommandNum() == 3 || buttons.get(3).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(3);
        }
        else {
            g2.setColor(Color.WHITE);
        }
        //FONT GIà IMPOSTATO
        length = getLength(exitGame, g2);

        g2.drawString(exitGame, gp.getScreenWidth() / 2 - length / 2 ,
                gp.getScreenHeight() - (int)(gp.getTileSize() * 1.5));

        /*for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(g2);
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        menu(code, 3);

        if(code == KeyEvent.VK_ENTER) {
            if(gp.getCommandNum() == 0 ) {
                gp.setUser("user1");
                gp.loadMapByUser();
                gp.setGameState(gp.getDataState());
            }
            else if(gp.getCommandNum() == 1 ) {
                gp.setUser("user2");
                gp.loadMapByUser();
                gp.setGameState(gp.getDataState());
            }
            else if(gp.getCommandNum() == 2 ) {
                gp.setUser("user3");
                gp.loadMapByUser();
                gp.setGameState(gp.getDataState());
            }
            else if(gp.getCommandNum() == 3) {
                gp.getDb().closeDataBase();
                System.exit(0);
            }
            gp.setCommandNum(0);
            resetButtons(buttons);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }

    public String getNewGame1() {
        return newGame1;
    }
    public void setNewGame1(String newGame1) {
        this.newGame1 = newGame1;
    }
    public String getNewGame2() {
        return newGame2;
    }
    public void setNewGame2(String newGame2) {
        this.newGame2 = newGame2;
    }
    public String getNewGame3() {
        return newGame3;
    }
    public void setNewGame3(String newGame3) {
        this.newGame3 = newGame3;
    }
}