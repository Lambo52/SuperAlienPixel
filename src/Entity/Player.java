package Entity;

import Objects.superObject;
import main.gamePanel;
import Inputs.keyHandler;

import java.awt.*;

public class Player extends Entity{

    keyHandler Key;
    int numCoin;
    int counterBullets;
    int bulletsBlocker;
    int Immunity;

    public Player(gamePanel gp, keyHandler Key) { //costruttore

        super(gp);
        this.Key = Key;

        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        maxLife = 3;

        setDefaultValues(); //importante se no non parte il personaggio
        getPlayerImage(); //chiamiamo il metodo per prendere l'immagine del giocatore
    }

    public void setDefaultValues() { //mettiamo le coordinate di base

        if(gp.getLevel() == gamePanel.level1) {
            worldX = gp.getTileSize() * 7; //x, y, speed non dobbiamo inizializzarle dato che abbiamo l'estensione di
            // una classe
            // astratta
            worldY = gp.getTileSize() * 12;
        }
        else if(gp.getLevel() == gamePanel.level2) {
            worldX = gp.getTileSize() * 4; //x, y, speed non dobbiamo inizializzarle dato che abbiamo l'estensione di
            // una classe
            // astratta
            worldY = gp.getTileSize() * 12;
        }
        else if(gp.getLevel() == gamePanel.level3) {
            worldX = gp.getTileSize() * 5; //x, y, speed non dobbiamo inizializzarle dato che abbiamo l'estensione di
            // una classe
            // astratta
            worldY = gp.getTileSize() * 12;
        }
        speed = 5;
        direction = right; //di default dobbiamo impostare una direzione se no non sa che immagine caricare delle
        // tante
        falling = true;
        numCoin = 0;
        counterBullets = 0;
        bulletsBlocker = 30;
        Life = maxLife;
        Immunity = 60;

        velX = 0;
        velY = 0;
    }

    public void getPlayerImage() { //qui prendiamo l'immagine del player e la chiamiamo nella classe entity

        left1 = loadImage("/player/left1");
        left2 = loadImage("/player/left2");
        right1 = loadImage("/player/right1");
        right2 = loadImage("/player/right2");
    }

    public void update() {
        worldY += velY;
        worldX += velX;

        if(Key.isSpacePressed()) {
            jumping = true;
        }
        else {
            jumping = false;
        }

        //CONTROLLO GRAVITY nella classe entity così viene chiamata anche dai mostri
        gravityCheck();

        collisionTop = false;
        gp.getCollision().checkTop(this);
        if (collisionTop) {
            velY = 1;
        }

        //MODIFICATO NUOVO TUTORIAL che fa schifo perché non si capisce niente
        if (jumping && !falling) { //CI ENTRIAMO SOLO NEL MOMENTO IN CUI PREMIAMO JUMP, DOPO LA
            // GRAVITà FA IL RESTO
            collisionTop = false;
            gp.getCollision().checkTop(this);
            if (!collisionTop) {
                velY = -11;
                falling = true;
            }
            else {
                velY = 0;
            }
            jumping = false;
        }

        if(bulletsBlocker <= 30) {
            bulletsBlocker += 1;
        }

        if(Key.isfPressed() && bulletsBlocker > 30) {
            if(counterBullets < 3) {
                gp.bulletCreator();
                counterBullets += 1;
                bulletsBlocker = 0;
            }
            else {
                counterBullets = 3;
            }
        }

        if(Key.isRightPressed() || Key.isLeftPressed()) {

            if (Key.isLeftPressed()) { // ci muoviamo a sinistra
                direction = left;
                collisionLeft = false;
                gp.getCollision().checkLeft(this);
                if (!collisionLeft) {
                    velX = -speed;
                }
                else {
                    velX = 0; //se no entra nel muro
                }
            }

            else if (Key.isRightPressed()) { // ci muoviamo a destra
                direction = right;
                collisionRight = false; //creato in Entity
                gp.getCollision().checkRight(this);
                //collisionOn dice se la collisione c'è o no
                if (!collisionRight) {
                    velX = speed;
                }
                else {
                    velX = 0; //se no entra nel muro
                }
            }
            //collisioni checker
            if (!falling) { //SE NEL SALTO NON VOGLIAMO LE SPRITES CHE CAMBIANO
                spriteCounter++; //ogni frame incrementiamo spriteCounter così se arriva a 12 cambiamo sprite
                if (spriteCounter > 12) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    }
                    else {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
            else {
                spriteCounter = 12; //COSì ALLA FNIE DEL SALTO CAMBIA POSA
            }
        }
        else {
            spriteCounter = 12; // questo serve perché così se sto fermo e di colpo mi muovo il personaggio cambia posa
            velX = 0;
        }
        //per farci colpire
        if(Immunity <= 60) {
            Immunity += 1;
        }
        //CHECK OBJECT COLLISION
        int objIndex = gp.getCollision().checkObj(this,true);
        pickUpObject(objIndex);

        //CHECK MONSTER COLLISION
        int monsterIndex = gp.getCollision().checkEntity(this, gp.getMonsters());
        monsterCollision(monsterIndex);
    }

    public void pickUpObject(int index) {

        if(index != 999) { //se è 999 non abbiamo toccato niente

            String objectName = gp.getObj()[index].getName();
            if (objectName.equals(superObject.coin)) {
                numCoin += 1;
                gp.getObj()[index] = null; //distruggiamo l'oggetto a quell'index
                gp.getUi().setMessageOn(true);
            }
            if (objectName.equals(superObject.door)) {
                if(numCoin == 5) {
                    gp.setGameState(gp.getFinishState());
                }
                else {
                    gp.getUi().showMessage("you need 5 coins!");
                }
            }
        }

    }

    public void monsterCollision(int index) {
        if(index != 999) {
            //non assegno il nome dato che qualsiasi collisione può essere solo del monster dato che l'array si
            // chiama monsters
            //significa che abbiamo toccato un mostro
            //gp.ui.showMessage("SEI MORTO");
            if(Immunity > 60) {
                Life -= 1;
                Immunity = 0;
            }
            if(Life <= 0) {
                Life = 0;
                gp.setGameState(gp.getDeathState());
            }
        }
    }

    public void draw(Graphics2D g2) {

        if(Immunity >= 60) {
            setImage();
        }
        else {
            if(Immunity % 10 == 0) { //mentre veniamo danneggiati l'immagine sfarfalla 6 volte al secondo
                setImage();
            }
            else {
                image = null;
            }
        }

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


        g2.drawImage(image, x, y, null); //disegnamo il personaggio IMPORTANTE
    }
    public void setImage() { //serve così chiamiamo solo setImage per mettere l'immagine del player
        if (direction.equals(left)) { //siamo in draw quindi in base a come ci muoviamo disegnamo a destra o a sinistra
            if (spriteNum == 1) {
                image = left1;
            } else if (spriteNum == 2) {
                image = left2;
            }
        } else if (direction.equals(right)) {
            if (spriteNum == 1) {
                image = right1;
            } else if (spriteNum == 2) {
                image = right2;
            }
        }
    }

    public int getNumCoin() {
        return numCoin;
    } //il set non l'ho messo dato che quando va a zero bisogna per forza chiamare setDefaultVariables dove lo si
    // mette a 0
    public void setImmunity(int immunity) {
        Immunity = immunity;
    }
    public int getCounterBullets() {
        return counterBullets;
    }
}