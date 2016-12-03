 /**
  * TEI of Athens, Department of Informatics
  * Master of Science in Computing and Network Technologies
  * Distributed Web Applications, Task 2 Ενδεικτική λύση,
  * Subject: Ανάλυση κώδικα HTML ιστοσελίδας.
  * @author Michael Galliakis AM: CNT16003.
  * Ημερομηνία : 3/12/2016
  */
package kdt.task2.analhtml;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AnalysisHTMLFile {
    public enum ReloadStatus{
        ok
        ,err_url
        ,err_parse
        ,err_other
        ,not_defined_url
    }
    
    private Elements alSscripts ;
    private  Elements alCss  ;
    private int crowdOfLinks ;
    private Document doc ;
    private URL currURL ;
    private ReloadStatus relSta ;
    private JTextPane outputTextPane ;
    
    public AnalysisHTMLFile() {
        crowdOfLinks = 0 ;
        relSta = ReloadStatus.not_defined_url ;
    }
    
    public ReloadStatus reload()
    {
        if (currURL==null)
            relSta = ReloadStatus.not_defined_url ;
        else
            relSta = parseURL(currURL) ;
        return relSta ;
    }
    public ReloadStatus reload(String url)
    {
        try  {
            URL tmpURL = new URL(url) ;
            
            relSta =  parseURL(tmpURL) ;
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
            relSta = ReloadStatus.err_url ;
        }
        
        return relSta ;
    }
    
    private ReloadStatus parseURL(URL url)
    {
        try  {
            Document tmpDoc = Jsoup.parse(url,4000) ;
            
            int number = tmpDoc.select("a").size();
            Elements scripts = tmpDoc.select("script[src$=.js]") ;
            Elements css = tmpDoc.select("link[rel=stylesheet][type=text/css][href$=.css]") ;
            
            currURL = url ;
            doc = tmpDoc ;
            crowdOfLinks = number ;
            alCss = css ;
            alSscripts = scripts ;
            return ReloadStatus.ok ;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return ReloadStatus.err_parse ;
        }
    }
    public void setOutputTextPane(JTextPane tp)
    {
        outputTextPane = tp ;
    }
    public void clearOutputTextPane()
    {
        if (outputTextPane!=null)
            outputTextPane.setText("");
    }
    public void printAll()
    {        
        if (relSta==ReloadStatus.ok)
        {
            printMessage("# # # # # # # BEGIN # # # # # # # # # #");
            printMessage("Host: "+currURL.getHost()) ;            
            printMessage("@ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @");
            printCrowdOfLinks();
            printMessage("* * * * * * * * * * * * * * * *");
            printCSS();
            printMessage("* * * * * * * * * * * * * * * *");
            printScripts();
            printMessage("# # # # # # # # END # # # # # # # # # #");
        }
        else
            printMessage(getMessageRelSta(relSta));
    }
    
    public void printScripts()
    {
        if (relSta==ReloadStatus.ok)
        {
            printMessage("ALL Script: ("+alSscripts.size()+")");
            printMessage("-------------------------------");
            alSscripts.forEach((sc) -> {
                printMessage(sc.toString());
            });
        }
        else
            printMessage(getMessageRelSta(relSta));
    }
    
    public void printCSS()
    {
        if (relSta==ReloadStatus.ok)
        {
            printMessage("ALL CSS: ("+alCss.size()+")");
            printMessage("-------------------------------");
            alCss.forEach((sc) -> {
                printMessage(sc.toString());
            });
        }
        else
            printMessage(getMessageRelSta(relSta));
    }
    public void printCrowdOfLinks()
    {
        if (relSta==ReloadStatus.ok)
        {
            printMessage("Crowd of Links: "+crowdOfLinks);
        }
        else
            printMessage(getMessageRelSta(relSta));
    }
    public void printMessage(String mess)
    {
        System.out.println(mess);
        if (outputTextPane!=null)
        {
            try {
                outputTextPane.getStyledDocument().insertString(
                        outputTextPane.getStyledDocument().getLength(),mess + "\n", null);
            } catch (BadLocationException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    // Get μέθοδοι :
    
    public Elements getAlCss() {
        return alCss;
    }
    
    public Elements getAlSscripts() {
        return alSscripts;
    }
    
    public int getCrowdOfLinks() {
        return crowdOfLinks;
    }
    
    public Document getDoc() {
        return doc;
    }
    
    public URL getCurrURL() {
        return currURL;
    }
    
    // Static Tools:
    
    static public String getMessageRelSta(ReloadStatus rs)
    {
        String tmpStr = "Reload Status: ";
        switch(rs)
        {
            case ok :
                tmpStr += "OK" ;
                break ;
            case err_url :
                tmpStr += "URL ERROR " ;
                break ;
            case err_parse :
                tmpStr += "PARSE ERROR" ;
                break ;
            case not_defined_url :
                tmpStr += "NOT DEFINED URL" ;
                break ;
            case err_other :
            default:
                tmpStr += "ERROR" ;
        }
        return tmpStr ;
    }
}
