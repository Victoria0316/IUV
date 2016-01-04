package bluemobi.iuv.view;

/**
 * Created by wangzhijun on 2015/7/8.
 */


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import bluemobi.iuv.R;
import bluemobi.iuv.util.Utils;


/**
 * @author wzhj0528
 */
public class SwitchDotView extends LinearLayout {

    private int mCount = 0;
    private Context mContext;
    private int mCurrentIndex = 0;
    private LayoutParams params;
    private int selectRes;
    private int unSelectRes;

    public SwitchDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        if(attrs != null){
            TypedArray typedArray = mContext.obtainStyledAttributes(attrs,
                    R.styleable.SwitchDotView);
            int temp;
            temp = typedArray.getResourceId(R.styleable.SwitchDotView_selected, -1);
            selectRes = temp==-1?selectRes: temp;
            temp = typedArray.getResourceId(R.styleable.SwitchDotView_unselected, -1);
            unSelectRes = temp==-1?unSelectRes: temp;
            typedArray.recycle();
        }
    }

    public SwitchDotView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        mContext = context;
    }

    public void generateDots(int count) {
        this.removeAllViews();
        params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.rightMargin = Utils.dip2px(mContext, 10);
        mCount = count;
        if (count > 1) {
            this.setVisibility(View.VISIBLE);
            ImageView dot0 = new ImageView(mContext);
            dot0.setPadding(0, 0, 5, 0);
            dot0.setImageResource(selectRes);
            dot0.setLayoutParams(params);
            dot0.setTag(0);
            this.addView(dot0, 0);

            for (int i = 1; i < count; i++) {
                ImageView dot = new ImageView(mContext);
                dot.setPadding(0, 0, 5, 0);
                dot.setImageResource(unSelectRes);
                dot.setTag(i);
                if (i != count - 1) {
                    dot.setLayoutParams(params);
                }
                this.addView(dot, i);
            }
        } else {
            this.setVisibility(View.GONE);
        }
    }

    public void next() {
        if (mCurrentIndex < 0) {
            mCurrentIndex = mCount - 1;
        }
        ImageView preDot = (ImageView) this.getChildAt(mCurrentIndex);
        if (preDot != null) {
            preDot.setImageResource(unSelectRes);
            mCurrentIndex = mCurrentIndex + 1;
            if (mCurrentIndex > mCount - 1 || mCurrentIndex < 0) {
                mCurrentIndex = 0;
            }
            ImageView currentDot = (ImageView) this.getChildAt(mCurrentIndex);
            currentDot.setImageResource(selectRes);
        }
    }

    public void pre() {
        ImageView nexDot = (ImageView) this.getChildAt(mCurrentIndex);
        if (nexDot != null) {
            nexDot.setImageResource(unSelectRes);
            mCurrentIndex = mCurrentIndex - 1;
            if (mCurrentIndex < 0) {
                mCurrentIndex = mCount - 1;
            }
            ImageView currentDot = (ImageView) this.getChildAt(mCurrentIndex);
            currentDot.setImageResource(selectRes);
        }
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentIndex(int index) {
        mCurrentIndex = index;
        for (int i = 0; i < mCount; i++) {
            ImageView dot = (ImageView) getChildAt(i);
            if (dot == null) {
                return;
            }
            if (i == mCurrentIndex) {
                dot.setImageResource(selectRes);
            } else {
                dot.setImageResource(unSelectRes);
            }
        }
    }
}
