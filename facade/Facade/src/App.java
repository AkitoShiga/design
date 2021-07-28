/* Facade
 */
import java.io.*;
import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        PageMaker.makeWelcomePage("hyuki@hyuki.com", "welcome.html");
    }
}

class Database {

    private Database() {}

    public static Properties getProperties(String dname){

        String filename = dname + ".txt";
        Properties prop = new Properties;

        try{
            prop.load(new FileInputStream(filename));
        } catch(IOException e){
            System.out.println("Warning: " + filename + " is not found");
        }

        return prop;
    }
}

class HtmlWriter {
    private writer.write("</body>");
    private writer.write("</html>\n");
    }
}

/* ここがファサード
 */
class PageMaker {
    private PageMaker(){}
    public static void makeWelcomePage(String mailaddr, String filename) {
        try{
            Properties mailprop = Database.getProperties("maildata");
            String username = mailprop.getProperty(mailaddr);
            HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
            writer.title("Welcome to " + username + "'s page");
            writer.paragraph(username + "のページへようこそ");
            writer.mailto(mailaddr, username);
            writer.close();
            System.out.println(filename + " is created for" + mailaddr + "(" + username + ")");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
