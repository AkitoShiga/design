/* テンプレートメソッド
 * スーパークラスの中でサブクラスのメソッドを定義する
 * サブクラスはその定義されたメソッドの中で動作を定義する
 */
public class App {

    public static void main(String[] args) throws Exception {

        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("Hello world.");
        AbstractDisplay d3 = new StringDisplay("こんにちは。");

        d1.display();
        d2.display();
        d3.display();

    }

}

/* スーパークラス
 */
abstract class AbstractDisplay {

    public abstract void open();
    public abstract void print();
    public abstract void close();
    //ここがテンプレートメソッドにあたる
    //抽象的に動作を定義して、その中の具体的な動作をサブクラスに任せる
    //つまり、テンプレートメソッドは自クラスの抽象メソッドを呼び出して構成する
    public final void display() {

        open();

        for (int i = 0; i < 5; i++) {

            print();

        }

        close();

    }
}

/* 具体1
 */
class CharDisplay extends AbstractDisplay {

    private char ch;

    public CharDisplay(char ch) {

        this.ch = ch;

    }

    public void open() {

        System.out.print("<<");

    }

    public void print() {


        System.out.print(ch);

    }

    public void close() {

        System.out.println(">>");

    }

}

/* 具体2
 */
class StringDisplay extends AbstractDisplay {


    private String str;
    private int width;

    public StringDisplay(String str) {

        this.str = str;
        this.width = str.getBytes().length;

    }

    public void open() {

        printLine();

    }

    public void print() {

        System.out.println("|" + str + "|");

    }

    public void close() {

        printLine();

    }

    private void printLine() {

        System.out.print("+");

        for(int i = 0; i < width; i++) {

            System.out.print("-");

        }

        System.out.println("+");

    }

}