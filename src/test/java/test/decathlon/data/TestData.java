package test.decathlon.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import test.decathlon.dto.EventResult;
import test.decathlon.enums.Event;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestData {
    public static String load(String fileName) {

        try (InputStream inputStream = TestData.class.getResourceAsStream(fileName)) {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException("error loading test.csv data", e);
        }
    }

    public void generateCSV() throws IOException {
        String jsonBody = TestData.load("success.json");

        List<EventResult> eventResults = new ObjectMapper().readValue(jsonBody, new TypeReference<List<EventResult>>() {
        });

        try (CSVWriter writer = new CSVWriter(new FileWriter("test.csv"))) {
            for (EventResult eventResult : eventResults) {

                String[] strings = new String[11];
                strings[0] = eventResult.getAthleteName();
                for (Event event : eventResult.getValues().keySet()) {
                    strings[event.index] = String.valueOf(eventResult.getValues().get(event));
                }
                writer.writeNext(strings);
            }
        }
    }
}
