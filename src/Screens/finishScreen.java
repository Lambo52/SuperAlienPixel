package Screens;

import Buttons.menuButton;
import main.gamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class finishScreen extends Screens {

    String text;
    String textEnd;
    String backGame = "BACK";
    String nextLevel = "NEXT LEVEL";
    String exitGame = "EXIT";

    public finishScreen(gamePanel gp) {
        super(gp);
        buttons = new ArrayList<>();
        loadButtons();
    }
    public void loadButtons() {
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int)(gp.getTileSize() * 3),(int) (gp.getTileSize() * 3)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int)(gp.getTileSize() * 2.5),(int) (gp.getTileSize() * 2.5)));
        buttons.add(new menuButton(gp,gp.getScreenHeight() - (int)(gp.getTileSize() * 2),(int) (gp.getTileSize() * 2.5)));
    }

    public void update() {
        //niente
    }

    public void draw(Graphics2D g2) {
        //int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        //int x = gp.screenWidth / 4 - length / 2;
        //int y = gp.screenHeight / 4;
        super.draw(g2);
        g2.setFont(Font_40);
        g2.setColor(Color.yellow);
        if(gp.getLevel() == gamePanel.level1) {
            text = "Level 1 completed";
            textEnd = "Tap NEXT LEVEL to save!";
        }
        else if(gp.getLevel() == gamePanel.level2) {
            text = "Level 2 completed";
            textEnd = "Tap NEXT LEVEL to save!";
        }
        else if(gp.getLevel() == gamePanel.level3) {
            text = "Thank you for playing";
            textEnd = "You will restart the game";
        }

        length = getLength(text, g2);
        g2.drawString(text, gp.getScreenWidth() / 2 - length / 2, 150);

        length = getLength(textEnd, g2);
        g2.drawString(textEnd, gp.getScreenWidth() / 2 - length / 2, 250);



        //BOTTONI
        if(buttons.get(0).isButtonPressed()) {

            if(gp.getLevel() == gamePanel.level1) {
                gp.getDb().storeDataBase(2,"LEVEL 2", gp.getUser());
                gp.setLevel(gamePanel.level2); //AGGIORNARE DATABASE

            }
            else if(gp.getLevel() == gamePanel.level2) {
                gp.getDb().storeDataBase(3,"LEVEL 3", gp.getUser());
                gp.setLevel((gamePanel.level3)); //AGGIORNARE DATABASE
            }
            else if(gp.getLevel() == gamePanel.level3) {
                gp.getDb().storeDataBase(1,"LEVEL 1", gp.getUser());
                gp.setLevel(gamePanel.level1); //se abbiamo finito il livello 3 ripartiamo da capo E
                // AGGIORNIAMO IL DATABASE
            }
            gp.getTitle().updateNames();
            gp.getTile().startTileManager(); //qui setta la mappa, metodo a parte da invocare solo se la
            // mappa si resetta
            gp.reSetUpGame(); //qui setta anche gli oggetti in base al llivello
            gp.setGameState(gp.getPlayState());


            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(1).isButtonPressed()) {
            gp.setGameState(gp.getDataState());

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        else if(buttons.get(2).isButtonPressed()) {
            gp.getDb().closeDataBase();
            System.exit(0);

            gp.setCommandNum(0);

            resetButtons(buttons);
        }
        //FINEBOTTONI

        g2.setFont(Font_30);
        if (gp.getCommandNum() == 0 || buttons.get(0).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(0);
        } else {
            g2.setColor(Color.white);
        }
        //g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30)); già impostato
        length = getLength(nextLevel, g2);
        g2.drawString(nextLevel, gp.getScreenWidth() / 2 - length / 2,
                gp.getScreenHeight() - (int) (gp.getTileSize() * 2.5));

        if (gp.getCommandNum() == 1 || buttons.get(1).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(1);
        } else {
            g2.setColor(Color.white);
        }
        length = getLength(backGame, g2);
        g2.drawString(backGame, gp.getScreenWidth() / 2 - length / 2, gp.getScreenHeight() - gp.getTileSize() * 2);

        if (gp.getCommandNum() == 2 || buttons.get(2).getHover() == true) {
            g2.setColor(Color.GREEN);
            gp.setCommandNum(2);
        } else {
            g2.setColor(Color.white);
        }
        //g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30)); già impostato
        length = getLength(exitGame, g2);
        g2.drawString(exitGame, gp.getScreenWidth() / 2 - length / 2, gp.getScreenHeight() - (int) (gp.getTileSize() * 1.5));

        /*for(int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(g2);
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        menu(code, 2);

        if(code == KeyEvent.VK_ENTER) {
            if (gp.getCommandNum() == 0) {

                if(gp.getLevel() == gamePanel.level1) {
                    gp.getDb().storeDataBase(2,"LEVEL 2", gp.getUser());
                    gp.setLevel(gamePanel.level2); //AGGIORNARE DATABASE

                }
                else if(gp.getLevel() == gamePanel.level2) {
                    gp.getDb().storeDataBase(3,"LEVEL 3", gp.getUser());
                    gp.setLevel((gamePanel.level3)); //AGGIORNARE DATABASE
                }
                else if(gp.getLevel() == gamePanel.level3) {
                    gp.getDb().storeDataBase(1,"LEVEL 1", gp.getUser());
                    gp.setLevel(gamePanel.level1); //se abbiamo finito il livello 3 ripartiamo da capo E
                    // AGGIORNIAMO IL DATABASE
                }
                gp.getTitle().updateNames();
                gp.getTile().startTileManager(); //qui setta la mappa, metodo a parte da invocare solo se la
                // mappa si resetta
                gp.reSetUpGame(); //qui setta anche gli oggetti in base al llivello
                gp.setGameState(gp.getPlayState());
            }
            else if (gp.getCommandNum() == 1) {
                gp.setGameState(gp.getDataState());
            }
            else if (gp.getCommandNum() == 2) {
                gp.getDb().closeDataBase();
                System.exit(0);
            }
            gp.setCommandNum(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }
}
