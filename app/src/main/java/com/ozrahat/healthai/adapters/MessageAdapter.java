package com.ozrahat.healthai.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ozrahat.healthai.R;
import com.ozrahat.healthai.models.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private final Context context;
    private final List<ChatMessage> messages;

    public static final int MESSAGE_TYPE_LEFT = 0;
    public static final int MESSAGE_TYPE_RIGHT = 1;

    public MessageAdapter(Context context, List<ChatMessage> messages) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view;
        if(viewType == MESSAGE_TYPE_LEFT){
            view = LayoutInflater.from(context).inflate(R.layout.chat_bubble_left, parent, false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.chat_bubble_right, parent, false);
        }
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        final ChatMessage chatMessage = messages.get(position);

        holder.message.setText(chatMessage.message);

        Date date = new Date(chatMessage.date);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        holder.date.setText(sdf.format(date));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView message;
        public TextView date;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            message = itemView.findViewById(R.id.chat_bubble_message);
            date = itemView.findViewById(R.id.chat_bubble_date);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).sender == 0){
            return MESSAGE_TYPE_LEFT;
        }else {
            return MESSAGE_TYPE_RIGHT;
        }
    }
}