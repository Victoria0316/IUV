package bluemobi.iuv.util;

import android.view.View;

/**
 * Created by liufy on 2015/9/24.
 */
public class MeasureUtils {

    public static int getMeasurement(int measureSpec, int contentSize) {
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        int resultSize = 0;
        switch (specMode) {
            case View.MeasureSpec.UNSPECIFIED:
                resultSize = contentSize;
                break;
            case View.MeasureSpec.AT_MOST:
                resultSize = Math.min(contentSize, specSize);
                break;
            case View.MeasureSpec.EXACTLY:
                resultSize = specSize;
                break;
        }

        return resultSize;
    }
}
