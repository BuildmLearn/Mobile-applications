package org.buildmlearn.learnfrommap.adapter;

import java.util.ArrayList;
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
import android.widget.ImageView;

public class ScoreAdapter extends ArrayAdapter<UserAnsweredData> {


	private static final int MCQ = 0;
	private static final int FILL = 1;
	private static final int PIN = 2;
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
		if(getItemViewType(position) == MCQ)
		{
			View row = convertView;
			UserAnsweredData userAnswer = mData.get(position);
			AnswerHolderMcq holder  = null;
			if(row == null)
			{
				holder = new AnswerHolderMcq();
				LayoutInflater inflator =  ((Activity)mContext).getLayoutInflater();
				row = inflator.inflate(mListResourceId, parent, false);
				holder.question = (TextViewPlus)row.findViewById(R.id.list_question);
				holder.option1 = (TextViewPlus)row.findViewById(R.id.option1);
				holder.option2 = (TextViewPlus)row.findViewById(R.id.option2);
				holder.option3 = (TextViewPlus)row.findViewById(R.id.option3);
				holder.option4 = (TextViewPlus)row.findViewById(R.id.option4);
				holder.isCorrect = (ImageView)row.findViewById(R.id.answer_status);
				row.setTag(holder);
			}
			else
			{
				holder = (AnswerHolderMcq)row.getTag();
			}
			holder.data = userAnswer;
			holder.question.setText("Ques" + (position + 1) + ": " + holder.data.getmQuestion());

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
						holder.option1.setBackgroundResource(R.drawable.right_answer);
					}
					else if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[1]))
					{
						holder.option2.setBackgroundResource(R.drawable.right_answer);
					}
					else if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[2]))
					{
						holder.option3.setBackgroundResource(R.drawable.right_answer);
					}
					else
					{
						holder.option4.setBackgroundResource(R.drawable.right_answer);
					}
				}
				else
				{
					if(!holder.data.getmUserAnswer().equals(holder.data.getmAnswer()))
					{
						if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[0]))
						{
							holder.option1.setBackgroundResource(R.drawable.wrong_answer);
						}
						else if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[1]))
						{
							holder.option2.setBackgroundResource(R.drawable.wrong_answer);
						}
						else if(holder.data.getmUserAnswer().equals(holder.data.getmOptions()[2]))
						{
							holder.option3.setBackgroundResource(R.drawable.wrong_answer);
						}
						else
						{
							holder.option4.setBackgroundResource(R.drawable.wrong_answer);
						}
					}
				}
			}
			if(holder.data.getmAnswer().equals(holder.data.getmOptions()[0]))
			{
				holder.option1.setBackgroundResource(R.drawable.right_answer);
			}
			else if(holder.data.getmAnswer().equals(holder.data.getmOptions()[1]))
			{
				holder.option2.setBackgroundResource(R.drawable.right_answer);
			}
			else if(holder.data.getmAnswer().equals(holder.data.getmOptions()[2]))
			{
				holder.option3.setBackgroundResource(R.drawable.right_answer);
			}
			else
			{
				holder.option4.setBackgroundResource(R.drawable.right_answer);
			}
			if(holder.data.isAnswerCorrect())
			{
				holder.isCorrect.setImageResource(R.drawable.ic_action_accept);
			}
			else
			{
				holder.isCorrect.setImageResource(R.drawable.ic_action_cancel);
			}

			return row;

		}
		else if(getItemViewType(position) == FILL)
		{

			View row = convertView;
			UserAnsweredData userAnswer = mData.get(position);
			AnswerHolderFill holder  = null;
			if(row == null)
			{
				holder = new AnswerHolderFill();
				LayoutInflater inflator =  ((Activity)mContext).getLayoutInflater();
				row = inflator.inflate(R.layout.listview_row_fill, parent, false);
				holder.question = (TextViewPlus)row.findViewById(R.id.list_question);
				holder.answer = (TextViewPlus)row.findViewById(R.id.answer);
				holder.isCorrect = (ImageView)row.findViewById(R.id.answer_status);
				row.setTag(holder);
			}
			else
			{
				holder = (AnswerHolderFill)row.getTag();
			}
			holder.data = userAnswer;
			holder.question.setText("Ques" + (position + 1) + ": " + holder.data.getmQuestion());
			holder.answer.setText("Answer: " + holder.data.getmAnswer());
			if(holder.data.isAnswerCorrect())
			{
				holder.isCorrect.setImageResource(R.drawable.ic_action_accept);
			}
			else
			{
				holder.isCorrect.setImageResource(R.drawable.ic_action_cancel);
			}
			return row;
		}
		else
		{
			View row = convertView;
			UserAnsweredData userAnswer = mData.get(position);
			AnswerHolderPin holder  = null;
			if(row == null)
			{
				holder = new AnswerHolderPin();
				LayoutInflater inflator =  ((Activity)mContext).getLayoutInflater();
				row = inflator.inflate(R.layout.listview_row_map, parent, false);
				holder.question = (TextViewPlus)row.findViewById(R.id.list_question);
				holder.isCorrect = (ImageView)row.findViewById(R.id.answer_status);
				row.setTag(holder);
			}
			else
			{
				holder = (AnswerHolderPin)row.getTag();
			}
			holder.data = userAnswer;
			holder.question.setText("Ques" + (position + 1) + ": " + holder.data.getmQuestion());
			if(holder.data.isAnswerCorrect())
			{
				holder.isCorrect.setImageResource(R.drawable.ic_action_accept);
			}
			else
			{
				holder.isCorrect.setImageResource(R.drawable.ic_action_cancel);
			}
			return row;
		}
	}

	@Override
	public int getViewTypeCount() {
		return 3;
	}

	@Override
	public int getItemViewType(int position) {
		UserAnsweredData userData = mData.get(position);
		if(userData.getmQuestionType() ==  Type.Fill)
		{
			return FILL;
		}
		else if(userData.getmQuestionType() ==  Type.Mcq)
		{
			return MCQ;
		}
		else
		{
			return PIN;
		}
	}

	static class AnswerHolderMcq
	{
		public TextViewPlus question;
		public TextViewPlus option1;
		public TextViewPlus option2;
		public TextViewPlus option3;
		public TextViewPlus option4;
		public UserAnsweredData data;
		public ImageView isCorrect;


	}

	static class AnswerHolderFill
	{
		public TextViewPlus answer;
		public UserAnsweredData data;
		public TextViewPlus question;
		public ImageView isCorrect;

	}

	static class AnswerHolderPin
	{
		public UserAnsweredData data;
		public TextViewPlus question;
		public ImageView isCorrect;
	}




}
