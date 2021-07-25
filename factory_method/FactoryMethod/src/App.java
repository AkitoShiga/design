/* Factory Method
 * Template Methodでは具体的な処理をサブクラスによって行ったのに対し、
 * Factory Methodではインスタンスの生成をサブクラスによって行う
 * インスタス作成に付随する処理があるときにFinalメソッドに記述して、使うのが良いのかなと思った。
 */

import java.util.*;
public class App {

    public static void main(String[] args) throws Exception {

        Factory factory = new IDCardFactory();

        Product card1 = factory.create("A");
        Product card2 = factory.create("B");
        Product card3 = factory.create("C");

        card1.use();
        card2.use();
        card3.use();

    }
}

/* frame workパッケージ */

abstract class Factory {
    //Product 作成の際はここを呼び出す
    public final Product create(String owner) {

        Product p = createProduct(owner);

        registerProduct(p);

        return p;

    }

    protected abstract Product createProduct(String owner);
    protected abstract void registerProduct(Product product);

}

abstract class Product {

    public abstract void use();

}

/* idcardパッケージ */
class IDCardFactory extends Factory {

    private HashMap database = new HashMap();
    private int serial = 100;

    protected Product createProduct(String owner) {

        return new IDCard(owner, serial++);

    }

    protected void registerProduct(Product product) {

        IDCard card = (IDCard)product;
        database.put(new Integer(card.getSerial()), card.getOwner());

    }

    public HashMap getDatabase() {

        return database;

    }
}

class IDCard extends Product {

    private String owner;
    private int serial;

    //同一パッケージ内での呼び出しを許可する
    IDCard(String owner, int serial) {

        System.out.println(owner + "(" + serial + ")" + "のカードを作ります。");
        this.owner = owner;
        this.serial = serial;

    }

    public void use() {

        System.out.println(owner + "(" + serial + ")" + "のカードを使います。");

    }

    public String getOwner() {

        return owner;

    }

    public int getSerial() {

        return serial;

    }

}