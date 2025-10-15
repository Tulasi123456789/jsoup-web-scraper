package com.example.controller;

import com.example.model.LinkInfo;
import com.example.model.PageInfo;
import com.example.service.JsoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("jsoup/")
public class JsoupController {

    @Autowired
    private JsoupService jsoupService;

    @GetMapping("/scrape")
    public List<LinkInfo> scrapeWebsite(@RequestParam String url) {
        return jsoupService.scrapeWebsite(url);
    }

    /**
     * Endpoint to fetch page content and links.
     */
    @GetMapping("/fetchPage")
    public PageInfo fetchPage(@RequestParam String url) {
        return jsoupService.fetchPage(url);
    }

}

