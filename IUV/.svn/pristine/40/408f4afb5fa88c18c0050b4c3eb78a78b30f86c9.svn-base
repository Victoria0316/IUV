package bluemobi.iuv.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.entity.CityBean;

public class AreaAdapter extends BaseAdapter {

	private List<CityBean> mList;


	private Context mContext;

	private View.OnClickListener selectListener;

	public AreaAdapter(List<CityBean> data,  Context context) {
		mList = data;
		mContext = context;
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		CityBean item = mList.get(position);
		TextView view = new TextView(mContext);
		view.setGravity(Gravity.CENTER);
		if (position==0){
			view.setBackgroundResource(R.drawable.city_blue);
			view.setTextColor(mContext.getResources().getColor(
					R.color.white));
		}else {
			view.setBackgroundResource(R.drawable.city_white);
		}
		view.setSingleLine(true);
		holder = new ViewHolder();
		holder.area = view;
		convertView = view;
		holder.area.setText(item.getName());
		holder.area.setTag(item);
		holder.area.setOnClickListener(selectListener);
		return convertView;
	}

	class ViewHolder {
		public TextView area;
	}

	public View.OnClickListener getSelectListener() {
		return selectListener;
	}

	public void setSelectListener(View.OnClickListener selectListener) {
		this.selectListener = selectListener;
	}

}
