package com.binarybricks.pragya.youplus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.binarybricks.pragya.youplus.datamodel.ConversationData;
import com.binarybricks.pragya.youplus.adapters.ConversationListAdapter;
import com.binarybricks.pragya.youplus.utils.PseudoConversationGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ConversationHomeActivity extends AppCompatActivity {

    public static final int DELAY_MILLIS = 1500;
    public static final String FRIEND_ID = "friendId";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";

    @BindView(R.id.lvConversationList)
    ListView lvConversationList;

    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;

    private List<ConversationData> conversationList;
    private ConversationListAdapter conversationListAdapter;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_home);
        ButterKnife.bind(this);

        sdf = new SimpleDateFormat(MM_DD_YYYY);
        conversationList = PseudoConversationGenerator.getFiveConversation();
        sortConversation();
        conversationListAdapter = new ConversationListAdapter(ConversationHomeActivity.this, conversationList);
        lvConversationList.setAdapter(conversationListAdapter);
    }
    @OnItemClick(R.id.lvConversationList)
    void openConversationDetails(int position) {
        //in ideal scenarios, we will have something like conversation id
        // which can be used to fetch the conversation from the server.
        ConversationData conversationData = conversationList.get(position);
        Intent intent = new Intent(ConversationHomeActivity.this,ConversationDetails.class);
        //add conversation id as intent extra
        startActivity(intent);
    }
    @OnClick(R.id.fabAdd)
    void addNewConversation(View v){
        ConversationData newConversationData = PseudoConversationGenerator.getPseudoConversation(501,"8/4/2016");
        newConversationData.setLoading(true);
        conversationList.add(newConversationData);
        sortConversation();
        conversationListAdapter.setConversationList(conversationList);
        conversationListAdapter.notifyDataSetChanged();
        postConversationAfterDelay(newConversationData.getFriendId());
    }

    //helper method to sort conversation list based on date.
    //Most recent conversation will appear on top.
    private void sortConversation(){
        Collections.sort(conversationList, new Comparator<ConversationData>() {
            @Override
            public int compare(ConversationData lhs, ConversationData rhs) {
                try {
                    return sdf.parse(rhs.getLastMessageReceivedTime()).compareTo(sdf.parse(lhs.getLastMessageReceivedTime()));
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
                return 0;
            }
        });
    }

    //handler for 1.5 sec loading
    private void postConversationAfterDelay(final long conversationId){
        final Handler mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                long friendId = msg.getData().getLong(FRIEND_ID);
                for (int i = 0; i < conversationList.size(); i++) {
                    if(conversationList.get(i).getFriendId() == friendId){
                        conversationList.get(i).setLoading(false);
                    }
                }
                conversationListAdapter.setConversationList(conversationList);
                conversationListAdapter.notifyDataSetChanged();
                return false;
            }
        });
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putLong(FRIEND_ID, conversationId);
        message.setData(bundle);
        mHandler.sendMessageDelayed(message, DELAY_MILLIS);
    }
}
