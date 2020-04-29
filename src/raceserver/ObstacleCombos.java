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
            "590", "455", "false"},

            {"500", "455", "true",
            "575", "415", "true",
            "650", "375", "true",
            "725", "335", "true",
            "755", "335", "true",
            "785", "335", "true",
            "815", "335", "true",
            "845", "335", "true",
            "765", "425", "true",
            "765", "455", "false",
            "900", "425", "true",
            "900", "455", "false"},

            {"500", "455", "true",
            "500","425", "true",
            "575", "455", "true",
            "575", "425", "true",
            "575", "395", "true",
            "575", "365", "true",
            "620", "455", "false"},

            {"500", "455", "true",
            "530", "455", "false",
            "560", "455", "false",
            "590", "455", "true",
            "590", "425", "true",
            "665", "455", "true"},

            {"500", "455", "true",
            "530", "455", "false",
            "560", "455", "false",
            "590", "455", "true",
            "590", "425", "true",
            "665", "395", "false"},

            {"500", "455", "true",
            "500", "425", "true",
            "560", "455", "true",
            "640", "455", "true",
            "600", "455", "false",
            "700", "455", "true",
            "700", "425", "true"},

            {"500", "455", "false",
            "500", "425", "true",
            "550", "455", "false"},

            {"500", "425", "true",
            "530", "445", "false",
            "560", "395", "true"},

            {"500", "470", "false",
            "530", "455", "true",
            "560", "470", "false"},

            {"500", "425", "true",
            "560", "455", "true",
            "620", "395", "true",
            "680", "455", "false",
            "680", "425", "true"},

            {"500", "425", "true",
            "560", "395", "true",
            "590", "455", "false"},

            {"500", "425", "true",
            "575", "425", "true",
            "650", "425", "true",
            "725", "425", "true",
            "800", "425", "true",
            "500", "470", "false",
            "530", "470", "false",
            "560", "470", "false",
            "590", "470", "false",
            "620", "470", "false",
            "650", "470", "false",
            "680", "470", "false",
            "710", "470", "false",
            "740", "470", "false",
            "770", "470", "false",
            "800", "470", "false"},

            {"500", "455", "true",
            "575", "425", "true",
            "650", "455", "true",
            "725", "425", "true",
            "800", "455", "true"},

            {"500", "475", "true",
            "575", "455", "true",
            "650", "425", "true",
            "720", "395", "true",
            "760", "475", "false"}

    };
}
