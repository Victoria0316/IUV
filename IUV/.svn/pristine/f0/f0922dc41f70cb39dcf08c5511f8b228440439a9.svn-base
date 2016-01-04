package bluemobi.iuv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by gaoxy on 2015/8/5.
 */
public class ScorllListView extends ListView{
    public ScorllListView(Context context) {
        super(context);
    }

        public ScorllListView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        public ScorllListView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // TODO Auto-generated method stub
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }


}
