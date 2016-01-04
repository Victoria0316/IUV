package bluemobi.iuv.eventbus;

/**
 * Created by liufy on 2015/10/16.
 */
public class CommonTypeEvent extends BaseEvent {

    private int type ;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
