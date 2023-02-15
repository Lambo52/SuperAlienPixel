package Objects;

import main.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Door extends superObject{

    public Door(gamePanel gp) {
        super(gp);

        name = door;
        collision = true;
        solidArea.x = gp.getTileSize() / 4;
        solidArea.width = gp.getTileSize() / 2;
        solidAreaDefaultX = solidArea.x;
        doorLoader();
    }

    public void doorLoader() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Door/door.png"));
            image = tileScaled.scaleImage(image, gp.getTileSize(), gp.getTileSize());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void update() {
        super.update();
    }*/

    /*@Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }*/
}