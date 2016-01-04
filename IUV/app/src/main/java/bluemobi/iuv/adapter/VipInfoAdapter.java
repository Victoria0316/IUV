package bluemobi.iuv.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.network.model.VipInfo;
import bluemobi.iuv.util.StringUtils;

/**
 * gaoxy
 */
public class VipInfoAdapter extends CommonAdapter<VipInfo> {

    private List<VipInfo> list;
    private Context mContext;


    public VipInfoAdapter(Context context, List mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        list = mDatas;
        mContext = context;
    }
    @Override
    public void convert(ViewHolder helper, final VipInfo item) {
        TextView receive = helper.getView(R.id.receive);
        TextView content = helper.getView(R.id.content);
        receive.setVisibility(View.GONE);
        ImageView coupons_image= helper.getView(R.id.coupons_image);
        TextView info=helper.getView(R.id.info);
        info.setText(item.getCouponDescription());
        content.setText("使用期限"+item.getCouponStartTime()+"-"+item.getCouponEndTime());
        if (StringUtils.isNotEmpty(item.getCustomerCouponImgPath())&& !"null".equals(item.getCustomerCouponImgPath())){
            Glide.with(mContext).load(item.getCustomerCouponImgPath()).into(coupons_image);
        } else {
            coupons_image.setImageResource(R.drawable.coupons_image);
        }

    }
}