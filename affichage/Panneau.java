package com.company;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

//crée un panneau et on peut donner une image de fond en parametre

public class Panneau extends JPanel {



    private Image image;

    public Panneau(Image image) {
        super();
        setImage(image);
    }

    public Panneau(String path) throws IOException {
        super();
        setImage(path);
    }


    public void setImage(Image image) {
        this.image = image;
        repaint();
    }


    public void setImage(String path) throws IOException {
        try {
            this.image = ImageIO.read(new File(path));
            repaint();
        }
        catch (IOException e) {
            throw new IOException(path+" introuvable", e);
        }
    }


    public Image getImage() {
        return image;
    }

    @Override
    public void paintComponent(Graphics g){
        if(image!=null){
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    }}
