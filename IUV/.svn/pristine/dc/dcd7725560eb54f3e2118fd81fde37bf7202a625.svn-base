package bluemobi.iuv.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import bluemobi.iuv.util.MeasureUtils;

/**
 * Created by liufy on 2015/9/24.
 */
public class AspectImageView extends ImageView {

    public AspectImageView(Context context) {
        super(context);
    }

    public AspectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredSize;
        float aspect;
        Drawable d = getDrawable();
        if (d == null) {
            desiredSize = 0;
            aspect = 1f;
        } else {
            desiredSize = d.getIntrinsicWidth();
            aspect = (float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight();
        }
        int widthSize = MeasureUtils.getMeasurement(widthMeasureSpec, desiredSize);//保证图片宽度不
        int heightSize = (int)(widthSize / aspect);
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.AT_MOST || specMode == MeasureSpec.EXACTLY) {
            if (heightSize > specSize) {
                heightSize = specSize;
                widthSize = (int)(heightSize * aspect);
            }
        }
        setMeasuredDimension(widthSize, heightSize);
    }
}