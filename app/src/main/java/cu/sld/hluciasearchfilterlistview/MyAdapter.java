package cu.sld.hluciasearchfilterlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cu.sld.hluciasearchfilterlistview.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Filterable {

    //원본 배열
    private ArrayList<Value> mOriginalValues;

    //filter후의 결과 배열
    private ArrayList<Value> mDisplayedValues;

    LayoutInflater inflater;
    Context mContext;

    public MyAdapter(Context context, ArrayList<Value> mValueArrayList) {
        this.mContext = context;
        this.mOriginalValues = mValueArrayList;
        this.mDisplayedValues = mValueArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        LinearLayout linearLayout;
        TextView nameTextView, numTextView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            holder.numTextView = (TextView) convertView.findViewById(R.id.numTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTextView.setText(mDisplayedValues.get(position).mName);
        holder.numTextView.setText(mDisplayedValues.get(position).mNumber + "");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(mContext, mDisplayedValues.get(position).mName, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                //필터링 된 값을 저장
                mDisplayedValues = (ArrayList<Value>) results.values;

                //새로운 필터링 된 값으로 데이터 갱신
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                //필터링 작업 결과를 값으로 유지
                FilterResults results = new FilterResults();
                ArrayList<Value> FilteredArrList = new ArrayList<Value>();

                if (mOriginalValues == null) {
                    //원래 데이터를 mOriginalValues에 저장합니다
                    mOriginalValues = new ArrayList<Value>(mDisplayedValues);
                }

                //constraint (CharSequence that is received)가 null 인 경우 mOriginalValues (Original) 값을 반환.
                //그렇지 않으면 필터링을 수행하고 FilteredArrList (Filtered)를 반환.
                if (constraint == null || constraint.length() == 0) {

                    //원래 결과를 반환하도록 설정
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).mName;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new Value(mOriginalValues.get(i).mName, mOriginalValues.get(i).mNumber));
                        }
                    }

                    //필터링 결과를 반환하도록 설정
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}
