package Objects;

import main.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Coin extends superObject{
    //public gamePanel tilePanel;// questa roba ti ha buttato via una mattina
    public BufferedImage Coin1; //serve per metterla in alto a sinistra
    BufferedImage Coin2;
    BufferedImage Coin3;
    BufferedImage Coin4;
    int spriteCounter = 0;
    //gamePanel tilePanel; //serve solo per impostare la tilesize del coin

    public Coin(gamePanel gp) {
        super(gp);

        name = coin;
        coinLoader();
    }

    public void coinLoader() {

        try {
            Coin1 = ImageIO.read(getClass().getResourceAsStream("/Coins/coin1.png"));
            Coin1 = tileScaled.scaleImage(Coin1, gp.getTileSize(), gp.getTileSize());

            Coin2 = ImageIO.read(getClass().getResourceAsStream("/Coins/coin2.png"));
            Coin2 = tileScaled.scaleImage(Coin2, gp.getTileSize(), gp.getTileSize());

            Coin3 = ImageIO.read(getClass().getResourceAsStream("/Coins/coin3.png"));
            Coin3 = tileScaled.scaleImage(Coin3, gp.getTileSize(), gp.getTileSize());

            Coin4 = ImageIO.read(getClass().getResourceAsStream("/Coins/coin4.png"));
            Coin4 = tileScaled.scaleImage(Coin4, gp.getTileSize(), gp.getTileSize());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        spriteCounter++; //ogni frame incrementiamo spriteCounter così se arriva a 12 cambiamo sprite
        if (spriteCounter >= 0) {
            image = Coin1; //porta chiusa
        }
        if (spriteCounter > 10) {
            image = Coin2; //porta socchiusa
        }
        if (spriteCounter > 20) {
            image = Coin3; //porta aperta
        }
        if (spriteCounter > 30) {
            image = Coin4; //porta socchiusa
        }
        if (spriteCounter > 40) {
            image = Coin3;
        }
        if (spriteCounter > 50) {
            image = Coin2;
        }
        if (spriteCounter > 60) {
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        super.draw(g2);
    }
    //draw non ha senso dato che scegliamo l'immagine nell'update e la disegnamo nel superobject

    public BufferedImage getCoin1() {
        return Coin1;
    }
}