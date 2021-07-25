/* シングルトン
 * プログラムにおいて特定のインスタンスが1つしか生成されないことを保証する
 */
public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Start");
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        if(obj1 == obj2) {

            System.out.println("同じインスタンス");

        } else {

            System.out.println("同じインスタンスではない");

        }

        System.out.println("End");

    }
}

class Singleton {

    private static Singleton singleton = new Singleton();

    //外部からの呼び出しを禁止する
    private Singleton () {
        System.out.println("インスタンスを生成しました");
    }

    public static Singleton getInstance() {

        return singleton;

    }

}
