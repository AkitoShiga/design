/* 継承によるアダプター
 * すでに用意されている具象と、必要となる抽象を結びつける
 * -> 用意されている具象を継承し、必要な抽象の具象を生成する
 * 正直ここでBannerを呼び出せばよいのでは？と思った
 */
public class App {

    public static void main(String[] args) throws Exception {

        Print p = new PrintBanner("Hello");

        p.printWeak();
        p.printStrong();

    }
}

/* すでに用意されている具象
 */
class Banner {

    private String string;

    public Banner(String string) {

        this.string = string;

    }

    public void showWithParen() {

        System.out.println("(" + string + ")");

    }

    public void showWithAster() {

        System.out.println("*" + string + "*");

    }

}

/* 必要となる抽象
 * 漠然とこの様なものが欲しいという段階
 */
interface Print {

    public abstract void printWeak();
    public abstract void printStrong();

}

/* すでに用意されている具象を継承することにより、必要となる抽象を具象化
 */
class PrintBanner extends Banner implements Print {

    public PrintBanner(String string) {

        super(string);

    }

    public void printWeak() {

        showWithParen();

    }

    public void printStrong() {

        showWithAster();

    }



}