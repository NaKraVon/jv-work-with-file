package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String SUPPLY_NAME = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] linePart;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();

            while (value != null) {
                linePart = value.split(",");
                if (linePart[0].equals(SUPPLY_NAME)) {
                    supplyAmount += Integer.parseInt(linePart[1]);
                } else {
                    buyAmount += Integer.parseInt(linePart[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write("supply," + supplyAmount);
            writer.newLine();
            writer.write("buy," + buyAmount);
            writer.newLine();
            writer.write("result," + (supplyAmount - buyAmount));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write into file",e);
        }

    }
}
