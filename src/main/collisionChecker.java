package main;

import Entity.Entity;

import java.awt.*;

public class collisionChecker {

    int entityLeft, entityRight, entityTop, entityBottom;
    int entityLeftCol, entityRightCol, entityBottomRow, entityTopRow;
    int tileNum1, tileNum2;
    gamePanel gp;
    public collisionChecker(gamePanel gp) { //gamepanel passato da gamePanel e classe inizializzata da gamePanel
        this.gp = gp;
    }

    public void checkTop(Entity entity) { //metodo chiamato dalle varie Entity
        entityLeft = entity.getWorldX() + entity.getSolidArea().x;
        entityRight = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;//sono i 4 corners
        entityTop = entity.getWorldY() + entity.getSolidArea().y;

        entityLeftCol = entityLeft / gp.getTileSize();
        entityRightCol = entityRight / gp.getTileSize();

        entityTopRow = (entityTop + (int) entity.getVelY()) / gp.getTileSize();
        tileNum1 = gp.getTile().getMapTileNum()[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTile().getMapTileNum()[entityRightCol][entityTopRow];

        if (gp.getTile().getTileArray()[tileNum1].isCollision() == true || gp.getTile().getTileArray()[tileNum2].isCollision() == true) {
            entity.setCollisionTop(true); //collisione col soffitto
        }
    }

    public void checkLeft(Entity entity){ //metodo chiamato dalle varie Entity

        entityLeft = entity.getWorldX() + entity.getSolidArea().x;
        entityTop = entity.getWorldY() + entity.getSolidArea().y + 5; //altrimenti rimane nel soffitto e non scende più
        entityBottom = (int) (entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height/1.1);

        entityTopRow = entityTop / gp.getTileSize();
        entityBottomRow = entityBottom / gp.getTileSize();

        entityLeftCol = (entityLeft - entity.getSpeed()) / gp.getTileSize(); //è lo stesso + tanto va in negativo
        tileNum1 = gp.getTile().getMapTileNum()[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTile().getMapTileNum()[entityLeftCol][entityBottomRow];

        if (gp.getTile().getTileArray()[tileNum1].isCollision() == true || gp.getTile().getTileArray()[tileNum2].isCollision() == true) {
            entity.setCollisionLeft(true); //la collisione è true quindi non va avanti
        }
    }


    public void checkRight(Entity entity) { //metodo chiamato dalle varie Entity

        entityRight = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        entityTop = entity.getWorldY() + entity.getSolidArea().y + 5;
        entityBottom = (int) (entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height / 1.1);

        entityTopRow = entityTop / gp.getTileSize();
        entityBottomRow = entityBottom / gp.getTileSize();

        entityRightCol = (entityRight + entity.getSpeed()) / gp.getTileSize();
        tileNum1 = gp.getTile().getMapTileNum()[entityRightCol][entityTopRow];
        tileNum2 = gp.getTile().getMapTileNum()[entityRightCol][entityBottomRow];

        if(gp.getTile().getTileArray()[tileNum1].isCollision() == true || gp.getTile().getTileArray()[tileNum2].isCollision() == true) {
            entity.setCollisionRight(true);
        }
    }

    public void checkBottom(Entity entity) { //PORCATA GRAVITà MOSCONI MA NON SO COME ALTRO FARE

        entityLeft = entity.getWorldX() + entity.getSolidArea().x;
        entityRight = entity.getWorldX() + entity.getSolidArea().width + entity.getSolidArea().x;//sono i 4 corners
        entityBottom = entity.getWorldY() + entity.getSolidArea().height + entity.getSolidArea().y;

        entityLeftCol = entityLeft / gp.getTileSize();
        entityRightCol = entityRight / gp.getTileSize();

        entityBottomRow = (entityBottom + 7) / gp.getTileSize(); //CONTROLLARLO MI RACCOMANDO, + 7 perché 48 px è il
        // player ma /1.1 fa 43 nel right e left, in più la max gravity è 10 quindi perfetto.
        tileNum1 = gp.getTile().getMapTileNum()[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTile().getMapTileNum()[entityRightCol][entityBottomRow];

        if(gp.getTile().getTileArray()[tileNum1].isCollision() == true || gp.getTile().getTileArray()[tileNum2].isCollision() == true) {
            entity.setCollisionBottom(true);
        }
    }


    public int checkObj(Entity entity, boolean player) { //collisione con oggetti

        int index = 999;

        for(int i = 0; i < gp.getObj().length; i++) {

            if(gp.getObj()[i] != null) {
                //prendiamo la posizione della solidarea di entity
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                //prendiamo la solidarea dell'oggetto
                gp.getObj()[i].getSolidArea().x = gp.getObj()[i].getWorldX() + gp.getObj()[i].getSolidArea().x;
                gp.getObj()[i].getSolidArea().y = gp.getObj()[i].getWorldY() + gp.getObj()[i].getSolidArea().y;

                if(entity.getDirection().equals(Entity.left)) { //CASO SINISTRA
                    entity.getSolidArea().x -= entity.getSpeed();

                    if(collision(entity.getSolidArea(),gp.getObj()[i].getSolidArea())) {
                        if(gp.getObj()[i].isCollision() == true) {
                            entity.setCollisionLeft(true);
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                }
                else if (entity.getDirection().equals(Entity.right)) { //CASO DESTRA
                    entity.getSolidArea().x += entity.getSpeed();

                    if(collision(entity.getSolidArea(),gp.getObj()[i].getSolidArea())) {
                        if(gp.getObj()[i].isCollision() == true) {
                            entity.setCollisionRight(true);
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();

                gp.getObj()[i].getSolidArea().x = gp.getObj()[i].getSolidAreaDefaultX();
                gp.getObj()[i].getSolidArea().y = gp.getObj()[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {

        int index = 999;

        for(int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //prendiamo la posizione della solidarea di entity
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                //prendiamo la solidarea dell'oggetto
                target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x;
                target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y;

                if (entity.getDirection().equals(Entity.left)) { //CASO SINISTRA
                    entity.getSolidArea().x -= entity.getSpeed();

                    if (collision(entity.getSolidArea(), target[i].getSolidArea())) {
                        if(target[i] != entity) {
                            entity.setCollisionLeft(true);
                            index = i;
                        }
                    }
                }
                else if (entity.getDirection().equals(Entity.right)) { //CASO DESTRA
                    entity.getSolidArea().x += entity.getSpeed();
                    if (collision(entity.getSolidArea(), target[i].getSolidArea())) {
                        if(target[i] != entity) { //controlliamo se non si schianta su se stasso
                            entity.setCollisionRight(true);
                            index = i;
                        }
                    }
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();

                target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
                target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }

    //VIDEO NPC PER VEDERE COLLISIONE DA NPC A PLAYER MA NON MI SERVE PER ORA
    public boolean collision(Rectangle rec1, Rectangle rec2) {
        if(rec1.intersects(rec2)) {
            return true;
        }
        else {
            return false;
        }
    }
}