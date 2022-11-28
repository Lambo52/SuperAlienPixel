package Objects;

import main.gamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Life extends superObject{

    public Life(gamePanel gp) {
        super(gp);
        lifeLoader();
    }

    public void lifeLoader() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Life/Heart.png"));
            image = tileScaled.scaleImage(image, gp.getTileSize(), gp.getTileSize());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //il getter di image è nel superobject, nei coin c'è il getter dato che image della superclasse è variabile tra
    // le 4 immagini possibili

}
