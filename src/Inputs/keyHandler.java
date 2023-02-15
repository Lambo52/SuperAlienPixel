package Inputs;

import main.gamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener { //in questa classe mettiamo true o false i vari tasti

    boolean leftPressed = false, rightPressed = false, spacePressed = false, fPressed = false;
    gamePanel gp;

    public keyHandler(gamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(gp.getGameState() == gp.getTitleScreen()) { //SIAMO NEL TITOLO

            gp.getTitle().keyPressed(e);

        } //FINE TITOLO
        //DATASTATE
        else if(gp.getGameState() == gp.getDataState()) {

            gp.getData().keyPressed(e);

            /*menu(code, 2);

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
            }*/
        }//FINE DATASTATE

        //SCHERMATA FINE DEL LIVELLO
        else if(gp.getGameState() == gp.getFinishState()) {

            gp.getFinish().keyPressed(e);

        }

        //SCHERMATA PAUSA
        else if(gp.getGameState() == gp.getPauseState()) { //PAUSA

            gp.getPause().keyPressed(e);

        } //FINE PAUSA

        //GIOCO
        else if(gp.getGameState() == gp.getPlayState()) {
            if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            else if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            else if (code == KeyEvent.VK_F) {
                fPressed = true;
            }
            else if(code == KeyEvent.VK_ESCAPE) {
                gp.setGameState(gp.getPauseState());
            }
        }

        //SCHERMATA MORTE
        else if (gp.getGameState() == gp.getDeathState()) {

            gp.getDeath().keyPressed(e);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        else if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        else if (code == KeyEvent.VK_F) {
            fPressed = false;
        }
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
    }
    public boolean isSpacePressed() {
        return spacePressed;
    }
    public boolean isfPressed() {
        return fPressed;
    }
}