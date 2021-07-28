/* Memento
 * 状態を保存する
 */
import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        Gamer gamer = new Gamer(100);
        Memento memento = gamer.createMemento();
        for(int i = 0; i < 100; i++) {
            System.out.println("==== " + i);
            System.out.println("現状: " + gamer);
            gamer.bet();
            System.out.println("所持金は " + gamer.getMoney() + "円になりました");
            if(gamer.getMoney() > memento.getMoney()) {

                System.out.println("だいぶ増えたので現在の状態を保存しておこう");
                memento = gamer.createMemento();
            } else if(gamer.getMoney() < memento.getMoney() / 2) {

                System.out.println("だいぶ減ったので、以前の状態に復帰しよう");
                gamer.restoreMemento(memento);
            }

        }

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) { }
        System.out.println("");

    }
}

class Memento {
    int money;
    ArrayList fruits;
    Memento(int money) {
        this.money =  money;
        this.fruits = new ArrayList();
    }
    public int getMoney() {
        return money;
    }
    void addFruit(String fruit) {
        fruits.add(fruit);
    }
    List getFruits() {
        return (List)fruits.clone();
    }
}

class Gamer {
    private int money;
    private List fruits = new ArrayList();
    private Random random = new Random();
    private static String[] fruitsname = {
        "リンゴ", "ぶどう", "バナナ", "みかん"
    };
    public Gamer(int money) {
        this.money = money;
    }
    public int getMoney(){
        return money;
    }
    public void bet() {
        int dice = random.nextInt(6) + 1;
        if(dice == 1) {
            money += 100;
            System.out.println("所持金が増えました");
        } else if(dice == 2) {
            money /= 2;
            System.out.println("所持金が半分になりました");
        } else if(dice == 6) {
            String f = getFruit();
            System.out.println("フルーツ" + f + "をもらいました");
            fruits.add(f);
        } else {
            System.out.println("何もおこりませんでした");
        }
    }
    public Memento createMemento() {
        Memento m = new Memento(money);
        Iterator it = fruits.iterator();
        while(it.hasNext()){
            String f = (String)it.next();
            if(f.startsWith("おいしい")) {
                m.addFruit(f);
            }
        }
        return m;
    }
    public void restoreMemento(Memento memento) {
        this.money = memento.money;
        this.fruits = memento.getFruits();
    }
    public String toString() {
        return "[ money = " + money + ", fruits" + fruits + "]";
    }
    private String getFruit() {
        String prefix = "";
        if(random.nextBoolean()) {
            prefix = "おいしい";
        }
        return prefix + fruitsname[random.nextInt(fruitsname.length)];
    }
}


