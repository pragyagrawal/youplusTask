package com.binarybricks.pragya.youplus.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.binarybricks.pragya.youplus.R;
import com.binarybricks.pragya.youplus.datamodel.ConversationData;
import com.binarybricks.pragya.youplus.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by PRAGYA on 8/2/2016.
 */
public class ConversationListAdapter extends BaseAdapter {

    private List<ConversationData> conversationList;
    private Context context;

    public ConversationListAdapter(Context context, List<ConversationData> conversationList) {
        this.conversationList = conversationList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public Object getItem(int position) {
        return conversationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // our ViewHolder.
// caches our TextView
    static class ViewHolderItem {
        // Lookup view for data population
        TextView tvFriendName;
        TextView tvLastMessage;
        TextView tvUnreadCount;
        CircleImageView ivAvatarImage;
        TextView tvTime;
        ProgressBar pbLoading;
        LinearLayout llConversationItem;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolderItem viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.conversation_list_item, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.tvFriendName = (TextView) view.findViewById(R.id.tvFriendName);
            viewHolder.tvLastMessage = (TextView) view.findViewById(R.id.tvLastMessage);
            viewHolder.tvUnreadCount = (TextView) view.findViewById(R.id.tvUnreadCount);
            viewHolder.ivAvatarImage = (CircleImageView) view.findViewById(R.id.ivAvatarImage);
            viewHolder.tvTime = (TextView) view.findViewById(R.id.tvLastMessageTime);
            viewHolder.llConversationItem = (LinearLayout) view.findViewById(R.id.llConversationListItem);
            viewHolder.pbLoading = (ProgressBar) view.findViewById(R.id.pbLoading);

            // store the holder with the view.
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) view.getTag();
        }

        ConversationData conversationData = conversationList.get(position);

        //check if isLoading flag is true
        //if yes, display the loading bar and hide conversation, else vice-versa
        if (conversationData.isLoading()) {
            viewHolder.llConversationItem.setVisibility(View.GONE);
            viewHolder.pbLoading.setVisibility(View.VISIBLE);
        } else {
            viewHolder.pbLoading.setVisibility(View.GONE);
            viewHolder.llConversationItem.setVisibility(View.VISIBLE);

            viewHolder.tvFriendName.setText(conversationData.getFriendName());
            viewHolder.tvLastMessage.setText(conversationData.getLastMessage());
            if (conversationData.getUnreadCount() > 0) {
                viewHolder.tvUnreadCount.setVisibility(View.VISIBLE);
                viewHolder.tvUnreadCount.setText(String.valueOf(conversationData.getUnreadCount()));
            } else {
                viewHolder.tvUnreadCount.setVisibility(View.INVISIBLE);
            }
            try {
                viewHolder.tvTime.setText(CommonUtils.formatToYesterdayOrToday(context, conversationData.getLastMessageReceivedTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Picasso.with(context).load(conversationData.getAvatarImageURL()).into(viewHolder.ivAvatarImage);
        }
        // Return the completed view to render on screen
        return view;
    }

    public void setConversationList(List<ConversationData> conversationList) {
        this.conversationList = conversationList;
    }
}
