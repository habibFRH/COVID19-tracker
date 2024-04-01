package com.example.demo;


import java.io.IOException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired annotation
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    // Inject CoronaService using @Autowired annotation
    @Autowired
    CoronaService coronaService;

    @GetMapping("/")
    public String root(Model model) throws IOException {
        model.addAttribute("test1", "Hello user");
        coronaService.populateDatabase();
        return "MainTemplate";
    }

    @GetMapping("/r2")
    public String root2(Model model) throws IOException {
        model.addAttribute("test1", "Hello user");
        
        model.addAttribute("coronaData",coronaService.findAll());
        return "MainTemplate";
    }
}
