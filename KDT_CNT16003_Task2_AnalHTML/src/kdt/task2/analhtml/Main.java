 /**
  * TEI of Athens, Department of Informatics
  * Master of Science in Computing and Network Technologies
  * Distributed Web Applications, Task 2 Ενδεικτική λύση,
  * Subject: Ανάλυση κώδικα HTML ιστοσελίδας.
  * @author Michael Galliakis AM: CNT16003.
  * Ημερομηνία : 3/12/2016
  */
package kdt.task2.analhtml;

public class Main {
    public static void main(String[] args) {
        AnalysisHTMLFile ahf = new AnalysisHTMLFile() ;        
        
        System.out.println(FMain.TITLE);
        System.out.println("Ενδεικτικά γίνεται ανάλυση σε 3 site:");
        
        ahf.reload("http://www.teiath.gr") ;
        ahf.printAll();
        
        ahf.reload("http://www.cs.teiath.gr") ;        
        ahf.printAll();
        
        ahf.reload("http://stackoverflow.com/") ;        
        ahf.printAll();
    }
}
