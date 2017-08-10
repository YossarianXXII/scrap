/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siteObjects;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Yoss
 */
public class ESANewsArticle {
    public String url;
    public String title;
    public String text;
    public String html;
    
    public ESANewsArticle(){
        html = "";
        text = "";
    }
    
    public void generateTitle(){
        
    }
     public void generateText(HtmlPage page){
        
        List<HtmlElement> articleSubElement = page.getByXPath("//div[@id='article']/div[@class='section']/p");
        String result = "";
        
        StringBuilder textBuild = new StringBuilder(result);
        StringBuilder htmlBuild = new StringBuilder(html);
        LetterCount letterList = new LetterCount();
                
        for(HtmlElement item : articleSubElement){
            textBuild.append(item.asText());
        }
        text = textBuild.toString();        
        result = text.toLowerCase();     
                 
        /* Counts occurence of distinct characters - for now only a-z, A-Z,
             numerical can be added if needed.*/
        Set<Character> distinctChars = result.chars().filter((value) -> {
            return value >= 97 && value <= 122 || value >=65 && value <=90;
        }).mapToObj(i -> (char)i).collect(Collectors.toSet());
        for(Character c : distinctChars)
            letterList.addLetter(c, StringUtils.countMatches(result, c));         
          
        letterList.countSize();
        
        /* Generate html version of text */
        for(char c: text.toCharArray()){
            if(letterList.contains(Character.toLowerCase(c))) 
                htmlBuild
                        .append("<span style=\"font-size:")
                        .append(letterList.getLSize(Character.toLowerCase(c)))
                        .append("px\">")
                        .append(c)
                        .append("</span>");
            else{
                htmlBuild
                        .append("<span style=\"font-size:")
                        .append((int)((letterList.maxSize+letterList.minSize)/2))
                        .append("px\">")
                        .append(c)  
                        .append("</span>");
            }
        }
        html = htmlBuild.toString();
                 
    }
    
}
