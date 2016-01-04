package bluemobi.iuv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.entity.PersonalCenterMyInformBean;
import bluemobi.iuv.util.Utils;

/**
 * Created by gaozq on 2015/8/6.
 */
public class PersonalCenterMyCollectGoodsAdapter extends BaseAdapter {
    protected final static String tag ="PersonalCenterMyCollectGoodsAdapter";

    protected List<PersonalCenterMyInformBean> mListData;
    protected LayoutInflater inflater;
    private Context context;


    public PersonalCenterMyCollectGoodsAdapter(Context context, List<PersonalCenterMyInformBean> data) {
        this.mListData=data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {


        PersonalCenterMyInformBean bean=mListData.get(position);
        HolderView holder = null;
        if (convertView == null) {
            holder = new HolderView();
            convertView = inflater.inflate(R.layout.item_my_collect_goods,
                    null);
            holder.head= (ImageView) convertView.findViewById(R.id.head);
            holder.goods_name= (TextView) convertView.findViewById(R.id.goods_name);
            holder.goods_info= (TextView) convertView.findViewById(R.id.goods_info);
            holder.money= (TextView) convertView.findViewById(R.id.money);



            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }

//        holder.content.setText(bean.getContent());
//        holder.market_name.setText(bean.getMarket_name());
//        holder.head.
//        holder.goods_name.setText("");
//        holder.goods_info.setText("");
//        holder.money.setText("");

        return convertView;
    }

    class HolderView{

        ImageView head;
        TextView goods_info,goods_name,money;
    }

}
