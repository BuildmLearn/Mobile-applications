package org.buildmlearn.learnfrommap.adapter;

import java.util.ArrayList;
import java.util.List;

import org.buildmlearn.learnfrommap.R;
import org.buildmlearn.learnfrommap.TextViewPlus;
import org.buildmlearn.learnfrommap.questionmodule.GeneratedQuestion.Type;
import org.buildmlearn.learnfrommap.questionmodule.UserAnsweredData;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
		holder.data = userAnswer;
		holder.question.setText(holder.data.getmQuestion());
		if(holder.data.getmQuestionType() == Type.Mcq)
		{
			holder.option1.setText(holder.data.getmOptions()[0]);
			holder.option2.setText(holder.data.getmOptions()[1]);
			holder.option3.setText(holder.data.getmOptions()[2]);
			holder.option4.setText(holder.data.getmOptions()[3]);
			holder.option1.setBackgroundColor(Color.TRANSPARENT);
			holder.option2.setBackgroundColor(Color.TRANSPARENT);
			holder.option3.setBackgroundColor(Color.TRANSPARENT);
			holder.option4.setBackgroundColor(Color.TRANSPARENT);
			
			
			if(userAnswer.isAnswered())
			{
				if(holder.data.getmUserAnswer().equals(holder.data.getmAnswer()))
				{
					if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[0]))
					{
						holder.option1.setBackgroundColor(Color.argb(128, 0, 255, 0));
					}
					else if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[1]))
					{
						holder.option2.setBackgroundColor(Color.argb(128, 0, 255, 0));
					}
					else if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[2]))
					{
						holder.option3.setBackgroundColor(Color.argb(128, 0, 255, 0));
					}
					else
					{
						holder.option4.setBackgroundColor(Color.argb(128, 0, 255, 0));
					}
				}
				else
				{
					if(!holder.data.getmUserAnswer().equals(holder.data.getmAnswer()))
					{
						if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[0]))
						{
							holder.option1.setBackgroundColor(Color.argb(128, 255, 0, 0));
						}
						else if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[1]))
						{
							holder.option2.setBackgroundColor(Color.argb(128, 255, 0, 0));
						}
						else if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[2]))
						{
							holder.option3.setBackgroundColor(Color.argb(128, 255, 0, 0));
						}
						else
						{
							holder.option4.setBackgroundColor(Color.argb(128, 255, 0, 0));
						}
					}
				}
			}
			if(holder.data.getmAnswer().equals(holder.data.getmOptions()[0]))
			{
				holder.option1.setBackgroundColor(Color.argb(128, 0, 255, 0));
			}
			else if(holder.data.getmAnswer().equals(holder.data.getmOptions()[1]))
			{
				holder.option2.setBackgroundColor(Color.argb(128, 0, 255, 0));
			}
			else if(holder.data.getmAnswer().equals(holder.data.getmOptions()[2]))
			{
				holder.option3.setBackgroundColor(Color.argb(128, 0, 255, 0));
			}
			else
			{
				holder.option4.setBackgroundColor(Color.argb(128, 0, 255, 0));
			}
		}
		return row;
	}

	static class AnswerHolder
	{
		public TextViewPlus question;
		public TextViewPlus option1;
		public TextViewPlus option2;
		public TextViewPlus option3;
		public TextViewPlus option4;
		public UserAnsweredData data;


	}




}
