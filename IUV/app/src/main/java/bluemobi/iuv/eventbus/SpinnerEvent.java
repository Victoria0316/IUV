package bluemobi.iuv.eventbus;

import bluemobi.iuv.view.CustomSpinnerSingle;

/**
 */
public class SpinnerEvent extends BaseEvent
{
    public CustomSpinnerSingle mySpinner;

    public CustomSpinnerSingle getMySpinner() {
        return mySpinner;
    }

    public void setMySpinner(CustomSpinnerSingle mySpinner) {
        this.mySpinner = mySpinner;
    }
}
