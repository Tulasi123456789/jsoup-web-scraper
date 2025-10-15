package com.example.service;

import com.example.model.LinkInfo;
import com.example.model.PageInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class JsoupService {

    public List<LinkInfo> scrapeWebsite(String url) {
        List<LinkInfo> data = new ArrayList<>();
        int retries = 2; // Number of retries if timeout occurs

        for (int i = 0; i < retries; i++) {
            try {
                Document doc = Jsoup.connect(url)
                        .timeout(30000)                 // 30 seconds
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                        .referrer("http://www.google.com")
                        .followRedirects(true)
                        .get();

                Elements links = doc.select("a[href]");
                for (Element link : links) {
//                    Map<String, String> map = new HashMap<>();
//                    map.put("text", link.text());
//                    map.put("url", link.absUrl("href"));
//                    data.add(map);
                    data.add(new LinkInfo(link.text(), link.absUrl("href")));
                }

                break;

            } catch (IOException e) {
                System.out.println("Attempt " + (i + 1) + " failed: " + e.getMessage());
                if (i == retries - 1) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(3000); // wait 3 seconds before retry
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return data;
    }


    /**
     * Fetch a single page's content and its links.
     */
    public PageInfo fetchPage(String url) {
        List<LinkInfo> links = new ArrayList<>();
        String htmlContent = "";

        int retries = 2;

        for (int i = 0; i < retries; i++) {
            try {
                Document doc = Jsoup.connect(url)
                        .timeout(15000)  // 15 seconds
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                        .referrer("http://www.google.com")
                        .followRedirects(true)
                        .get();

                htmlContent = doc.html(); // entire HTML content

                Elements elements = doc.select("a[href]");
                for (Element link : elements) {
                    links.add(new LinkInfo(link.text(), link.absUrl("href")));
                }

                break; // success, exit retry

            } catch (IOException e) {
                System.out.println("Attempt " + (i + 1) + " failed for URL: " + url + " | " + e.getMessage());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return new PageInfo(url, htmlContent, links);
    }

}

