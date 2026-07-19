package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvReader {
    public static List<String[]> readCsv(String pathFile) throws IOException {
        List<String[]> dataCsv = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader((pathFile)));
        try(reader){
            String line;
            boolean isHeader = true;
            while((line = reader.readLine()) != null){
                if(isHeader){
                    isHeader = false;
                    continue;
                }

                if(line.trim().isEmpty()){
                    continue;
                }

                String[] values = line.split(",");
                dataCsv.add(values);
            }

        }

        return dataCsv;
    }

    // chuan hoa data theo testng
    public static Object[][] toDataProviderArray(List<String[]> rows){
        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i < rows.size(); i++){
            data[i] = rows.get(i);
        }
        return data;
    }
}
