package Inputs;

import main.gamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class mouseInputs implements MouseListener, MouseMotionListener {

    gamePanel gp;
    public mouseInputs(gamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(gp.getGameState() == gp.getTitleScreen()) {
            gp.getTitle().mousePressed(e);
        }
        else if(gp.getGameState() == gp.getPauseState()) {
            gp.getPause().mousePressed(e);
        }
        else if(gp.getGameState() == gp.getDataState()) {
            gp.getData().mousePressed(e);
        }
        else if(gp.getGameState() == gp.getDeathState()) {
            gp.getDeath().mousePressed(e);
        }
        else if(gp.getGameState() == gp.getFinishState()) {
            gp.getFinish().mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(gp.getGameState() == gp.getTitleScreen()) {
            gp.getTitle().mouseReleased(e);
        }
        else if(gp.getGameState() == gp.getPauseState()) {
            gp.getPause().mouseReleased(e);
        }
        else if(gp.getGameState() == gp.getDataState()) {
            gp.getData().mouseReleased(e);
        }
        else if(gp.getGameState() == gp.getDeathState()) {
            gp.getDeath().mouseReleased(e);
        }
        else if(gp.getGameState() == gp.getFinishState()) {
            gp.getFinish().mouseReleased(e);
        }
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

    @Override
    public void mouseMoved(MouseEvent e) {
        if(gp.getGameState() == gp.getTitleScreen()) {
            gp.getTitle().mouseMoved(e);
        }
        else if(gp.getGameState() == gp.getPauseState()) {
            gp.getPause().mouseMoved(e);
        }
        else if(gp.getGameState() == gp.getDataState()) {
            gp.getData().mouseMoved(e);
        }
        else if(gp.getGameState() == gp.getDeathState()) {
            gp.getDeath().mouseMoved(e);
        }
        else if(gp.getGameState() == gp.getFinishState()) {
            gp.getFinish().mouseMoved(e);
        }
    }
}
