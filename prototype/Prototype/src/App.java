/* Prototype
 * 既存のインスタンスから新しいインスタンスを作成する
 */

import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {

        //インスタンスを生成してマネージャーに登録
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('~');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        manager.register("strong message", upen);
        manager.register("warning box", mbox);
        manager.register("slash box", sbox);

        //生成
        Product p1 = manager.create("strong message");
        p1.use("Hello world");
        Product p2 = manager.create("warning box");
        p2.use("Hello world");
        Product p3 = manager.create("slash box");
        p3.use("Hello world");

    }
}

/* java.lang.Cloneableを継承するとcloneメソッドで自動的に複製を行える
 */
interface Product extends Cloneable {

    public abstract void use(String s);
    public abstract Product createClone();

}

/* インスタンスの複製を行う
 */
class Manager {

    // インスタンスと名前を紐付けて登録する
    private HashMap showcase = new HashMap();

    // 複製したいクラスをInterfaceにすることで、実装への依存を回避する
    public void register(String name, Product proto) {

        showcase.put(name, proto);

    }

    public Product create(String protoname) {

        Product p = (Product)showcase.get(protoname);

        return p.createClone();

    }

}

class MessageBox implements Product {

    private char decochar;

    public MessageBox(char decochar) {

        this.decochar = decochar;

    }

    public void use(String s) {

        int length = s.getBytes().length;

        for( int i = 0; i < length + 4; i++) {

            System.out.print(decochar);

        }

        System.out.println("");
        System.out.println(decochar + " " + s + " " + decochar);

        for( int i = 0; i < length + 4; i++) {

            System.out.print(decochar);

        }

        System.out.println("");

    }
    // 他のクラスから呼び出すためにcloneをラップする
    public Product createClone() {

        Product p = null;

        try {

            p = (Product)clone();

        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
        }

        return p;
    }

}

class UnderlinePen implements Product {

    private char ulchar;

    public UnderlinePen(char ulchar) {

        this.ulchar = ulchar;

    }

    public void use(String s) {

        int length = s.getBytes().length;

        System.out.println("\"" + s + "\"");

        for(int i = 0; i < length; i++) {

            System.out.print(ulchar);

        }

        System.out.println("");

    }

    // ここを共通化したほうがよいのでは？
    public Product createClone() {

        Product p = null;

        try {

            p = (Product)clone();

        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
        }

        return p;
    }


}