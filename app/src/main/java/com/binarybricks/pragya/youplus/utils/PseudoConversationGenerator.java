package com.binarybricks.pragya.youplus.utils;

import android.text.TextUtils;

import com.binarybricks.pragya.youplus.datamodel.ConversationData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by PRAGYA on 8/2/2016.
 */
public class PseudoConversationGenerator {

    //since this is test data, not externalizing in strings.xml

    private static int[] id = {100, 101, 102, 103, 104, 105, 106, 107, 108};
    private static String[] friendName = {"John", "Andrew", "Mathew", "Adam", "Justin", "Micheal", "Lucy", "Anna", "Macy"};
    private static String[] messages = {"How are you ?",
            "I want to add you as a friend",
            "Let's chat", "Call me", "Talk to you later", "In a meeting", "Hey..wassup!!", "Let's go Party", "I am offline"
    };
    private static String[] avatarImageUrl = {"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTYYwscgxsNhCSBonaZqZeKE-mbnMSj9wGGlYP8yOTx96u4KhEw6Q",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQpdoPqL6magv0lHaCR3r4yK0M69aIZRAJ0L7kGuchY6lF4ZhMfFfMdtA",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTV0ni2V0nT_ab1uwd7Wpzv7ncDYYxvHHAPulbBrapwdjbePdZk1w",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTjjRVhxwpnwV0vcwEhDRwqllsFIgq5QCJlRl1o2rmc1jGRXdrPPA",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ48Mej_FujaqA7gbO1ePs2_OQTm7Yt_7D5L466KGpK2lhHszdpTAmHOQ",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTYYwscgxsNhCSBonaZqZeKE-mbnMSj9wGGlYP8yOTx96u4KhEw6Q",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQpdoPqL6magv0lHaCR3r4yK0M69aIZRAJ0L7kGuchY6lF4ZhMfFfMdtA",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTV0ni2V0nT_ab1uwd7Wpzv7ncDYYxvHHAPulbBrapwdjbePdZk1w",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTjjRVhxwpnwV0vcwEhDRwqllsFIgq5QCJlRl1o2rmc1jGRXdrPPA"};

    private static int[] unreadCount = {2, 4, 5, 1, 7, 0, 0, 0, 2};
    private static String[] receivedTime = {"8/2/2016", "8/1/2016", "8/1/2016", "8/2/2016", "6/2/2016", "8/1/2016", "8/1/2016", "8/2/2016", "7/22/2016"};

    public static ConversationData getPseudoConversation(long friendId, String conversationTime) {
        ConversationData conversationData = new ConversationData();
        int randomFriend = getRandomNo();
        if (friendId != 0) {
            conversationData.setFriendId(friendId);
        } else {
            conversationData.setFriendId((long) id[randomFriend]);
        }
        conversationData.setFriendName(friendName[randomFriend]);
        conversationData.setLastMessage(messages[randomFriend]);
        conversationData.setAvatarImageURL(avatarImageUrl[randomFriend]);
        conversationData.setUnreadCount(unreadCount[randomFriend]);
        if (TextUtils.isEmpty(conversationTime)) {
            conversationData.setLastMessageReceivedTime(receivedTime[randomFriend]);
        } else {
            conversationData.setLastMessageReceivedTime(conversationTime);
        }
        return conversationData;
    }

    private static int getRandomNo() {
        Random r = new Random();
        int Low = 0;
        int High = 9;
        return r.nextInt(High - Low) + Low;
    }

    public static List<ConversationData> getFiveConversation() {
        List<ConversationData> friendList = new ArrayList<>();
        friendList.add(getPseudoConversation(0,""));
        friendList.add(getPseudoConversation(0,""));
        friendList.add(getPseudoConversation(0,""));
        friendList.add(getPseudoConversation(0,""));
        friendList.add(getPseudoConversation(0,""));
        return friendList;
    }
}
