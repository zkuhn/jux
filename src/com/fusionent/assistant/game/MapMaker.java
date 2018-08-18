package com.fusionent.assistant.game;

import java.awt.Point;
import java.util.Vector;

public class MapMaker {

    public static Vector<Wall> randomWalls(){
        int WALL_SPACE=60; 
        Vector<Wall> walls = new Vector<Wall>();
        for (int i = 0; i < 400; i+= WALL_SPACE){
            for (int j = 0; j < 400; j+= WALL_SPACE) {
                double rand = Math.random();
                int horz = 0;
                int vert = 0;
                
                if (rand > .50) {
                    double rand_dir = Math.random();
                    if(rand_dir >.5){
                        horz = WALL_SPACE;
                    } else {
                        vert = WALL_SPACE;
                    }
                    Wall w = new Wall(new Point(i,j), new Point(i+horz,j+vert));
                    walls.add(w);
                }
            }
        } 
        return walls;
    }
}
