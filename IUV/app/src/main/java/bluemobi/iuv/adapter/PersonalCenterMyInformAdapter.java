package bluemobi.iuv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.entity.PersonalCenterMyInformBean;
import bluemobi.iuv.util.Utils;

/**
 * Created by gaozq on 2015/8/6.
 */
public class PersonalCenterMyInformAdapter extends BaseAdapter {
    protected final static String tag ="PersonalCenterMyInformAdapter";

    protected List<PersonalCenterMyInformBean> mListData;
    protected LayoutInflater inflater;
    private Context context;


    public PersonalCenterMyInformAdapter(Context context,List<PersonalCenterMyInformBean> data) {
        Utils.out(tag,"PersonalCenterMyInformAdapter 构造函数");
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

        Utils.out(tag,"PersonalCenterMyInformAdapter getView");

        PersonalCenterMyInformBean bean=mListData.get(position);
        HolderView holder = null;
        if (convertView == null) {
            holder = new HolderView();
            convertView = inflater.inflate(R.layout.item_my_inform,
                    null);
            holder.content= (TextView) convertView.findViewById(R.id.content);
            holder.market_name=(TextView)convertView.findViewById(R.id.market_name);

            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }

        holder.content.setText(bean.getContent());
        holder.market_name.setText(bean.getMarket_name());

        return convertView;
    }

    class HolderView{

        TextView content,market_name;
    }

}
