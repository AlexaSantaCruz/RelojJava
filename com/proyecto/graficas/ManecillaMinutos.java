package com.proyecto.graficas;

import javax.swing.*;
import java.awt.*;

public class ManecillaMinutos extends JComponent implements Runnable {
    private int minutos = 0;
    private boolean isRunning = true;

    public ManecillaMinutos() {
        setPreferredSize(new Dimension(900, 900));

        // Inicia un hilo para actualizar la posición de la manecilla cada segundo
        Thread thread = new Thread(this);
        thread.start(); // Inicia el hilo
        paint(getGraphics());
    }

    @Override
    public void run() {
        if (EventQueue.isDispatchThread()) {
            repaint();
        }
        do {
            if (this != null) {
                minutos = (minutos + 1) % 60;
                EventQueue.invokeLater(() -> repaint());
            }
            try {
                Thread.sleep(60000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (isRunning);

        /*
         * while (isRunning) {
         * try {
         * Thread.sleep(60000); // Espera 1 minuto
         * minutos = (minutos + 1) % 60;
         * repaint(); // Vuelve a pintar el componente
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * }
         * }
         */

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);

        // Calcula la posición de la manecilla de segundos
        int minutosX = 430 + (int) (380 * Math.cos(Math.toRadians(minutos * 6 - 90)));
        int minutosY = 430 + (int) (380 * Math.sin(Math.toRadians(minutos * 6 - 90)));

        // Dibuja la línea de la manecilla de segundos
        g2d.drawLine(430, 430, minutosX, minutosY);
    }

    public void stop() {
        isRunning = false; // Detiene el hilo cuando sea necesario
    }
}
