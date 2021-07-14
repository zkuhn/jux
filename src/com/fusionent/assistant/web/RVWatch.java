package com.fusionent.assistant.web;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;



public class RVWatch {
    static String baseSearch = "https://sandiego.craigslist.org/search/rva?query=diesel&min_price=15000&max_price=45000&min_auto_year=1999";
    
    public void crawlRvs(){
        
        try {
            System.out.println("here");
            Document doc = Jsoup.connect(baseSearch).get();
            System.out.println(doc);
            Elements resultLinks = doc.select(".result-title");
            System.out.println(resultLinks);
            for (Element link : resultLinks) {
                String page = link.absUrl("href");
                System.out.println(page);
                
            }
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args){
        RVWatch mine = new RVWatch();
        mine.crawlRvs();
    }
}
