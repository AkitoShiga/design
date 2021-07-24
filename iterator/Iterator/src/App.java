// Iterator
import java.util.*;
public class App {

    public static void main(String[] args) throws Exception {

        BookShelf bookShelf = new BookShelf(4);

        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));

        //インターフェースで宣言するのが良い
        Iterator it = bookShelf.iterator();

        // コレクションクラスをイテレーターでスキャンするのはだいぶ読みやすいが、
        // そこまでコストをかけて作成するべきかは少し実感しにくい
        // クラスの粒度と責務の分割についてあまりピンときていない
        // アグリゲートクラスの実装にfor文が依存しない
        while(it.hasNext()) {

            Book book = (Book)it.next();

            System.out.println(book.getName());

        }

    }

}

/* アグリゲート 集合体
 * イテレーターを1つ作成するのみ
 * この実装クラスをコレクションとみなす
 */
interface Aggregate {

    public abstract Iterator iterator();

}


/* イテレーター
 * 反復処理によってコレクションの全体を捜査する
 */
interface Iterator {

    // 次の要素が存在するか？
    public abstract boolean hasNext();

    // 次の要素を表示
    // 対象のコレクションの内部状態を次に進めておく
    public abstract Object next();

}

/* コレクションの要素
 */

class Book {

    private String name;

    public Book(String name) {

        this.name = name;

    }

    public String getName() {

        return this.name;

    }

}

/* 要素を格納するコレクション
 * 要素を集約し、イテレーターの実装に集約される
 */
class BookShelf implements Aggregate {

    private List<Book> books;
    private int last = 0;

    public BookShelf(int maxsize) {

        this.books = new ArrayList();

    }

    public Book getBookAt(int index) {


        return books.get(index);

    }

    public void appendBook(Book book) {

        //ここでのインデックスのチェックはどこの責務なのか？
        this.books.add(book);
        last++;

    }

    public int getLength() {

        return last;

    }

    public Iterator iterator() {

        return new BookShelfIterator(this);

    }

}

/* コレクションの捜査クラス
 * コレクションはこのクラスに集約される
 */
class BookShelfIterator implements Iterator {

    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {

        this.bookShelf = bookShelf;
        this.index = 0;

    }

    public boolean hasNext() {

        if(index < bookShelf.getLength()) {

            return true;

        } else {

            return false;

        }

    }
    // Bookを返しているのになぜObject型なのか？戻り値の変更はオーバーライドとみなされないんだっけ？
    // →オーバーロードだった戻り値の型のみの変更は仕様上できない
    public Object next() {

        Book book = bookShelf.getBookAt(index);

        index++;

        return book;

    }

}
