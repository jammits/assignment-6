package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class FileService {

    public List<TeslaCar> teslaSales(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<TeslaCar> sales = new ArrayList<>();
            String line;
            //Skip header of CSVs
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] salesValues = line.split(",");
                TeslaCar car = new TeslaCar(YearMonth.parse(salesValues[0], DateTimeFormatter.ofPattern("MMM-yy")), TeslaModel.valueOf(fileName.substring(0,6).toUpperCase()), Integer.parseInt(salesValues[1]));
                sales.add(car);
            }
            return sales;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
