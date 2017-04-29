package com.example.shentanli.accessibilitydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shentanli.accessibiliydemo.R;

/**
 * Created by shentanli on 12/2/16.
 */
public class List_View extends ListView {

    private Context mcontext;

    public List_View(Context context) {
        super(context);
        init(context);
    }

    private class MyAdapter extends BaseAdapter{
        private LayoutInflater minflater;

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String[] itemName = {"li", "install", "uninstall"};
            minflater = LayoutInflater.from(mcontext);
            convertView = minflater.inflate(R.layout.listview_item, null);
            TextView mtext = (TextView) convertView.findViewById(R.id.test);
            mtext.setText(itemName[position]);

            String contentdes = new StringBuilder().append(mcontext.getString(R.string.task_name)).append("").append(itemName[position]).toString();
            mtext.setContentDescription(contentdes);
            convertView.setTag(position);
            return convertView;
        }
    }


    private void init(Context context) {
        mcontext = context;
        setAdapter(new MyAdapter());
        System.out.println("init");
    }

    @Override
    public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event){
        System.out.println("into onrequestsendaccessibilityevent");
        AccessibilityEvent record = event.obtain();
        super.onInitializeAccessibilityEvent(record);
        int priority = (Integer) child.getTag();
        String prioritystr = "Priority:" +priority;
        record.setContentDescription(prioritystr);
        event.appendRecord(record);

        return true;
    }

}
