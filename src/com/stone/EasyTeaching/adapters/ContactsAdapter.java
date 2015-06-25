package com.stone.EasyTeaching.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.stone.EasyTeaching.R;
import com.stone.EasyTeaching.models.ContactsModel;

import java.util.List;

/**
 * Created by hangli2 on 2015/6/25.
 */
public class ContactsAdapter extends BaseAdapter {
    private Context context;
    private List<ContactsModel> contactsModelList;
    private LayoutInflater inflater;

    public ContactsAdapter(Context context, List<ContactsModel> contactsModelList) {
        this.context = context;
        this.contactsModelList = contactsModelList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contactsModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactsModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.contacts_item,null);
            viewHolder.header = (ImageView) view.findViewById(R.id.contacts_header);
            viewHolder.call = (ImageView) view.findViewById(R.id.contacts_call);
            viewHolder.message = (ImageView) view.findViewById(R.id.contacts_message);
            viewHolder.name = (TextView) view.findViewById(R.id.contacts_name);
            viewHolder.intro = (TextView) view.findViewById(R.id.contacts_intro);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    private class ViewHolder{
        ImageView header;
        ImageView call;
        ImageView message;
        TextView name;
        TextView intro;
    }
}
