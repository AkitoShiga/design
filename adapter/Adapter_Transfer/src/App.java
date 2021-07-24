/* Transfer 移譲
 * すでに用意されている具象をメンバとして保持し、実際の処理を移譲する
 * これはよく見るやつかも？
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

/* 欲しい抽象 -> インターフェースから抽象クラスに変更
 */
abstract class Print {

    public abstract void printWeak();
    public abstract void printStrong();

 }

/* 欲しい抽象の具象
 */
class PrintBanner extends Print {

    private Banner banner;

    public PrintBanner(String string) {

        //自身のコンストラクタで生成している
        this.banner = new Banner(string);

    }

    public void printWeak() {

        banner.showWithParen();

    }

    public void printStrong() {

        banner.showWithAster();

    }

}