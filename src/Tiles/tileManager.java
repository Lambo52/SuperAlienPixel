package Tiles;

import main.gamePanel;
import main.tilesOptimizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tileManager {

    gamePanel gp;
    Tile[] tileArray;
    int[][] mapTileNum;
    int worldCol, worldRow;
    int tileNum;
    int worldX, worldY, screenX, screenY;

    public tileManager(gamePanel gp) { //COSTRUTTORE, PASSATO PER PARAMETRO IL PANNELLO DAL GAMEPANEL
        this.gp = gp;

        tileArray = new Tile[10]; //10 tipi di tiles, acqua terra ecc.
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()]; //è una matrice di tutte le colonne e righe
        // delle
        // tail
        // della mappa
        getTileImage();
    }
    public void startTileManager() {
        if(gp.getLevel() == gamePanel.level1) {
            loadMap("/map/map1new.txt");
        }
        else if(gp.getLevel() == gamePanel.level2) {
            loadMap("/map/map2new.txt");
        }
        else if(gp.getLevel() == gamePanel.level3) {
            loadMap("/map/map3new.txt");
        }
    }

    public void getTileImage() { //METODO CHIAMATO DAL COSTRUTTORE DI tileManager, qui si caricano le tipologie di tail
        //caricamento nothing
        loadTile(0, "0nothing", false);

        //caricamento wall
        loadTile(1, "1wall", true);

        //caricamento grass
        loadTile(2, "2grass", true);

        //caricamento heart
        loadTile(3, "3earth", true);

        //caricamento grasstopright
        loadTile(4, "4grasstopright", true);

        //caricamento grasstopleft
        loadTile(5, "5grasstopleft", true);

        //caricamento earthright
        loadTile(6, "6earthright", true);

        //caricamento earthleft
        loadTile(7, "7earthleft", true);

        //caricamento earthtopright
        loadTile(8, "8earthtopright", true);

        //caricamento earthtopleft
        loadTile(9, "9earthtopleft", true);
    }

    public void loadTile(int index, String name, boolean collision) {

        tilesOptimizer tileScaled = new tilesOptimizer();
        try {
            tileArray[index] = new Tile();
            tileArray[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tileste/" + name + ".png")));

            tileArray[index].setImage(tileScaled.scaleImage(tileArray[index].image, gp.getTileSize(), gp.getTileSize())); //così
            // l'immagine
            // è già impostata come dimensioni, non bisogna impostargliele dal g2.draw rallentando le prestazioni
            tileArray[index].setCollision(collision);

        }
        catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void loadMap(String file) { //chiamato dal costruttore, qui si carica la mappa intera grazie al file map.txt

        try{
            InputStream is = getClass().getResourceAsStream(file); //per importare il text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //per leggere il contenuto del text file

            int col = 0;
            int row = 0;

            while(col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine(); //legge singola linea

                while(col < gp.getMaxWorldCol()) {

                    String numbers[] = line.split(" "); //si splitta grazie agli spazi, avremo un array numbers di
                    // interi
                    int num = Integer.parseInt(numbers[col]); //il num è uguale al numero nella mappa presente
                    mapTileNum[col][row] = num; //parte IMPORTANTE, qua si mette il numero dentro a mapTileNum
                    col++;
                }
                if(col == gp.getMaxWorldCol()) { //si azzerano le colonne se si è arrivati al massimo e si incrementano le
                    // righe
                    col = 0;
                    row++;
                }
            }
            br.close(); //chiudiamo il bufferedreader
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        worldCol = 0;
        worldRow = 0;

        while(worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {

            tileNum = mapTileNum[worldCol][worldRow]; //tileNum prende da mapTileNum il numero corrispondente

            worldX = worldCol * gp.getTileSize(); //worldX è la posizione nel mondo
            worldY = worldRow * gp.getTileSize();
            screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX(); //screenX è la posizione nello
            // schermo
            screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            //BLOCCO CAMERA ALLA FINE DELLA MAPPA
            if(gp.getPlayer().getScreenX() > gp.getPlayer().getWorldX()) { //blocco a sinistra
                screenX = worldX;
            }
            if(gp.getPlayer().getScreenY() > gp.getPlayer().getWorldY()) {
                screenY = worldY;
            }
            int rightOffset = gp.getScreenWidth() - gp.getPlayer().getScreenX();
            if(rightOffset > gp.getWorldWidth() - gp.getPlayer().getWorldX()) {
                screenX = gp.getScreenWidth() - (gp.getWorldWidth() - worldX);
            }
            int bottomOffset = gp.getScreenHeight() - gp.getPlayer().getScreenY();
            if(bottomOffset > gp.getWorldHeight() - gp.getPlayer().getWorldY()) {
                screenY = gp.getScreenHeight() - (gp.getWorldHeight() - worldY);
            }

            if(tileNum != 0) {
                if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() && worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() && worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() && worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {//la
                    // disegno solo se rientra nello schermo
                    g2.drawImage(tileArray[tileNum].getImage(), screenX, screenY, null); //disegnamo
                    // l'immagine corrispondente
                } else if (gp.getPlayer().getScreenX() > gp.getPlayer().getWorldX() || gp.getPlayer().getScreenY() > gp.getPlayer().getWorldY() || rightOffset > gp.getWorldWidth() - gp.getPlayer().getWorldX() || bottomOffset > gp.getWorldHeight() - gp.getPlayer().getWorldY()) {
                    g2.drawImage(tileArray[tileNum].getImage(), screenX, screenY, null);
                }
            }

            worldCol++; //*incrementiamo le colonne

            if(worldCol == gp.getMaxWorldCol()) { //azzeriamo le colonne se si è arrivati al massimo e incrementiamo le righe
                worldCol = 0;

                worldRow++;

            }
        } //si chiude questo processo quando siamo arrivati al numero massimo di righe e di colonne.
    }

    public Tile[] getTileArray() {
        return tileArray;
    }
    public int[][] getMapTileNum() {
        return mapTileNum;
    }
}