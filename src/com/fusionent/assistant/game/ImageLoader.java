package com.fusionent.assistant.game;

import java.awt.Image;
import java.io.File;

import java.util.Vector;

import javax.imageio.ImageIO;

import java.nio.file.*;

public class ImageLoader {
    public ImageLoader() {
        
    }
    
    public Vector<Image> loadImages (String filepath) {
        
        Vector<Image> returnVector = new Vector<Image>();
        
        Path dir = FileSystems.getDefault().getPath(filepath);
        File loadFile;
        Image img; 
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry: stream) {
                loadFile = entry.toFile();
                img = ImageIO.read(loadFile);
                returnVector.add(img);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return returnVector;
        
    }

}
