package main;

import Entity.Monster;
import Objects.Coin;
import Objects.Door;

public class objectSetter { //NON C'ENTRA CON IL RESTO DI OBJECT METTE SOLO TUTTI GLI OGGETTI SULLA MAPPA
    gamePanel gp;
    public objectSetter(gamePanel gp) {
        this.gp = gp;
    }

    public void setObjectLevel1() {

        gp.getObj()[0] = new Coin(gp);
        gp.getObj()[0].setWorldX(gp.getTileSize() * 5);
        gp.getObj()[0].setWorldY(gp.getTileSize() * 12);

        gp.getObj()[1] = new Coin(gp);
        gp.getObj()[1].setWorldX(gp.getTileSize() * 16);
        gp.getObj()[1].setWorldY(gp.getTileSize() * 15);

        gp.getObj()[2] = new Coin(gp);
        gp.getObj()[2].setWorldX(gp.getTileSize() * 29);
        gp.getObj()[2].setWorldY(gp.getTileSize() * 13);

        gp.getObj()[3] = new Coin(gp);
        gp.getObj()[3].setWorldX(gp.getTileSize() * 33);
        gp.getObj()[3].setWorldY(gp.getTileSize() * 16);

        gp.getObj()[4] = new Coin(gp);
        gp.getObj()[4].setWorldX(gp.getTileSize() * 45);
        gp.getObj()[4].setWorldY(gp.getTileSize() * 15);

        gp.getObj()[5] = new Door(gp);                      //<---LA PORTA è QUA
        gp.getObj()[5].setWorldX(gp.getTileSize() * 47);    // PORTA
        gp.getObj()[5].setWorldY(gp.getTileSize() * 15);     // PORTA

    }
    public void setEntityLevel1() {

        gp.getMonsters()[0] = new Monster(gp);
        gp.getMonsters()[0].setWorldX(gp.getTileSize() * 12);
        gp.getMonsters()[0].setWorldY(gp.getTileSize() * 12);

        gp.getMonsters()[1] = new Monster(gp);
        gp.getMonsters()[1].setWorldX(gp.getTileSize() * 17);
        gp.getMonsters()[1].setWorldY(gp.getTileSize() * 15);

        gp.getMonsters()[2] = new Monster(gp);
        gp.getMonsters()[2].setWorldX(gp.getTileSize() * 23);
        gp.getMonsters()[2].setWorldY(gp.getTileSize() * 15);

        gp.getMonsters()[3] = new Monster(gp);
        gp.getMonsters()[3].setWorldX(gp.getTileSize() * 34);
        gp.getMonsters()[3].setWorldY(gp.getTileSize() * 16);

        gp.getMonsters()[4] = new Monster(gp);
        gp.getMonsters()[4].setWorldX(gp.getTileSize() * 42);
        gp.getMonsters()[4].setWorldY(gp.getTileSize() * 16);

    }

    public void setObjectLevel2() {

        gp.getObj()[0] = new Door(gp);
        gp.getObj()[0].setWorldX(gp.getTileSize() * 38);
        gp.getObj()[0].setWorldY(gp.getTileSize() * 7);


        gp.getObj()[1] = new Coin(gp);
        gp.getObj()[1].setWorldX(gp.getTileSize() * 21);
        gp.getObj()[1].setWorldY(gp.getTileSize() * 6);

        gp.getObj()[2] = new Coin(gp);
        gp.getObj()[2].setWorldX(gp.getTileSize() * 37);
        gp.getObj()[2].setWorldY(gp.getTileSize() * 13);

        gp.getObj()[3] = new Coin(gp);
        gp.getObj()[3].setWorldX(gp.getTileSize() * 35);
        gp.getObj()[3].setWorldY(gp.getTileSize() * 18);

        gp.getObj()[4] = new Coin(gp);
        gp.getObj()[4].setWorldX(gp.getTileSize() * 23);
        gp.getObj()[4].setWorldY(gp.getTileSize() * 11);

        gp.getObj()[5] = new Coin(gp);
        gp.getObj()[5].setWorldX(gp.getTileSize() * 12);
        gp.getObj()[5].setWorldY(gp.getTileSize() * 15);

    }

    public void setEntityLevel2() {
        gp.getMonsters()[0] = new Monster(gp);
        gp.getMonsters()[0].setWorldX(gp.getTileSize() * 21);
        gp.getMonsters()[0].setWorldY(gp.getTileSize() * 9);

        gp.getMonsters()[1] = new Monster(gp);
        gp.getMonsters()[1].setWorldX(gp.getTileSize() * 35);
        gp.getMonsters()[1].setWorldY(gp.getTileSize() * 9);

        gp.getMonsters()[2] = new Monster(gp);
        gp.getMonsters()[2].setWorldX(gp.getTileSize() * 35);
        gp.getMonsters()[2].setWorldY(gp.getTileSize() * 18);

        gp.getMonsters()[3] = new Monster(gp);
        gp.getMonsters()[3].setWorldX(gp.getTileSize() * 21);
        gp.getMonsters()[3].setWorldY(gp.getTileSize() * 15);

    }

    public void setObjectLevel3() {

        gp.getObj()[0] = new Coin(gp);
        gp.getObj()[0].setWorldX(gp.getTileSize() * 1);
        gp.getObj()[0].setWorldY(gp.getTileSize() * 6);

        gp.getObj()[1] = new Coin(gp);
        gp.getObj()[1].setWorldX(gp.getTileSize() * 25);
        gp.getObj()[1].setWorldY(gp.getTileSize() * 3);

        gp.getObj()[2] = new Coin(gp);
        gp.getObj()[2].setWorldX(gp.getTileSize() * 38);
        gp.getObj()[2].setWorldY(gp.getTileSize() * 5);

        gp.getObj()[3] = new Coin(gp);
        gp.getObj()[3].setWorldX(gp.getTileSize() * 22);
        gp.getObj()[3].setWorldY(gp.getTileSize() * 15);

        gp.getObj()[4] = new Coin(gp);
        gp.getObj()[4].setWorldX(gp.getTileSize() * 34);
        gp.getObj()[4].setWorldY(gp.getTileSize() * 15);

        gp.getObj()[5] = new Door(gp);
        gp.getObj()[5].setWorldX(gp.getTileSize() * 4);
        gp.getObj()[5].setWorldY(gp.getTileSize() * 12);

    }

    public void setEntityLevel3() {
        gp.getMonsters()[0] = new Monster(gp);
        gp.getMonsters()[0].setWorldX(gp.getTileSize() * 22);
        gp.getMonsters()[0].setWorldY(gp.getTileSize() * 9);

        gp.getMonsters()[1] = new Monster(gp);
        gp.getMonsters()[1].setWorldX(gp.getTileSize() * 35);
        gp.getMonsters()[1].setWorldY(gp.getTileSize() * 9);

        gp.getMonsters()[2] = new Monster(gp);
        gp.getMonsters()[2].setWorldX(gp.getTileSize() * 36);
        gp.getMonsters()[2].setWorldY(gp.getTileSize() * 15);

        gp.getMonsters()[3] = new Monster(gp);
        gp.getMonsters()[3].setWorldX(gp.getTileSize() * 31);
        gp.getMonsters()[3].setWorldY(gp.getTileSize() * 15);

        gp.getMonsters()[4] = new Monster(gp);
        gp.getMonsters()[4].setWorldX(gp.getTileSize() * 26);
        gp.getMonsters()[4].setWorldY(gp.getTileSize() * 15);

        gp.getMonsters()[5] = new Monster(gp);
        gp.getMonsters()[5].setWorldX(gp.getTileSize() * 21);
        gp.getMonsters()[5].setWorldY(gp.getTileSize() * 15);

        gp.getMonsters()[6] = new Monster(gp);
        gp.getMonsters()[6].setWorldX(gp.getTileSize() * 16);
        gp.getMonsters()[6].setWorldY(gp.getTileSize() * 15);
    }
}