package bluemobi.iuv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.fragment.BrandDetailFragment;
import bluemobi.iuv.fragment.HotMallFragment;
import bluemobi.iuv.network.model.GoodInfo;
import bluemobi.iuv.network.request.CollectGoodRequest;
import bluemobi.iuv.network.request.PraiseGoodRequest;
import bluemobi.iuv.network.response.GivePraiseResponse;
import bluemobi.iuv.network.response.PraiseGoodResponse;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;

/**
 * Created by gaoxy on 2015/8/5.
 */
public class HotMallAdapter extends BaseAdapter {
    protected List<GoodInfo> mListData;
    protected LayoutInflater inflater;
    private Context context;
    private  HotMallFragment hot;
    public HotMallAdapter(Context c,List<GoodInfo> data,HotMallFragment f) {
        mListData=data;
        inflater = LayoutInflater.from(c);
        context=c;
        hot=f;
    }
    @Override
    public int getCount() {
        return mListData==null?0:mListData.size();

    }

    @Override
    public Object getItem(int position) {
        return mListData == null ? null : mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GoodInfo bean=mListData.get(position);
        HolderView holder = null;
        if (convertView == null) {
            holder = new HolderView();
            convertView = inflater.inflate(R.layout.lv_hot_mall_list_item,
                    null);
            holder.pic=(ImageView)convertView.findViewById(R.id.pic);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.mark=(TextView)convertView.findViewById(R.id.mark);
            holder.price=(TextView)convertView.findViewById(R.id.price);
            holder.praise_text=(TextView)convertView.findViewById(R.id.praise_text);
            holder.collect_text=(TextView)convertView.findViewById(R.id.collect_text);
            holder.collect_img=(ImageView)convertView.findViewById(R.id.collect_img);
            holder.praise_img=(ImageView)convertView.findViewById(R.id.praise_img);

            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
        if(StringUtils.isEmpty(bean.getPraiseId())||"null".equals(bean.getPraiseId())){
            holder.praise_img.setImageResource(R.drawable.praise_n);
        }else {
            holder.praise_img.setImageResource(R.drawable.praise_p);
        }
        if(StringUtils.isEmpty(bean.getCollectionId())||"null".equals(bean.getCollectionId())){
            holder.collect_img.setImageResource(R.drawable.collect_n);
        }else {
            holder.collect_img.setImageResource(R.drawable.collect_p);
        }
        holder.praise_text.setText(bean.getPraiseNum());
        holder.collect_text.setText(bean.getCollectionNum());
        holder.name.setText(bean.getProductName());
        holder.mark.setText(bean.getDescription());
        holder.price.setText("¥ " + bean.getCustomerPrice());

        holder.pic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String goodId = mListData.get(position).getId();
                Toast.makeText(context, "" + position + "   " + goodId, Toast.LENGTH_SHORT).show();
                Utils.moveToFragment(BrandDetailFragment.class, hot, "brandlistfragment");
            }
        });
        Glide.with(context).load(bean.getImgPath()).placeholder(R.drawable.p8_2_shop_default).into(holder.pic);

        holder.praise_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //赞
                praiseGood(mListData.get(position).getId(), position, refreshPraiseGood);
            }
        });

        holder.collect_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收藏
                collectGood(mListData.get(position).getId(),position,refreshCollectGood);
            }
        });

        return convertView;
    }

    public interface RefreshPraiseGoodListener {
        public void refreshView(int pos);
    }

    RefreshPraiseGoodListener refreshPraiseGood = new RefreshPraiseGoodListener() {
        @Override
        public void refreshView(int p) {
            mListData.get(p).setPraiseId("1");
            mListData.get(p).setPraiseNum(String.valueOf(Integer.valueOf(mListData.get(p).getPraiseNum()) + 1));
            HotMallAdapter.this.notifyDataSetChanged();
        }
    };

    private  void praiseGood(String goodId,  final int position, final RefreshPraiseGoodListener listener){
        PraiseGoodRequest request = new PraiseGoodRequest(
                new Response.Listener<PraiseGoodResponse>() {
                    @Override
                    public void onResponse(PraiseGoodResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            listener.refreshView(position);
                        }
                    }
                }, (Response.ErrorListener) context);
        request.setId(goodId);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(context);
        WebUtils.doPost(request);
    }

    //收藏 start
    public interface RefreshCollectGoodListener {
        public void refreshView(int pos);
    }

    RefreshCollectGoodListener refreshCollectGood = new RefreshCollectGoodListener() {
        @Override
        public void refreshView(int p) {
            mListData.get(p).setCollectionId("1");
            mListData.get(p).setCollectionNum(String.valueOf(Integer.valueOf(mListData.get(p).getCollectionNum()) + 1));
            HotMallAdapter.this.notifyDataSetChanged();
        }
    };

    private  void collectGood(String goodId,  final int position, final RefreshCollectGoodListener listener){
        CollectGoodRequest request = new CollectGoodRequest(
                new Response.Listener<GivePraiseResponse>() {
                    @Override
                    public void onResponse(GivePraiseResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            listener.refreshView(position);
                        }
                    }
                }, (Response.ErrorListener) context);
        request.setId(goodId);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(context);
        WebUtils.doPost(request);
    }

    class HolderView{
        ImageView pic,collect_img,praise_img;
        TextView name,mark,price,praise_text,collect_text;
    }
}
