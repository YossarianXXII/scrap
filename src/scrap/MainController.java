/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrap;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import siteObjects.ESANewsArticle;
import siteObjects.SiteHandling;

/**
 *
 * @author Yoss
 */
public class MainController implements Initializable {
    List<ESANewsArticle> articleList = new ArrayList<>();
    
    @FXML GridPane gridPane;
    private final int gridRowCount = 2;
    private final int gridColCount = 3;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        articleList = SiteHandling.parseMainSite();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for(int row = 0; row<gridRowCount; row++){
            for(int col = 0; col<gridColCount; col++){                               
                WebView w = new WebView();
                w.getStyleClass().add("webView");

                w.setMaxHeight(gridPane.getPrefHeight()/gridRowCount);
                w.setMaxWidth(gridPane.getPrefWidth()/gridColCount);  
                
                gridPane.add(w, col, row);
            }
        }
        int i=0;
        for(ESANewsArticle a: articleList){ 
            if(gridPane.getChildren().size()<=i) break;
            ((WebView)gridPane.getChildren().get(i)).getEngine().loadContent(a.html);
            i++;
        }
        
    }    
    
    
    
    // Gets site ready to parse (in this case devoid of CSS&JavaScript)
   

}
