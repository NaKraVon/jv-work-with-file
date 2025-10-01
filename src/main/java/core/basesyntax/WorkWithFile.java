package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String SUPPLY_NAME = "supply";
    static final String BUY_NAME = "buy";
    static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String operation;
        int amountStr;
        String[] linePart;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();

            while (value != null) {
                linePart = value.split(COMMA);
                if (linePart.length == 2) {
                    operation = linePart[0].trim();
                    amountStr = Integer.parseInt(linePart[1].trim());
                    if (operation.equals(SUPPLY_NAME)) {
                        supplyAmount += amountStr;
                    } else if (operation.equals(BUY_NAME)) {
                        buyAmount += amountStr;
                    } else {
                        throw new RuntimeException("Operation does not exist");
                    }
                } else {
                    throw new RuntimeException("Line incorrect");
                }

                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write("supply," + supplyAmount);
            writer.newLine();
            writer.write("buy," + buyAmount);
            writer.newLine();
            writer.write("result," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write into file",e);
        }

    }
}
