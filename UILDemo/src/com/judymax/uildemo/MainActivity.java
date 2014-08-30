package com.judymax.uildemo;

import com.judymax.uildemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class MainActivity extends Activity
{
    protected ImageLoader imageLoader;
    private DisplayImageOptions options;
    private String[] imageUrls;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        imageUrls = new String[] {
            "http://image.haha.mx/2013/10/31/middle/1021863_eaa3e905a0f90d999e324a504ce5318e_1383154853.jpg",
            "http://image.haha.mx/2013/10/31/middle/1022418_0975752a4fa53613356df16f6f156a13_1383201220.jpg",
            "http://image.haha.mx/2013/10/27/middle/1018533_1219857b5efcc6887431a791bd31e121_1382879198.jpg",
            "http://image.haha.mx/2014/08/29/middle/1405464_3d6fbe203300b4528bf68ea11ca965b7_1409319483.jpg"
        };

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_stub)
            .showImageForEmptyUri(R.drawable.ic_empty)
            .showImageOnFail(R.drawable.ic_error)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .displayer(new RoundedBitmapDisplayer(20))
            .build();
        
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new ItemAdapter());
    }

    private static class ViewHolder {
        TextView text;
        ImageView image;
    }

    private class ItemAdapter extends BaseAdapter {
        private SimpleImageLoadingListener imageLoadingListener = new SimpleImageLoadingListener();
        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ViewHolder holder;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.item_list_image, parent, false);
                holder = new ViewHolder();
                holder.text = (TextView) view.findViewById(R.id.text);
                holder.image = (ImageView) view.findViewById(R.id.image);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.text.setText("图片" + (position + 1));
            imageLoader.displayImage(imageUrls[position], holder.image, options, imageLoadingListener);
            return view;
        }
    }
}
