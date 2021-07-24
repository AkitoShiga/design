/* 練習問題
 * Propertiesクラスを用いて、プロパティの集合をファイルに保存するクラスを作成する
 */
import java.util.Properties;
import java.io.*;
public class App {
    public static void main(String[] args) throws Exception {

        FileIO f = new FileProperties();

        try {

            f.readFromFile("file.txt");
            f.setValue("year", "2004");
            f.setValue("month", "4");
            f.setValue("day", "21");
            f.writeToFile("newFile.txt");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}

/* Propertiesのメソッドなんか知らなくても、FileIOのメソッドを知ってれば大丈夫だよねという理論
interface FileIO {

    public abstract void readFromFile(String filename) throws IOException;
    public abstract void writeToFile(String filename) throws IOException;
    public abstract void setValue(String key, String value);
    public abstract String getValue(String key);

}
*/

/* FileIOのメソッドを用いてPropertiesの関数を呼び出すアダプター
 * FileIO.*をインポートする前提で使用
 */
class FileProperties implements FileIO extends Properties {

    public FileProperties() {}

    public void writeToFile(String filename) {

        load(new FileInputStream(filename));

    }

    public void readFromFile(String filename) {

        load(new FileOutputStream(filename));

    }

    public void setValue(String key, String value) {

        setProperty(key, value);

    }

    public void getValue(String key) {

        return getProperty(key, "");

    }


}