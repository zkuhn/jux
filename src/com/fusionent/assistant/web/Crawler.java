package com.fusionent.assistant.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class Crawler {
    

    public void crawlUrl(URL url){
        Fetcher f = new Fetcher();
        
        String page = f.getWebpageRaw(url);
        
        Vector<URL> links = this.extractLinks(page);
        for(URL link : links) {
            System.out.println("Valid URL : - " + link);
        }
        
    }
    
    public Vector<URL> extractLinks(String page){
        
        Vector<URL> links = new Vector<URL>();
        int k = 0;
        int index, hrefStart, anchorEnd;
        String startString, urlContent;
        while((index = page.indexOf("<a ",k)) != -1) {
          
            startString = page.substring(index);
            hrefStart = startString.indexOf("href");
            anchorEnd = startString.indexOf("\"", hrefStart + 6);
            urlContent = startString.substring(hrefStart + 6, anchorEnd);
            k = index + anchorEnd;  //move the pointer up to the next anchor tag found
            try {
                links.add(new URL(urlContent));
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                
                System.out.println("Malformed URl: - " + urlContent);
                /*
                 * This could be a relative URL:
                 * 
                 * Malformed URl: - /search/hhh
                    Malformed URl: - /search/apa
                    Malformed URl: - /search/swp
                    Malformed URl: - /search/hsw
                    Malformed URl: - /search/off
                    
                    Malformed URl: - //sandiego.craigslist.org/d/events-classes/search/eee?sale_date=2017-10-06
                    Malformed URl: - //sandiego.craigslist.org/d/events-classes/search/eee?sale_date=2017-10-07
                    Malformed URl: - //sandiego.craigslist.org/d/events-classes/search/eee?sale_date=2017-10-08
                 */
                
                //e.printStackTrace();
            }
        }
        
        
        return links;
        
    }

}
