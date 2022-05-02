// This script is extremely scuffed. Use with caution.
// It also requires you to remove a LOT of trailing commas.

import java.util.*;
import java.io.*;

public class TsvToJson {
    private static Scanner tsvFile;
    private static FileWriter writer;
    public static void main(String[] args) {
        try {
            tsvFile = new Scanner(new File("../src/data/main-dict-from-sheets.tsv")).useDelimiter("\t");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            writer = new FileWriter("./temp.json");
            writer.write("[\n");
            do {
                String[] currentEntry = tsvFile.nextLine().split("\t", 0);
                writer.write("  {\n    \"word\": \"" + currentEntry[0] + "\",\n    \"meanings\": [\n"); 
                for (int i = 1; i <= 3; i++) {
                    if (i >= currentEntry.length || currentEntry[i].isEmpty()) continue;
                    writer.write(addSpacing("[", 3) + "\n" + addSpacing("\"" + partOfSpeech(i), 4) + "\",\n" + addSpacing("\"" + currentEntry[i], 4) + "\"\n      ]" + ((i < 3) ? ",\n" : ""));
                }
                writer.write("\n    ]\n  },\n");
            } while (tsvFile.hasNextLine());
            writer.write("]");
            System.out.println("ilo li pini pona.");
        } catch (IOException e) {
            System.out.println("pakala li lon.");
            e.printStackTrace();
        }
    }
    private static String addSpacing(String str, int amount) {
        if (amount <= 0) return str;
        String spaces = "  ";
        for (int j = 0; j < amount - 1; j++) spaces += "  ";
        return spaces + str;
    }
    private static String partOfSpeech(int index) {
        return switch (index) {
            case 1 -> "n";
            case 2 -> "verb";
            case 3 -> "mod";
            default -> "ERROR";
        };
    }
}
