package com.nytimes.stringers.views.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nytimes.data.entity.InvestigationModel;
import com.nytimes.stringers.R;
import com.nytimes.stringers.models.Message;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class InvestigationPageAdapter extends PagerAdapter {

    private final String[] PAGE_TITLES = {"Info", "Chat"};
    private LayoutInflater layoutInflater;
    private InvestigationModel model;

    public static final String USER_ID_KEY = "userId";
    private static final String TAG = InvestigationPageAdapter.class.getName();
    private static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;
    private static String sUserId;

    private EditText etMessage;
    private Button btSend;
    private ListView lvChat;
    private ArrayList<Message> mMessages;
    private ChatListAdapter mAdapter;
    private Context context;

    // Create a handler which can run code periodically
    private Handler handler = new Handler();

    public InvestigationPageAdapter(Context context, InvestigationModel model) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.model = model;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int res_id = position == 0 ? R.layout.investigation_info : R.layout.investigation_chat;
        View view =  layoutInflater.inflate(res_id, null);

        if (res_id == R.layout.investigation_info) {
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(model.getTitle());
            TextView description = (TextView) view.findViewById(R.id.description);
            description.setText(model.getDescription());
        }

        if (res_id == R.layout.investigation_chat) {
            // Find the text field and button
            etMessage = (EditText) view.findViewById(R.id.etMessage);
            btSend = (Button) view.findViewById(R.id.btSend);
            lvChat = (ListView) view.findViewById(R.id.lvChat);
            initParse();
        }

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
}

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private void initParse() {
        // User login
        if (ParseUser.getCurrentUser() != null) { // start with existing user
            startWithCurrentUser();
        } else { // If not logged in, login as a new anonymous user
            login();
        }

        // Run the runnable object defined every 100ms
        handler.postDelayed(runnable, 100);
    }

    // Create an anonymous user using ParseAnonymousUtils and set sUserId
    private void login() {
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.d("**", "Anonymous login failed: " + e.toString());
                } else {
                    startWithCurrentUser();
                }
            }
        });
    }
    // Get the userId from the cached currentUser object
    private void startWithCurrentUser() {
        sUserId = ParseUser.getCurrentUser().getObjectId();
        setupMessagePosting();
    }

    // Setup button event handler which posts the entered message to Parse
    private void setupMessagePosting() {
        // When send button is clicked, create message object on Parse
        mMessages = new ArrayList<Message>();
        mAdapter = new ChatListAdapter(context, sUserId, mMessages);
        lvChat.setAdapter(mAdapter);
        btSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                String body = etMessage.getText().toString();
                // Use Message model to create new messages now
                Message message = new Message();
                message.setUserId(sUserId);
                message.setBody(body);
                message.setName("Alexio");
                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
//                        Toast.makeText(v.getContext(), "Successfully created message on Parse",
//                                Toast.LENGTH_SHORT).show();
                        receiveMessage();
                    }
                });
                etMessage.setText("");
            }
        });
    }

    // Query messages from Parse so we can load them into the chat adapter
    private void receiveMessage() {
        // Construct query to execute
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        // Configure limit and sort order
        query.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);
        query.orderByAscending("createdAt");
        // Execute query to fetch all messages from Parse asynchronously
        // This is equivalent to a SELECT query with SQL
        query.findInBackground(new FindCallback<Message>() {
            public void done(List<Message> messages, ParseException e) {
                if (e == null) {
                    mMessages.clear();
                    mMessages.addAll(messages);
                    mAdapter.notifyDataSetChanged(); // update adapter
                    lvChat.invalidate(); // redraw listview
                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });
    }

    // Defines a runnable which is run every 100ms
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            refreshMessages();
            handler.postDelayed(this, 100);
        }
    };

    private void refreshMessages() {
        receiveMessage();
    }

}
