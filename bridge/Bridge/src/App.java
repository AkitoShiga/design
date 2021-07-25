/* Bridge 機能追加のためのサブクラスの作成と、実装の追加のためのサブクラスの作成を分割する
 */
public class App {
    public static void main(String[] args) throws Exception {

        Display d1 = new Display(new StringDisplayImpl("Hello World"));
        Display d2 = new Display(new StringDisplayImpl("Hello Japan"));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("Hello Universe"));

        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(5);

    }
}

/* 機能 */
class Display {

    private DisplayImpl impl;

    public Display(DisplayImpl impl) {
        this.impl = impl;
    }

    public void open() {
        impl.rawOpen();
    }

    public void print() {
        impl.rawPrint();
    }

    public void close() {
        impl.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }

}

class CountDisplay extends Display {

    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    public void multiDisplay(int times) {
        open();
        for(int i =0; i < times; i++) {
            print();
        }
        close();
    }
}

/* 実装 */
abstract class DisplayImpl {

    public abstract void rawOpen();
    public abstract void rawPrint();
    public abstract void rawClose();

}

class StringDisplayImpl extends DisplayImpl {

    private String string;
    private int width;

    public StringDisplayImpl(String string) {
        this.string = string;
        this.width = string.getBytes().length;
    }

    public void rawOpen() {
        printLine();
    }

    public void rawPrint() {
        System.out.println("|" + string + "|");
    }

    public void rawClose() {
        printLine();
    }

    private void printLine() {
        System.out.println("+");
        for(int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

}