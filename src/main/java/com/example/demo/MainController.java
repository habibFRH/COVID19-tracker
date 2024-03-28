package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Coronarepository;
import com.opencsv.CSVReader;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class MainController {

    @Autowired
    Coronarepository coronarepository;

    @GetMapping("/")
    public String root(Model model) throws FileNotFoundException {
        model.addAttribute("test1", "Hello user");

        String csvFile = "C:/Users/sidou/OneDrive/Bureau/web-proj/demo/06-03-2020.csv";
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            int i = 0;
            while ((line = reader.readNext()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                Corona corona = new Corona();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                corona.setLastUpdates(LocalDateTime.parse(line[4],formatter));
                corona.setConfirmed(Long.valueOf(line[7]));
                corona.setRecovered(Long.valueOf(line[9]));
                corona.setActive(Long.valueOf(line[10]));
                corona.setCombinedkey(line[11]);
                log.info(corona.toString());
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

        return "MainTemplate";
    }
}
