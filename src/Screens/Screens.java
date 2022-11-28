package Screens;

import Buttons.menuButton;
import main.gamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class Screens {

    gamePanel gp;
    Font originalFont;
    Font Font_30;
    Font Font_40;
    Font Font_50;
    int length;
    int commandNum = 0;
    public BasicStroke Border = new BasicStroke(5);
    public int mouseX, mouseY;
    ArrayList<menuButton> buttons;

    public Screens(gamePanel gp) {
        this.gp = gp;
        InputStream is = getClass().getResourceAsStream("/Font/Inter-ExtraBold.ttf");
        try {
            originalFont = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (FontFormatException e) { // ne servono 2 se no non va
            e.printStackTrace();
        }

        Font_30 = originalFont.deriveFont(Font.PLAIN,30);
        Font_40 = originalFont.deriveFont(Font.PLAIN,40);
        Font_50 = originalFont.deriveFont(Font.PLAIN,50);
    }

    public int getLength(String message, Graphics2D g2) {
        return (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth();
    }

    public void optimizeMouse(MouseEvent e) {
        mouseX = e.getX() * gp.getScreenWidth() / gp.getScreenWidthTrue();
        mouseY = e.getY() * gp.getScreenHeight() / gp.getScreenHeightTrue();
    }
    public boolean isIn(MouseEvent e, menuButton mb) {
        optimizeMouse(e);
        return mb.getBounds().contains(mouseX,mouseY);
    }
    public void resetButtons(ArrayList<menuButton> buttons) {
        for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setMousePressed(false);
            buttons.get(i).setButtonPressed(false);
            buttons.get(i).setHover(false);
        }

    }

    public void update() {

    }

    public void draw(Graphics2D g2) {

        //g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        g2.setColor(Color.white);
        g2.setStroke(Border);
        g2.drawRect(0,0,gp.getScreenWidth(),gp.getScreenHeight());
    }

    public void menu(int code, int Max) {
        if(code == KeyEvent.VK_UP) {
            if(gp.getCommandNum() == 0) {
                gp.setCommandNum(Max);
            }
            else {
                commandNum = gp.getCommandNum();
                commandNum -= 1;
                gp.setCommandNum(commandNum);
            }
        }

        if(code == KeyEvent.VK_DOWN) {
            if(gp.getCommandNum() == Max) {
                gp.setCommandNum(0);
            }
            else {
                commandNum = gp.getCommandNum();
                commandNum += 1;
                gp.setCommandNum(commandNum);
            }
        }
    }
    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i < buttons.size(); i++) {
            if(isIn(e,buttons.get(i))) {
                buttons.get(i).setMousePressed(true);
                break;
            }
        }
    }
    public void mouseReleased(MouseEvent e) {
        for(int i = 0; i < buttons.size(); i++) {
            if(isIn(e,buttons.get(i))) {
                if(buttons.get(i).isMousePressed()) {
                    buttons.get(i).setButtonPressed(true);
                    break;
                }
            }
        }
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
        for(int i = 0; i < buttons.size(); i++) {
            if(isIn(e,buttons.get(i))) {
                buttons.get(i).setHover(true);
            }
            else buttons.get(i).setHover(false);
        }
    }

}
