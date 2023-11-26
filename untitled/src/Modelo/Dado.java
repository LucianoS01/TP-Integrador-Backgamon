package Modelo;

import java.util.Random;

public class Dado {
    public int tirar(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}

