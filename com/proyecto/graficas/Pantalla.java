package com.proyecto.graficas;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Pantalla extends JFrame{

    private BaseReloj baseReloj;


    public Pantalla(){
        setTitle("Reloj");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminar la ejecución cuando se cierra la ventana
        setResizable(false);
        
               try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("com/proyecto/graficas/sonido.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
         System.out.println("Error al reproducir el sonido.");
       }
        baseReloj=new BaseReloj();
        add(baseReloj);
        setVisible(true);

    }
    
    public static void main(String[] args) {
        // Crear una instancia de la ventana principal
        Pantalla pantalla = new Pantalla();
    }

}
