package Entity;

import main.gamePanel;
import main.tilesOptimizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {

    gamePanel gp;
    BufferedImage image = null;
    public int worldX, worldY, screenX, screenY;
    public int speed;
    public BufferedImage left1, left2, right1, right2;
    public String direction;
    public static String left = "left";
    public static String right = "right";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea; //è il rettangolo delle collisioni
    public boolean collisionTop = false;
    public boolean collisionLeft = false;
    public boolean collisionRight = false;
    public boolean collisionBottom = false;
    public boolean falling  = true; // GRAVITà MOSCONI
    public boolean jumping = false; //salto MOSCONI
    public double gravitySpeed = 0.3; //gravità
    public final double MAX_GRAVITY_SPEED = 10;
    public double velY = 0;
    public double velX = 0;
    public int actionCounter = 0;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int Life;
    public int maxLife;
    public boolean alive;

    public Entity(gamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(gp.getTileSize() / 8, 0, gp.getTileSize() - gp.getTileSize() / 4 - 1,
                gp.getTileSize() - 10);
        alive = true;
    }

    public BufferedImage loadImage(String name) {

        tilesOptimizer playerScaled = new tilesOptimizer();

        try {
            image = ImageIO.read(getClass().getResourceAsStream(name + ".png"));
            image = playerScaled.scaleImage(image, gp.getTileSize(), gp.getTileSize());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public void gravityCheck() {
        //CONTROLLO GRAVITY
        collisionBottom = false;
        gp.getCollision().checkBottom(this);
        if (!collisionBottom) {
            velY += gravitySpeed;
            falling = true;
        }
        else {
            velY = 0;
            falling = false;
        }
        if (velY > MAX_GRAVITY_SPEED) {
            velY = MAX_GRAVITY_SPEED;
        }
        //FINE CONTROLLO GRAVITY
    }

    public void setAction() {
        //NIENTE LA SETTIAMO NELLE CLASSI FIGLIE
    }

    public void update() {
        worldX += velX;
        worldY += velY;
        //CHECK DELL'AZIONE DA SVOLGERE
        setAction();
        //CHECK DELLA GRAVITY
        gravityCheck();

        if(direction.equals(left)) {
            collisionLeft = false;
            gp.getCollision().checkLeft(this);
            gp.getCollision().checkObj(this, false);
            gp.collision.checkEntity(this,gp.Monsters);
            if (collisionLeft == false) {
                velX = -speed;
            }
            else {
                velX = 0;
                direction = right;
            }
        }
        else if(direction.equals(right)) {
            collisionRight = false;
            gp.getCollision().checkRight(this);
            gp.getCollision().checkObj(this, false);
            gp.collision.checkEntity(this,gp.Monsters);
            if (collisionRight == false) {
                velX = speed;
            }
            else {
                velX = 0;
                direction = left;
            }
        }
        //dato che si muove di continuo aggiorniamo continuamente l'entità
        spriteCounter += 1;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        if(direction.equals(left)) { //siamo in draw quindi in base a come ci muoviamo disegnamo a destra o a sinistra
            if(spriteNum == 1) {
                image = left1;
            }
            if(spriteNum == 2) {
                image = left2;
            }
        }
        if(direction.equals(right)) {
            if(spriteNum == 1) {
                image = right1;
            }
            if(spriteNum == 2) {
                image = right2;
            }
        }

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
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {//la disegno solo

            g2.drawImage(image, x, y, null); //disegnamo
            // l'immagine corrispondente
        }
        else if(screenX > worldX || screenY > worldY || rightOffset > gp.getWorldWidth() - worldX || bottomOffset > gp.getWorldHeight() - worldY) {
            g2.drawImage(image, x, y, null);
        }
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
    public int getScreenX() {
        return screenX;
    }
    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }
    public int getScreenY() {
        return screenY;
    }
    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }
    public boolean isCollisionTop() {
        return collisionTop;
    }
    public void setCollisionTop(boolean collisionTop) {
        this.collisionTop = collisionTop;
    }
    public boolean isCollisionLeft() {
        return collisionLeft;
    }
    public void setCollisionLeft(boolean collisionLeft) {
        this.collisionLeft = collisionLeft;
    }
    public boolean isCollisionRight() {
        return collisionRight;
    }
    public void setCollisionRight(boolean collisionRight) {
        this.collisionRight = collisionRight;
    }
    public boolean isCollisionBottom() {
        return collisionBottom;
    }
    public void setCollisionBottom(boolean collisionBottom) {
        this.collisionBottom = collisionBottom;
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
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
    public double getVelY() {
        return velY;
    }
    public void setVelY(double velY) {
        this.velY = velY;
    }
    public double getVelX() {
        return velX;
    }
    public void setVelX(double velX) {
        this.velX = velX;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public int getLife() {
        return Life;
    }
    public void setLife(int life) {
        Life = life;
    }
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}