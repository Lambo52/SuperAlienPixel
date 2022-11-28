package Backgrounds;

import main.gamePanel;
import main.tilesOptimizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {
    gamePanel gp;
    BufferedImage image;
    tilesOptimizer tileScaled;
    int screenX, screenY;
    int worldX, worldY;
    int bottomOffset, rightOffset;
    int x, y;
    int numX, numY;
    int var, var2;
    Color c;

    public Background(gamePanel gp) {
        this.gp = gp;
        tileScaled = new tilesOptimizer();
        worldX = 0;
        worldY = 0;
        numX = (int) Math.round((double) gp.getMaxWorldCol() / gp.getMaxScreenCol());
        numY = (int) Math.round((double) gp.getMaxWorldRow() / gp.getMaxScreenRow());
        c = new Color(62,97,242);
        loadBg();
    }

    public void loadBg() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Background/bg.png"));
            image = tileScaled.scaleImage(image, gp.getScreenWidth(), gp.getScreenHeight());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        worldX = 0;
        worldY = 0;

        x = reSetX();
        y = reSetY();


        /*for(int i = 0; i < numX; i++) {
            for(int j = 0; j < numY; j++) {
                x = reSetX();
                y = reSetY();

                if(worldX + gp.getScreenWidth() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                        worldX - gp.getScreenWidth() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                        worldY + gp.getScreenHeight() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                        worldY - gp.getScreenHeight() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {

                g2.drawImage(image, x, y, null);
                }
                else if(screenX > worldX || screenY > worldY || rightOffset > gp.getWorldWidth() - worldX || bottomOffset >
                        gp.getWorldHeight() - worldY) {
                    g2.drawImage(image, x, y, null);
                }

                worldY += gp.getScreenHeight();
            }
            worldY = 0;
            worldX += gp.getScreenWidth();
        }*/
        g2.setColor(c);
        g2.fillRect(0,0,gp.getScreenWidth(),gp.getScreenHeight());

    }

    public int reSetY() {
        screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        var = screenY;
        //SETTIAMO L'EDGE
        if(screenY > worldY) {
            var = worldY;
        }
        bottomOffset = gp.getScreenHeight() - screenY;
        if(bottomOffset > gp.getWorldHeight() - worldY) {
            var = gp.getScreenHeight() - (gp.getWorldHeight() - worldY);
        }

        return var;
    }

    public int reSetX() {
        screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX(); //screenX è la posizione nello
        // schermo
        //SETTIAMO L'EDGE
        var2 = screenX;
        if(screenX > worldX) {
            var2 = worldX;
        }

        rightOffset = gp.getScreenWidth() - screenX;
        if(rightOffset > gp.getWorldWidth() - worldX) {
            var2 = gp.getScreenWidth() - (gp.getWorldWidth() - worldX);
        }

        return var2;
    }

    public void drawSingleBg(Graphics2D g2) {
        g2.drawImage(image, 0, 0, null);
    }

}