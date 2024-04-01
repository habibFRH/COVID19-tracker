package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired; // Import Autowired annotation
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.opencsv.CSVReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

    // Inject CoronaService using @Autowired annotation
    @Autowired
    CoronaService coronaService;

    @GetMapping("/")
    public String root(Model model) throws IOException {
        model.addAttribute("test1", "Hello user");

        URL url = new URL(
                "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/06-03-2020.csv");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int responsecode = huc.getResponseCode();

        log.info(String.valueOf(huc.getResponseCode()));
        if (responsecode == 200) {
            log.info("..... Successfully connected to Github");
            //String csvFile = "C:/Users/sidou/OneDrive/Bureau/web-proj/demo/06-03-2020.csv";

        CSVReader reader = null;
        try {
            //reader = new CSVReader(new FileReader(csvFile));
            BufferedReader input = new BufferedReader(new InputStreamReader(huc.getInputStream()),8192);
            reader = new CSVReader((input));
            String[] line;
            int i = 0;
            while ((line = reader.readNext()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                Corona corona = new Corona();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                corona.setLastUpdates(LocalDateTime.parse(line[4], formatter));
                corona.setConfirmed(Long.valueOf(line[7]));
                corona.setRecovered(Long.valueOf(line[9]));
                corona.setActive(Long.valueOf(line[10]));
                corona.setCombinedkey(line[11]);
                log.info(corona.toString());
                coronaService.save(corona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        }

        

        return "MainTemplate";
    }
}
