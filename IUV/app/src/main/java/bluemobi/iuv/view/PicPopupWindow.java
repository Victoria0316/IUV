package bluemobi.iuv.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import bluemobi.iuv.R;


/**
 * 
 * @author gaoxy
 *
 */
public class PicPopupWindow extends PopupWindow {
    protected View mMenuView;
    protected ImageView pic;
    protected TextView comment;
   
    public PicPopupWindow(Activity context,int picRes,String s) {
        super(context);  
        LayoutInflater inflater = (LayoutInflater) context  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        mMenuView = inflater.inflate(R.layout.pic_popup, null);
        pic = (ImageView) mMenuView.findViewById(R.id.pic);
        comment = (TextView) mMenuView.findViewById(R.id.comment);
        pic.setBackgroundResource(picRes);
        comment.setText(s);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);  
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(LayoutParams.WRAP_CONTENT);  
        //设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        //设置SelectPicPopupWindow弹出窗体动画效果  
        this.setAnimationStyle(R.style.AnimBottom);  
        //实例化一个ColorDrawable颜色baise
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#FFFFFF"));
        //设置SelectPicPopupWindow弹出窗体的背景  
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {  
               
            public boolean onTouch(View v, MotionEvent event) {  
                   
                int height = mMenuView.findViewById(R.id.pop_up).getTop();
                int y=(int) event.getY();  
                if(event.getAction()==MotionEvent.ACTION_UP){  
                    if(y<height){  
                        dismiss();  
                    }  
                }                 
                return true;  
            }  
        });  
   
    }  
    
   
}
