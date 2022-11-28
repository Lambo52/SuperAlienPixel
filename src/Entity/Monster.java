package Entity;

import main.gamePanel;

import java.awt.*;
import java.util.Random;

public class Monster extends Entity{

    public Monster(gamePanel gp) {
        super(gp);

        solidArea.y = gp.getTileSize() * 1 / 16;
        solidArea.height = gp.getTileSize() - gp.getTileSize() * 1 / 8;
        solidArea.x = gp.getTileSize() / 4;
        solidArea.width = gp.getTileSize() / 2;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getMonsterImage();
    }

    public void setDefaultValues() {
        direction = left;
        speed = 3;
    }


    public void getMonsterImage() { //qui prendiamo l'immagine del player

        left1 = loadImage("/Enemies/monster_left1");
        left2 = loadImage("/Enemies/monster_left2");
        right1 = loadImage("/Enemies/monster_right1");
        right2 = loadImage("/Enemies/monster_right2");
    }

    public void setAction() {

        if(actionCounter > 60) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 50) {
                direction = left;
            }
            if (i > 50) {
                direction = right;
            }
            actionCounter = 0;
        }

        actionCounter += 1;
    }

    public void update() {
        super.update();
    }
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }
}