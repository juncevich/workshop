package com.example.syncload;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadController {
    long defaultSleep = 5000;

    @GetMapping("/test")
    public String getTest(){
        return "This is the text";
    }

    @GetMapping("/testTime")
    public String getTestTime() throws InterruptedException {
        Thread.sleep(defaultSleep);
        return "This is the timed text";
    }

    @GetMapping("/increase")
    public String increase() {
        defaultSleep += 1000;
        return "Increased to: " + defaultSleep;
    }

    @GetMapping("/decrease")
    public String decrease() {
        if (defaultSleep <=0){
            return "Decreased to: " + defaultSleep;
        }
        defaultSleep -= 1000;
        return "Decreased to: " + defaultSleep;
    }
}
