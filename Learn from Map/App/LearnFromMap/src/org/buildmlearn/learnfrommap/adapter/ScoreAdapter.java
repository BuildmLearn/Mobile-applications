package org.buildmlearn.learnfrommap.adapter;

import java.util.ArrayList;
import java.util.List;

import org.buildmlearn.learnfrommap.R;
import org.buildmlearn.learnfrommap.TextViewPlus;
import org.buildmlearn.learnfrommap.questionmodule.UserAnsweredData;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ScoreAdapter extends ArrayAdapter<UserAnsweredData> {
	
	private Context mContext;
	private ArrayList<UserAnsweredData> mData;
	private int mListResourceId;

	public ScoreAdapter(Context context, int listResourceId,
			ArrayList<UserAnsweredData> data) {
		super(context, listResourceId, data);
		this.mListResourceId = listResourceId;
		this.mContext = context;
		this.mData = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		UserAnsweredData userAnswer = mData.get(position);
		AnswerHolder holder  = null;
		if(row == null)
		{
			holder = new AnswerHolder();
			LayoutInflater inflator =  ((Activity)mContext).getLayoutInflater();
			row = inflator.inflate(mListResourceId, parent, false);
			holder.question = (TextViewPlus)row.findViewById(R.id.list_question);
			holder.option1 = (TextViewPlus)row.findViewById(R.id.option1);
			holder.option2 = (TextViewPlus)row.findViewById(R.id.option2);
			holder.option3 = (TextViewPlus)row.findViewById(R.id.option3);
			holder.option4 = (TextViewPlus)row.findViewById(R.id.option4);
			row.setTag(holder);
		}
		else
		{
			holder = (AnswerHolder)row.getTag();
		}
		holder.question.setText(userAnswer.getmQuestion());
		return row;
	}

	static class AnswerHolder
	{
		public TextViewPlus question;
		public TextViewPlus option1;
		public TextViewPlus option2;
		public TextViewPlus option3;
		public TextViewPlus option4;
		
		
	}




}
