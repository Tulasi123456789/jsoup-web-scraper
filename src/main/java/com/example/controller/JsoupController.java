package com.example.controller;

import com.example.service.JsoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("jsoup/")
public class JsoupController {

    @Autowired
    private JsoupService jsoupService;

    // Endpoint: /scrape?url=https://www.shiksha.com
    @GetMapping("/scrape")
    public List<Map> scrapeWebsite(@RequestParam String url) {
        return jsoupService.scrapeWebsite(url);
    }

}
