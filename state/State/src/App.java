import java.awt.*;
import java.awt.event.*;
/* State
 * 状態をクラスで表現する
 */
public class App {
    public static void main(String[] args) throws Exception {
        SafeFrame frame = new SafeFrame("State Sample");
        while(true) {
            for(int hour = 0; hour < 24; hour++){
                frame.setClock(hour);
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                }
            }
        }

    }
}


interface State {
    public abstract void doClock(Context context, int hour);
    public abstract void doUse(Context context);
    public abstract void doAlarm(Context context);
    public abstract void doPhone(Context context);
}

class DayState implements State {
    private static DayState singleton = new DayState();
    private DayState() {}
    public static State getInstance(){
        return singleton;
    }
    public void doClock(Context context, int hour) {
        if(hour < 9 || 17 <= hour) {
            context.changeState(NightState.getInstance());
        }
    }
    public void doUse(Context context) {
        context.recording("金庫使用（昼間）");
    }
    public void doAlarm(Context context) {
        context.callSecurityCenter("非常ベル（昼間）");
    };
    public void doPhone(Context context) {
        context.callSecurityCenter("通常の電話（昼間）");
    };
    public String toString(){
        return "[昼間]";
    }
}

class NightState implements State {
    private static NightState singleton = new NightState();
    private NightState(){}
    public static State getInstance(){
        return singleton;
    }
    public  void doClock(Context context, int hour) {
        if(9 <=hour && hour < 17) {
            context.changeState(DayState.getInstance());
        }
    };
    public  void doUse(Context context) {
        context.callSecurityCenter("非常: 夜間の金庫使用");
    };
    public  void doAlarm(Context context) {
        context.callSecurityCenter("非常ベル（夜間）");
    };
    public  void doPhone(Context context) {
        context.recording("夜間の通話録音");
    };
    public String toString () {
        return "[夜間]";
    }
}

/* 状態管理のクラス
 * ContextがStateに処理を移譲している感じ？
 * ここをかますのがよく理解できていない
 */
interface Context{
    public abstract void setClock(int hour);
    public abstract void changeState(State state);
    public abstract void callSecurityCenter(String msg);
    public abstract void recording(String msg);
}


class SafeFrame extends Frame implements ActionListener, Context {
    private TextField textClock = new TextField(60);
    private TextArea  textScreen = new TextArea(10, 60);
    private Button buttonUse = new Button("金庫使用");
    private Button buttonAlarm = new Button("非常ベル");
    private Button buttonPhone = new Button("通常電話");
    private Button buttonExit = new Button("終了");
    private State state = DayState.getInstance();

    public SafeFrame(String title) {
        super(title);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        add(textClock, BorderLayout.NORTH);
        add(textScreen, BorderLayout.CENTER);
        Panel panel = new Panel();
        panel.add(buttonUse);
        panel.add(buttonAlarm);
        panel.add(buttonPhone);
        panel.add(buttonExit);
        add(panel, BorderLayout.SOUTH);
        pack();
        show();
        buttonUse.addActionListener(this);
        buttonAlarm.addActionListener(this);
        buttonPhone.addActionListener(this);
        buttonExit.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        if(e.getSource() == buttonUse) {
            state.doUse(this);
        } else if (e.getSource() == buttonAlarm){
            state.doAlarm(this);
        } else if (e.getSource() == buttonPhone){
            state.doPhone(this);
        } else if (e.getSource() == buttonExit){
            System.exit(0);
        } else {
            System.out.println("?");
        }

    }
    public void setClock(int hour) {
        String clockstring = "現在時刻は";

        if(hour < 10) {
            clockstring += "0" + hour + ":00";
        } else {
            clockstring += hour + ":00";
        }
        System.out.println(clockstring);
        textClock.setText(clockstring);
        state.doClock(this, hour);
    }

    public void changeState(State state) {
        System.out.println(this.state + "から" + state + "へ状態が変化しました");
        this.state = state;
    }
    public void callSecurityCenter(String msg) {
        textScreen.append("call!" + msg + "\n");
    }
    public void recording(String msg) {
        textScreen.append("record...." + msg  + "\n");
    }
}

