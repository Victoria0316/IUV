package bluemobi.iuv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.entity.CityBean;

/**
 * Created by gaoxy on 2015/8/4.
 */
public class SearchResCityAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    protected List<CityBean> listData;


    public SearchResCityAdapter(Context context, List<CityBean> data) {

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listData = data;
    }

    @Override
    public int getCount() {
        return listData == null ? 0 : listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData == null ? null : listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        CityBean  bean=listData.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(
                    R.layout.lv_search_city_res_item,
                    null);
            viewHolder.city = (TextView) convertView.findViewById(R.id.id);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.city.setTag(position);
        viewHolder.city.setText(bean.getName());

        viewHolder.city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (Integer)v.getTag();
                Toast.makeText(context, "" + pos, Toast.LENGTH_SHORT).show();
                //TODO FAKE


            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView city;
    }
}
