package test.decathlon.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import test.decathlon.dto.CalcResult;
import test.decathlon.dto.EventResult;
import test.decathlon.enums.Event;
import test.decathlon.service.CalculateService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final CalculateService calculateService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                List<EventResult> eventResults = read(reader);
                List<CalcResult> calcResults = calculateService.calc(eventResults);

                model.addAttribute("calcResults", calcResults);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }

    public List<EventResult> read(Reader inputReader) throws IOException, CsvValidationException {
        List<EventResult> eventResults = new ArrayList<>();
        try (CSVReader reader = new CSVReader(inputReader)) {
            String[] strings ;
            while ((strings = reader.readNext()) != null) {
                String athleteName = strings[0];
                Map<Event, Float> values = readValues(strings);

                EventResult eventResult = new EventResult();
                eventResult.setAthleteName(athleteName);
                eventResult.setValues(values);
                eventResults.add(eventResult);
            }
        }
        return eventResults;
    }

    private Map<Event, Float> readValues(String[] strings) {
        Map<Event, Float> values = new LinkedHashMap<>();
        for (Event event : Event.values()) {
            Float eventValue = Float.valueOf(strings[event.index]);
            values.put(event, eventValue);
        }
        return values;
    }
}