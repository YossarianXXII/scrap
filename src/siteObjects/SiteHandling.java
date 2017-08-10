/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siteObjects;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import scrap.MainController;

/**
 *
 * @author Yoss
 */
public class SiteHandling {
     public static HtmlPage loadHtmlPage(String url){
        WebClient client = new WebClient();          
        String targetURL =  url;
        HtmlPage page = null;

        client.getOptions().setCssEnabled(false);  
        client.getOptions().setJavaScriptEnabled(false); 
        
        try {
            page = client.getPage(targetURL);
        } catch (IOException | FailingHttpStatusCodeException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return page;
    }
     
     public static List<ESANewsArticle> parseMainSite(){        
        String targetURL = "http://www.esa.int";    
        List<ESANewsArticle> articles = new ArrayList<>();
        HtmlPage mainSite = SiteHandling.loadHtmlPage(targetURL);
        List<HtmlElement> news = mainSite.getByXPath("//div[@id='latest_news']//div[@class='ln_item']");
        
        for(HtmlElement e : news){
            ESANewsArticle article = new ESANewsArticle();
            
            HtmlElement aElement = e.getElementsByTagName("a").get(0);             
            article.url = aElement.getAttribute("href");
            article.generateText(SiteHandling.loadHtmlPage("http://www.esa.int/" + article.url));
            articles.add(article);            
        }   
        return articles;
    }
}
