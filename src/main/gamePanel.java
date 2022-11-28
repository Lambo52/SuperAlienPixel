package main;

import Backgrounds.Background;
import DataBase.dataBaseFile;
import Entity.Bullets;
import Entity.Entity;
import Entity.Player;
import Inputs.keyHandler;
import Inputs.mouseInputs;
import Objects.superObject;
import Screens.*;
import Tiles.tileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.Main.window;

public class gamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16; //ogni personaggio/mattone è fatto da 16x16 //MODIFICATO PER MOSCONI
    final int scale = 4; //la scala è per 3 quindi 16*3=48 MODIFICATO PER MOSCONI
    public final  int tileSize = originalTileSize * scale; //48*48
    public final int maxScreenCol = 16;// quindi è un quattro terzi
    public final int maxScreenRow = 9;
    public final int screenWidth = /*1400;*/tileSize * maxScreenCol; //larghezza dello schermo 768 pixel //MODIFICATO
    public final int screenHeight = /*600;//*/tileSize * maxScreenRow; //altezza dello schermo 576 pixel

    //IMPOSTAZIONI DEL MONDO
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 20;

    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    double drawInterval;
    double delta;
    long lastTime;
    long currentTime;

    //long drawStart; //debug
    BufferedImage tempScreen; //è l'immagine su cui inizialmente andiamo a disegnare tutto
    Graphics2D g2;

    public int screenWidthTrue = screenWidth; //queste variabili le settiamo nel setfullscreen
    public int screenHeightTrue = screenHeight;

    public int commandNum = 0;
    //FPS
    int FPS = 60;

    //PARTE IMPORTANTE PARTICOLARE INIZIALIZZIAMO IL KEY, TILE, E PLAYER----------------------------------
    public tileManager Tile = new tileManager(this); //inizializziamo il tilemanager per caricare la mappa, per poi
    // chiamare il metodo draw di tileManager
    keyHandler Key = new keyHandler(this); // key è il listener della classe keyHandler
    mouseInputs mouseListener = new mouseInputs(this);
    Thread gameThread; //gameThread è il thread principale per il game loop
    boolean running = false; //serve per non far ripartire la thread
    public Player player = new Player(this,Key); //creiamo un player grazie alla classe Player e ci passiamo per
    // parametri
    // questo gamepanel e la Key creata in questa classe ma che fa riferimento alla classe keyHandler
    public collisionChecker collision = new collisionChecker(this); //al collisionchecker bisogna passare questo gp,
    // poi le varie entità potranno usarlo chiamando questo metodo direttamente da qui, e passandogli la loro entità
    // in modo da rendere questa classe universale
    public superObject obj[] = new superObject[10];
    public Entity Monsters[] = new Entity[10];
    public objectSetter oSetter = new objectSetter(this);
    public titleScreen Title = new titleScreen(this);
    public pauseScreen Pause = new pauseScreen(this);
    public UI ui = new UI(this);
    public finishScreen Finish = new finishScreen(this);
    public deathScreen Death = new deathScreen(this);
    public Background background = new Background(this);
    public dataScreen data = new dataScreen(this);

    //GAMEPANEL PART ----------------------------------------
    public int gameState; //a volte ritornano
    public final int playState = 1;
    public final int pauseState = 2;
    public final int titleScreen = 3;
    public final int finishState = 4;
    public final int deathState = 5;
    public final int dataState = 6;

    //SELEZIONE LIVELLO

    int Level;
    public static int level1 = 1;
    public static int level2 = 2;
    public static int level3 = 3;

    //BULLETS PART
    public ArrayList<Bullets> bullet = new ArrayList<>();

    //FINE PARTE IMPORTANTE PARTICOLARE
    Image image = new ImageIcon(this.getClass().getResource("/icon/image.png")).getImage(); //immagine icona

    //DATABASE
    dataBaseFile db = new dataBaseFile();
    String User;

    public gamePanel() { //costruttore del gamepanel
        this.setPreferredSize(new Dimension(screenWidth,screenHeight)); //dimensioni pannello

        window.setIconImage(image); //boh

        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //incrementa le performances
        this.addKeyListener(Key); //implementa i tasti
        this.addMouseListener(mouseListener); //implementa i clic del mouse
        this.addMouseMotionListener(mouseListener); //implementa i movimenti del mouse

        this.requestFocus();// dovrebbe servire per il mouse ma funziona anche senza dato che c'è setFocusable
        this.setFocusable(true);
    }

    public void setupGame() {
        db.openDatabase();
        //QUI METTO I NOMI DELLE COSE
        Title.updateNames(); //così fa tutto il metodo nella classe titleScreen, viene chiamato anche nel keyhandler
        // per aggiornare i numi nel database e poi anche nel programma corrente.

        this.gameState = titleScreen ; //L'HO MESSO QUI PERCHE NON SAPEVO DOVE METTERLO E NON MI ANDAVA DI VEDERE IL
        // TUTORIAL PRECEDENTE
        this.tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        this.g2 = (Graphics2D)tempScreen.getGraphics();
        setFullScreen();
    }

    public void loadMapByUser() { //DA FARE DOPO CHE ABBIAMO SETTATO L'USER NEL TITOLO

        Level = db.loadDataBaseLevel(User); //ATTENZIONE QUESTO VA PRESO DAL DATABASE
        if(Level == level1) {
            oSetter.setObjectLevel1();
            oSetter.setEntityLevel1();
        }
        else if(Level == level2) {
            oSetter.setObjectLevel2(); //li metto qua perché non so dove metterli
            oSetter.setEntityLevel2();
        }
        else if(Level == level3) {
            oSetter.setEntityLevel3();
            oSetter.setEntityLevel3();
        }
        Tile.startTileManager(); //va fatto dopo aver assegnato il livello se no non parte niente
    }

    public void reSetUpGame() {
        //if(resetAll) { //così lo fa solo una volta a titleScreen e non si bugga facendo sfracellare il gameloop

        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i] = null;
            }
        }

        for(int i = 0; i < Monsters.length; i++) {
            if(Monsters[i] != null) {
                Monsters[i] = null;
            }
        }

        for(int i = 0; i < bullet.size(); i++) {
            bullet.remove(i);
        }

        ui.setPlayTime(0);
        player.setDefaultValues(); //mettiamo le variabili di default
        if(Level == level1) {
            oSetter.setObjectLevel1(); //li metto qua perché non so dove metterli
            oSetter.setEntityLevel1();
        }
        else if(Level == level2) {
            oSetter.setObjectLevel2(); //li metto qua perché non so dove metterli
            oSetter.setEntityLevel2();
        }
        else if(Level == level3) {
            oSetter.setObjectLevel3(); //li metto qua perché non so dove metterli
            oSetter.setEntityLevel3();
        }

        //}
    }

    public void setFullScreen() {
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        screenWidthTrue = window.getWidth(); //abbiamo settato grazie alla riga precedente il monitore in
        // fullscreen quindi ora possiamo prendere le informazioni sulla risoluzione
        screenHeightTrue = window.getHeight();

        //System.out.println("larghezza schermo normale " + screenWidth);
        //System.out.println("larghezza schermo scalato " + screenWidthTrue);
    }
    public void bulletCreator() {
         bullet.add(new Bullets(this));
    }
    public void startGameThread(){

        if (running) {
            return; //ritorna se la thread è gia partita e non riparte 2 volte, per sicurezza
        }
        running = true;
        gameThread = new Thread(this); //qui si inizializza in Thread
        gameThread.start(); //questo chiama il metodo run direttamente
    }


    @Override
    public void run() { //metodo generato automaticamente da runnable, GAMELOOP

        drawInterval = 1000000000/FPS;
        delta = 0;
        lastTime = System.nanoTime();
        //long currentTime;
        while (gameThread != null) {

            currentTime = System.nanoTime(); // misura in miliardo di nanosecondi
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                //qui dobbiamo aggiornare le informazioni del giocatore
                update();
                //rappresentiamo poi le informazioni del personaggio
                paintToTempScreen();
                drawToScreen();
                delta--;
            }
        }
    }

    public void update() { //aggiorniamo

        if(gameState == playState) { //SE STIAMO GIOCANDO UPDATO TUTTO
            //PLAYER
            player.update(); // al posto che fare l'update di ogni cosa del player qua la facciamo nella classe player,
            // semplicemente distribuiamo update tra le classi e non facciamo tutto qua se no è un casino

            //OBJECTS
            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    obj[i].update();
                }
            }

            //BULLETS
            for (int i = 0; i < bullet.size(); i++) {
                if (bullet.get(i).isAlive() == false) {
                    bullet.remove(i);
                    continue;
                }
                bullet.get(i).update();
            }

            //MONSTERS
            for(int i = 0; i < Monsters.length; i++) {
                if(Monsters[i] != null) {
                    if(Monsters[i].isAlive()) {
                        Monsters[i].update();
                    }
                    else {
                        Monsters[i] = null;
                    }
                }
            }

            ui.updateTimer();
        }

        else if(gameState == pauseState) {
            Pause.update();
        }

        else if(gameState == finishState) {
            Finish.update();
        }

        else if(gameState == titleScreen) {
            Title.update();
        }

        else if(gameState == deathState) {
            Death.update();
        }
        else if(gameState == dataState) {
            data.update();
        }
    }

    public void paintToTempScreen() { //DISEGNAMO AL MONITOR TEMPORANEO PER POI DISEGNARE ALLO SCHERMO
        //drawStart = System.nanoTime(); //debug
        //if(gameState == playState) {

        if(gameState == titleScreen) {//ALTRA COSA DA METTERE IN UI GIURO è LA PROSSIMA COSA CHE FACCIO
            Title.draw(g2); //chiamiamo il draw di una classe apposta così non facciamo tutto qua
        }
        else if(gameState == dataState) {
            data.draw(g2);
        }
        else {
            //BACKGROUND
            background.draw(g2);
            //TILE
            Tile.draw(g2); //chiamiamo il draw del tail dato che non facciamo tutto qua ma su diverse classi, importante
            // farlo prima di disegnare il player se no si sovrappongono
            //OBJECTS
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2);
                }
            }
            //MONSTERS
            for (int i = 0; i < Monsters.length; i++) {
                if (Monsters[i] != null) {
                    Monsters[i].draw(g2);
                }
            }
            //PLAYER
            player.draw(g2); // stessa cosa per update la facciamo anche qua, non ha senso disegnare tutto qua quindi
            // chiamiamo un metodo a player e gli passiamo ciò che vogliamo disegnare
            //BULLETS
            for (int i = 0; i < bullet.size(); i++) {
                bullet.get(i).draw(g2);
            }
            //UI
            ui.draw(g2); // qui scrive anche la vita
            //UI TIMER
            ui.drawTimer(g2);
            //}
            if (gameState == pauseState) { //PAUSA DA METTERE IN UNA CLASSE APPOSTA SE NO IL GAMEPANEL DIVENTA UN
                // INFERNO UI
                //player.setImmunity(60);
                Pause.draw(g2); //disegno menu di pausa con una classe apposta
            } else if (gameState == finishState) { //se arriva alla porta
                //player.setImmunity(60);
                Finish.draw(g2);
                ui.drawTimer(g2);
            } else if (gameState == deathState) {
                //player.setImmunity(60);
                Death.draw(g2);
            }
        }
        //debug
        //long drawTime = System.nanoTime() - drawStart;
        //g2.setColor(Color.white);
        //g2.drawString(String.valueOf(drawTime),10,400);
        //
         //System.out.println(drawTime);
    }

    public void drawToScreen() { //disegnamo nello schermo finale
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0,screenWidthTrue,screenHeightTrue,null); //scriviamo l'immagine alla nuova risoluzione

        g.dispose();
    }

    public int getTileSize() {
        return tileSize;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    public int getMaxWorldRow() {
        return maxWorldRow;
    }
    public int getPlayState() {
        return playState;
    }
    public int getPauseState() {
        return pauseState;
    }
    public int getTitleScreen() {
        return titleScreen;
    }
    public int getFinishState() {
        return finishState;
    }
    public int getDeathState() {
        return deathState;
    }
    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    public int getCommandNum() {
        return commandNum;
    }
    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }
    public superObject[] getObj() {
        return obj;
    }
    public Entity[] getMonsters() {
        return Monsters;
    }
    public UI getUi() {
        return ui;
    }
    public tileManager getTile() {
        return Tile;
    }
    public Player getPlayer() {
        return player;
    }
    public collisionChecker getCollision() {
        return collision;
    }
    public pauseScreen getPause() {
        return Pause;
    }
    public titleScreen getTitle() {
        return Title;
    }
    public void setTile(tileManager tile) {
        Tile = tile;
    }
    public finishScreen getFinish() {
        return Finish;
    }
    public deathScreen getDeath() {
        return Death;
    }
    public int getMaxScreenCol() {
        return maxScreenCol;
    }
    public int getMaxScreenRow() {
        return maxScreenRow;
    }
    public int getWorldWidth() {
        return worldWidth;
    }
    public int getWorldHeight() {
        return worldHeight;
    }
    public int getOriginalTileSize() {
        return originalTileSize;
    }
    public Background getBg() {
        return background;
    }
    public keyHandler getKey() {
        return Key;
    }
    public int getDataState() {
        return dataState;
    }
    public int getLevel() {
        return Level;
    }
    public void setLevel(int level) {
        Level = level;
    }
    public String getUser() {
        return User;
    }
    public void setUser(String user) {
        User = user;
    }
    public dataBaseFile getDb() {
        return db;
    }
    public dataScreen getData() {
        return data;
    }
    public int getScreenWidthTrue() {
        return screenWidthTrue;
    }
    public int getScreenHeightTrue() {
        return screenHeightTrue;
    }
}