package Objects;

import main.gamePanel;
import main.tilesOptimizer;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class superObject { //servirà per quando aggiungerò le monete mi raccomando non cancellarlo a caso
    // solo perché sembra inutile per fare le entity
    public gamePanel gp;
    public BufferedImage image;
    public boolean collision = false;
    public String name;
    public int worldX, worldY;
    public int screenX, screenY;
    public Rectangle solidArea;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public tilesOptimizer tileScaled;
    public static String coin = "coin";
    public static String door = "door";


    public superObject(gamePanel gp) {
        this.gp = gp;
        tileScaled = new tilesOptimizer();
        solidArea = new Rectangle(0,0,gp.getTileSize(),gp.getTileSize());
    }

    public void update() {
        //vuoto va implementato nelle sottoclassi a cui serve
    }

    public void draw(Graphics2D g2) {

        screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX(); //screenX è la posizione nello
        // schermo
        screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        int x = screenX;
        int y = screenY;
        //SETTIAMO L'EDGE
        if(screenX > worldX) {
            x = worldX;
        }
        if(screenY > worldY) {
            y = worldY;
        }
        int rightOffset = gp.getScreenWidth() - screenX;
        if(rightOffset > gp.getWorldWidth() - worldX) {
            x = gp.getScreenWidth() - (gp.getWorldWidth() - worldX);
        }
        int bottomOffset = gp.getScreenHeight() - screenY;
        if(bottomOffset > gp.getWorldHeight() - worldY) {
            y = gp.getScreenHeight() - (gp.getWorldHeight() - worldY);
        }


        if(worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
            g2.drawImage(image, x, y, null); //disegnamo
            // l'immagine corrispondente
        }
        else if(screenX > worldX || screenY > worldY || rightOffset > gp.getWorldWidth() - worldX || bottomOffset > gp.getWorldHeight() - worldY) {
            g2.drawImage(image, x, y, null);
        }
    }

    public boolean isCollision() {
        return collision;
    }
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    public int getWorldX() {
        return worldX;
    }
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }
    public int getWorldY() {
        return worldY;
    }
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BufferedImage getImage() {
        return image;
    }
}