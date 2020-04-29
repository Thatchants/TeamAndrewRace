package raceserver;

import java.util.Random;

public class ObstacleCombos {

    private static Random r = new Random();

    public static String[] randomCombo(){
        int index = r.nextInt((combos.length));
        return combos[index];
    }

    public static int comboSize(String[] combo){
        int end = 500;
        for(int i = 0; i < combo.length;i += 3){
            if(Integer.parseInt(combo[i]) + 25 > end){
                end = Integer.parseInt(combo[i]) + 25;
            }
        }
        return end - 500;
    }

    public static final String[][] combos = new String[][]{
            {"500", "455", "false",
            "500", "425", "true",
            "535", "425", "false"},

            {"500", "455", "false",
            "500", "425", "true"}
    };
}
