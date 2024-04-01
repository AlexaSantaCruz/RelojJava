package com.proyecto.graficas;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BaseReloj extends JPanel {

    private BufferedImage buffer;
    private BufferedImage relojImage; // Variable para almacenar la imagen del reloj
    Manecillas manecilla;

    public BaseReloj() {
        setBackground(Color.gray);
        buffer = new BufferedImage(900, 900, BufferedImage.TYPE_INT_ARGB); // Crear el buffer

        try {
            // Carga la imagen del reloj desde un archivo
            relojImage = ImageIO.read(new File("com/proyecto/graficas/reloj.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        manecilla = new Manecillas();
        add(manecilla);
        paint(getGraphics());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar en el buffer
        Graphics2D g2d = (Graphics2D) buffer.getGraphics();

        // Dibuja el borde del reloj (óvalo negro)
        g2d.setColor(Color.BLACK);
        g2d.fillOval(30, 30, 800, 800);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(80, 80, 700, 700);
        if (relojImage != null) {
            g2d.drawImage(relojImage, 80, 80, 700, 700, null);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 36));
        // Dibuja el nombre en un arco pequeño en la parte inferior del círculo negro
        String nombre = "Reloj :D";

        double angleStart = 1.5;
        double angleTotal = 0.5;

        // Dibuja cada letra del nombre en el arco
        for (int i = 0; i < nombre.length(); i++) {
            char letter = nombre.charAt(i);
            double angle = (angleStart + (double) i / (nombre.length() - 1) * angleTotal) + 2.90;
            int x = (int) (430 + Math.cos(angle) * 400); /*primer 430 posiciona, el 400 es como "se curvea"*/
            int y = (int) (470 + Math.sin(angle) * 400); /*Lo mismo de arriba*/
            g2d.drawString(String.valueOf(letter), x, y);
        }

        // Dibujar el buffer en la pantalla
        g.drawImage(buffer, 0, 0, null);
    }
}
