package shipping.queue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Listener {

    private static Listener listener;

    private boolean correctData = true;
    private boolean firstData = false;

    public boolean dataShouldBeUpdated() {
        correctData = false;
        return correctData;
    }

    public static Listener getListener() {
        Listener localListener = listener;
        if (localListener == null) {
            synchronized (Listener.class) {
                localListener = listener = new Listener();
            }
        }
        return localListener;
    }

}
