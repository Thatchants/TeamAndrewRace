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
            "560", "455", "false",
            "560", "425", "true",
            "620", "425", "false",
            "620", "360", "true",
            "680", "360", "true",
            "650", "360", "false"},

            {"500", "450", "true",
            "575", "410", "true",
            "650", "365", "true",
            "725", "320", "true",
            "800", "365", "true",
            "800", "455", "false",
            "800", "425", "false",
            "800", "395", "false"},

            {"500", "455", "false",
            "500", "425", "true"},

            {"500","455","false",
            "500", "425", "true",
            "560", "440", "true",
            "590", "440", "false"},

            {"500", "455", "true",
            "575", "455", "true",
            "650", "395", "true",
            "650", "455", "false",
            "650", "425", "false",
            "725", "365", "false"},

            {"500", "425", "false",
            "560", "455", "true"},

            {"500", "455", "true",
            "560", "455", "true",
            "620", "455", "true",
            "530", "455", "false",
            "590", "455", "false"}
    };
}
