package bluemobi.iuv.eventbus;

/**
 * Created by 丰宇 on 2015/8/23.
 */
public abstract class BaseEvent
{
   private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
