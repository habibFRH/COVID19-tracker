package com.example.demo;

import org.springframework.stereotype.Service;

@Service
class CoronaService {
    Coronarepository coronarepository;

    public CoronaService(Coronarepository coronarepository) {
        this.coronarepository = coronarepository;
    }

    @SuppressWarnings("null")
    public void save(Corona corona){
        coronarepository.save(corona);
    } 

    public void populateDatabase(){
        
    }
}