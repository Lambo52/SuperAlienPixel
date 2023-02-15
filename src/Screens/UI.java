package Screens;

import Entity.Bullets;
import Objects.Coin;
import Objects.Life;
import main.gamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI extends Screens{
    BufferedImage coinImage;
    BufferedImage lifeImage;
    BufferedImage fireImage;
    int messageCounter = 0;
    boolean messageOn = true;
    int overMessageCounter = 0;
    boolean overMessage = false;
    String message;
    DecimalFormat dFormat = new DecimalFormat("#0.00"); //2 decimali al massimo
    double playTime = 0;

    public UI(gamePanel gp) {
        super(gp);

        Coin coin = new Coin(gp);
        coinImage = coin.getCoin1();

        Life life = new Life(gp);
        lifeImage = life.getImage();

        Bullets bullet = new Bullets(gp);
        fireImage = bullet.getImage();
    }


    public void showMessage(String message) {

        overMessage = true; //non lo passo per parametro perché se chiamo questa funzione è ovvio che è true
        this.message = message;
    }

    public void draw(Graphics2D g2) {

        //if(messageOn) {
            g2.drawImage(coinImage, gp.getTileSize() / 2, gp.getTileSize() / 2, null);

            g2.setFont(Font_40);
            g2.setColor(Color.white);
            g2.drawString(String.valueOf(gp.getPlayer().getNumCoin()), (int) (gp.getTileSize() * 1.6),
                    gp.getTileSize() + 14);
            //g2.drawString("Bullets: " + gp.player.counterMadonne, 50, 100);
            //messageCounter += 1;
           // if(messageCounter > 180) {
              //  messageOn = false;
            //    messageCounter = 0;
          //  }
        //}

        if(overMessage) {

            int length = getLength(message, g2);
            g2.setFont(Font_40);
            g2.setColor(Color.white);
            g2.drawString(message, gp.getScreenWidth() / 2  - length / 2,gp.getTileSize() * 5);

            overMessageCounter += 1;
            if(overMessageCounter > 180) {
                overMessage = false;
                overMessageCounter = 0;
            }
        }

        lifeDrawer(g2);
        fireDrawer(g2);
    }

    public void lifeDrawer(Graphics2D g2) {
        int y = gp.getTileSize() * (gp.getMaxScreenRow() - 1) - gp.getTileSize() / 2;
        int x = gp.getTileSize() / 2;

        for(int i = 0; i < gp.getPlayer().getLife(); i++) {
            g2.drawImage(lifeImage, x, y, null);
            x += gp.getTileSize();
        }
    }
    public void updateTimer() {
        playTime += (double) 1 / 60;
    }

    public void drawTimer(Graphics2D g2) {
        g2.setFont(Font_30);
        g2.setColor(Color.white);
        g2.drawString(String.valueOf(dFormat.format(playTime)),gp.getScreenWidth() -100, 50);
    }
    public void fireDrawer(Graphics2D g2) {
        int x = gp.getScreenWidth() - gp.getTileSize() - gp.getTileSize() / 2;
        int y = gp.getScreenHeight() - gp.getTileSize() - gp.getTileSize() / 2;
        for(int i = 0; i < Math.abs(gp.getPlayer().getCounterBullets() - 3); i++) {
            g2.drawImage(fireImage, x, y, null);
            x -= gp.getTileSize() + 3;
        }
    }

    public boolean isMessageOn() {
        return messageOn;
    }
    public void setMessageOn(boolean messageOn) {
        this.messageOn = messageOn;
    }
    public void setPlayTime(double playTime) {
        this.playTime = playTime;
    }
}