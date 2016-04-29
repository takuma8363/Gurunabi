package net.example.nwwuser.gurunabi;

import android.app.ListActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nwwuser on 2016/04/22.
 */
public class SubActivity extends ListActivity  {

    private Integer[] imageDrawables = {
            R.drawable.image0,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
    };

    private String[] imageComments = {
            "店舗名所1","店舗名所2","店舗名所3","店舗名所4","店舗名所5",
            "店舗名所6","店舗名所7","店舗名所8"
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        List<CellData> list = new ArrayList<CellData>();

        for(int i = 0; i < imageDrawables.length; i++){
            CellData data = new CellData(imageComments[i],imageDrawables[i]);
            list.add(data);
        }
        setListAdapter(new ListViewAdapter(this,R.layout.sub_list_item,list));
    }

    class CellData{
        String imageComment;
        int imageDrawableId;

        public CellData(String imageComment,int imageDrawableId){
            this.imageComment = imageComment;
            this.imageDrawableId = imageDrawableId;
        }
    }

    class ViewHolder{
        TextView m_textView;
        ImageView imageView;
    }

    public class ListViewAdapter extends ArrayAdapter<CellData>{
        private LayoutInflater inflater;
        private int itemLayout;
        CellData data;

        public ListViewAdapter(Context context,int itemLayout,List<CellData>list){
            super(context,0,list);
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemLayout = itemLayout;
        }

        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            ViewHolder holder;
            if(convertView == null){
                convertView = inflater.inflate(itemLayout,parent,false);
                holder = new ViewHolder();
                holder.m_textView = (TextView)convertView.findViewById(R.id.textView);
                holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            data = getItem(position);
            holder.m_textView.setText(data.imageComment);
            holder.imageView.setImageResource(data.imageDrawableId);
            return convertView;
        }
    }
}
