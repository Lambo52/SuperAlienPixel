package Entity;

import main.gamePanel;
import main.tilesOptimizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullets extends Entity{ //è un po' staccata dagli altri oggetti statici ma non sapevo come trattarla

    String name;
    BufferedImage image;
    int index;

    public Bullets(gamePanel gp) {

        super(gp);

        name = "bullets";

        this.gp = gp;
        this.worldX = gp.getPlayer().getWorldX();
        this.worldY = gp.getPlayer().getWorldY();
        speed = 10;
        if(gp.getPlayer().getDirection().equals(Entity.right)) {
            velX = speed;
            direction = Entity.right;
        }
        else {
            velX = -speed;
            direction = Entity.left;
        }
        solidArea.x = 0;
        solidArea.y = gp.getTileSize() / 4;
        solidArea.width = gp.getTileSize();
        solidArea.height = gp.getTileSize() / 2;

        bulletImageLoader();
    }

    public void bulletImageLoader() {

        tilesOptimizer bulletsOptimizer = new tilesOptimizer();

        try {
            if(velX > 0) {
                image = ImageIO.read(getClass().getResourceAsStream("/Bullets/bulletRight1.png"));
            }
            else {
                image = ImageIO.read(getClass().getResourceAsStream("/Bullets/bulletLeft1.png"));
            }
            image = bulletsOptimizer.scaleImage(image, gp.getTileSize(), gp.getTileSize());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        worldX += velX;

        if(direction == Entity.right) {
            collisionRight = false;
            index = gp.getCollision().checkEntity(this, gp.getMonsters()); //index non serve dato che se lo colpisco la
            if(index != 999) {
                gp.getMonsters()[index].setAlive(false);
            }
            gp.getCollision().checkRight(this);
            if(collisionRight) {
                alive = false;
            }
        }
        else {
            collisionLeft = false;
            gp.getCollision().checkLeft(this);
            index = gp.getCollision().checkEntity(this, gp.getMonsters());
            if(index != 999) {
                gp.getMonsters()[index].setAlive(false);
            }
            if(collisionLeft) {
                alive = false;
            }
        }
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

            g2.drawImage(image, x, y, null);
        }
        else if(screenX > worldX || screenY > worldY || rightOffset > gp.getWorldWidth() - worldX || bottomOffset > gp.getWorldHeight() - worldY) {
            g2.drawImage(image, x, y, null);
        }
    }

    public int getWorldX() {
        return worldX;
    }
    public int getWorldY() {
        return worldY;
    }
    public BufferedImage getImage() {
        return image;
    }
}
