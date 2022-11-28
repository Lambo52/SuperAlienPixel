package Buttons;

import main.gamePanel;

import java.awt.*;

public class menuButton {
    gamePanel gp;
    int x,y;
    int width, height;
    Rectangle Bounds;
    boolean mousePressed = false;
    boolean buttonPressed = false;
    boolean Hover = false;
    public menuButton(gamePanel gp, int y, int width) {
        this.gp = gp;
        this.y = y;
        this.width = width;
        height = gp.getTileSize() / 2;
        this.x = (int)(gp.getScreenWidth() / 2 - width / 2);
        initBounds();
    }

    private void initBounds() {
        Bounds = new Rectangle(x,y,width,height);
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.drawRect(x,y,width,height);
    }

    public Rectangle getBounds() {
        return Bounds;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    public void setHover(boolean hover) {
        Hover = hover;
    }
    public boolean getHover() {
        return Hover;
    }
}
