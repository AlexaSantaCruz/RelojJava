package com.proyecto.graficas;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Manecillas extends JComponent implements Runnable {

    private int segundos;
    private int minutos;
    private int horas;
    private boolean isRunning = true;

    public Manecillas() {
        setPreferredSize(new Dimension(900, 900));

        // Obtener la hora actual del sistema
        Calendar cal = Calendar.getInstance();
        segundos = cal.get(Calendar.SECOND);
        minutos = cal.get(Calendar.MINUTE);
        horas = cal.get(Calendar.HOUR);

        // Inicia un hilo para actualizar la posición de la manecilla cada segundo
        Thread thread = new Thread(this);
        thread.start(); // Inicia el hilo
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1000); // Espera 1 segundo
                segundos = (segundos + 1) % 60;
                if (segundos == 0) {
                    minutos = (minutos + 1) % 60;
                    if (minutos == 0) {
                        horas = (horas + 1) % 12;
                    }
                }
                repaint(); // Vuelve a pintar el componente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Establece el color rojo para la línea de los segundos
        g2d.setColor(Color.RED);

        // Calcula la posición de la manecilla de segundos
        int segundosX = 430 + (int) (380 * Math.cos(Math.toRadians(segundos * 6 - 90))); // Calcula la posición X de la manecilla de segundos
        int segundosY = 430 + (int) (380 * Math.sin(Math.toRadians(segundos * 6 - 90))); // Calcula la posición Y de la manecilla de segundos

        // Dibuja la línea de la manecilla de segundos
        g2d.drawLine(430, 430, segundosX, segundosY);
        // Establece el grosor de la línea azul para la manecilla de minutos
        g2d.setStroke(new BasicStroke(5)); // Grosor de 3 píxeles
        g2d.setColor(Color.BLUE);

        // Calcula la posición de la manecilla de minutos
        int minutosX = 430 + (int) (350 * Math.cos(Math.toRadians(minutos * 6 - 90))); // Calcula la posición X de la manecilla de minutos
        int minutosY = 430 + (int) (350 * Math.sin(Math.toRadians(minutos * 6 - 90))); // Calcula la posición Y de la manecilla de minutos

        // Dibuja la línea de la manecilla de minutos
        g2d.drawLine(430, 430, minutosX, minutosY);

        // Establece el grosor de la línea verde para la manecilla de horas
        g2d.setStroke(new BasicStroke(10)); // Grosor de 5 píxeles
        g2d.setColor(Color.GREEN);

        // Calcula la posición de la manecilla de horas
        int horasX = 430 + (int) (300 * Math.cos(Math.toRadians(horas * 30 - 90))); // Calcula la posición X de la manecilla de horas
        int horasY = 430 + (int) (300 * Math.sin(Math.toRadians(horas * 30 - 90))); // Calcula la posición Y de la manecilla de horas

        // Dibuja la línea de la manecilla de horas
        g2d.drawLine(435, 425, horasX, horasY);

        // Restablece el grosor de la línea a su valor predeterminado
        g2d.setStroke(new BasicStroke(1)); // Grosor predeterminado
        g2d.drawLine(430, 430, horasX, horasY);
    }

    public void stop() {
        isRunning = false; // Detiene el hilo cuando sea necesario
    }
}
