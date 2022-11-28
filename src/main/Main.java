package main;

import javax.swing.*;

public class Main {

    //COSE DA FARE //IN TEORIA COSA PIù IMPORTANTE GRAVITà E SISTEMA DI GIOCO FATTO A METà UNIMO PROBLEMA GRAVITà MA
    // ARGINATO
    //implementare a modo gli object video 7 //FATTO
    //extra tile screen video da finire //FATTO
    //proiettili //FATTO
    //option menu in pausa //FATTO
    //switch mappe
    //database save load e selezione utente
    //interfaccia tra menu iniziale e livello (selezione 3 livelli)
    //
    //FINE COSE DA FARE

    //VIEDO EVENT SALTATO
    //VIDEO ENTITYLIST TUTTO SOTTO AD ENTITY COMPRESI GLI OGGETTI
    public static JFrame window;

    //ImageIcon image = new ImageIcon("/icon/image.jpg");

    public static void main(String[] args) { //telecamera fa schifo ai lati

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); //no ridimensionamento
        window.setTitle("SUPER ALIEN PIXEL");
        window.setUndecorated(true);

        gamePanel Panel = new gamePanel();
        window.add(Panel); //aggiunge il panel alla finestra
        window.pack(); // incrementa le performances
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Panel.setupGame(); //così genera prima l'immagine su cui disegnare e poi l'immagine a schermo
        Panel.startGameThread(); //qui inizializza il thread (loop principale)

    }
}
