/* Builder
 * 段階的にオブジェクトを構築する
 * よく使うかも
 */
public class App {
    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            usage();
            System.exit(0);
        }
        if(args[0].equals("plain")) {

            TextBuilder textbuilder = new TextBuilder();
            Director director = new Director(textbuilder);
            director.construct();
            String result = textbuilder.getResult();

        } else if(args[0].equals("html")) {
            HTMLBuilder htmlbuilder = new HTMLBuilder();
            Director director = new Director(htmlbuilder);
            director.construct();
            String filename = htmlbuilder.getResult();
            System.out.println(filename + "が作成されました");
        } else {

            usage();
            System.exit(0);

        }

    }

    public static void usage() {

        System.out.println("Usage: java Main plain");
        System.out.println("Usage: java Main html");

    }

}

abstract class Builder {

    public abstract void makeTitle(String title);
    public abstract void makeString(String str);
    public abstract void makeItems(String[] items);
    public abstract void close();

}

class Director {

    private Builder builder;

    public Director(Builder builder) {

        this.builder = builder;

    }

    public void construct() {

        builder.makeTitle("Greeting");
        builder.makeString("朝から昼にかけて");
        builder.makeItems(new String[] {

            "おはようございます",
            "こんにちは"

        });
        builder.makeString("夜に");
        builder.makeItems(new String[] {

                "こんばんは",
                "おやすみなさい",
                "さようなら"

        });
        builder.close();

    }

}

class TextBuilder extends Builder {

    private StringBuffer = new StringBuffer();

    public void makeTitle(String title) {

        buffer.append("========================\n");
        buffer.append("[" + title + "]");
        buffer.appned("\n");

    }

    public void makeString(String str) {

        buffer.appned('* ' + str + "\n");

    }

    public void makeItems(String[] items) {

        for(int i = 0; i < items.length; i++) {

            buffer.append("  ." + items[i] + "\n");

        }

        buffer.append("\n");

    }

    public void close() {

        buffer.append("========================\n");

    }

    public String getResult() {

        return buffer.toStrng();

    }

}

class HTMLBuilder extends Builder {

    private String filename;
    private PrintWriter writer;
    public void makeTitle(String title) {
        filename = title + ".html";
        try {

            writer = new PrintWriter(new FileWriter(filename));

        } catch(IOException e) {

            e.printStackTrace();

        }

        writer.println("<html><head><title>" + title + "</html></head></title>");
        writer.println("<h1>" + title + "</h1>");

    }

    public void makeString(String str) {

        writer.println("<p>" + str + "</p>");

    }

    public void makeItems(String[] items) {

        writer.println("<ul>");

        for(int i = 0; i < items.length; i++) {

            writer.println("<li>" + items[i] + "</li>");

        }

        writer.println("</ul>");

    }

    public void close() {

        writer.println("</body></html>");
        writer.close();

    }

    public String getResult() {

        return filename;

    }

}
