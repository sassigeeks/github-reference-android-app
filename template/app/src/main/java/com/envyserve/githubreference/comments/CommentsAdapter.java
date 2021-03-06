package com.envyserve.githubreference.comments;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.envyserve.githubreference.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentsAdapter extends ArrayAdapter<Comment> {
	
	private Context context;
	private int type;
	
    public CommentsAdapter(Context context, List<Comment> objects, int type) {
        super(context, 0, objects);
        this.context = context;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Comment comment = getItem(position);
        CommentViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_comments_row, parent, false);
            viewHolder = new CommentViewHolder();
            viewHolder.ivProfilePhoto = (ImageView) convertView.findViewById(R.id.ivProfilePhoto);
            viewHolder.tvUsername = (TextView)convertView.findViewById(R.id.tvUsername);
            viewHolder.tvComment = (TextView) convertView.findViewById(R.id.tvComment);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (CommentViewHolder)convertView.getTag();
        }

        viewHolder.ivProfilePhoto.setImageDrawable(null);
        if (comment.profileUrl != null){
        	viewHolder.ivProfilePhoto.setVisibility(View.VISIBLE);

            Picasso.with(context).load(comment.profileUrl).into(viewHolder.ivProfilePhoto);
        } else {
        	viewHolder.ivProfilePhoto.setVisibility(View.GONE);
        }

        viewHolder.tvUsername.setText(comment.username);

        viewHolder.tvComment.setText(Html.fromHtml(comment.text));
        
        if (type == CommentsActivity.WORDPRESS){
    		LinearLayout lineView = (LinearLayout) convertView.findViewById(R.id.lineView);
        	//if (comment.parentId == latestId){
        	//	lineCount = lineCount + 1;
        	//}
        	
        	//if (comment.parentId == 0){
           /// 	lineCount = 0;
        	//}
        	
        	lineView.removeAllViews();
        	for (int i = 0; i < comment.linesCount; i++) {
        		View line = View.inflate(context, R.layout.activity_comment_sub, null);
        		lineView.addView(line);
        	}
        }

        return convertView;
    }
    
    public class CommentViewHolder {
        ImageView ivProfilePhoto;
        TextView tvUsername;
        TextView tvComment;
    }

}
