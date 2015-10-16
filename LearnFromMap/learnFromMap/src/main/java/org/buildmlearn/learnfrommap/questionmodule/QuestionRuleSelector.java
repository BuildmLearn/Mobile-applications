package org.buildmlearn.learnfrommap.questionmodule;

import java.util.ArrayList;
import java.util.Random;

public class QuestionRuleSelector {

	private ArrayList<XmlQuestion> mRules;
	public enum Rule {None, Classic, Category};
	private Rule mRuleType;
	public static long lastSeed = 0;



	public QuestionRuleSelector(ArrayList<XmlQuestion> rules, Rule ruleType)
	{
		this.mRules = rules;
		this.mRuleType = ruleType;
	}

	public ArrayList<XmlQuestion> selectRules(int count) throws QuestionModuleException
	{
		ArrayList<XmlQuestion> selectedQuestion = new ArrayList<XmlQuestion>();
		if(mRuleType == Rule.Classic)
		{
			boolean captialCheck = false;
			for(XmlQuestion question : mRules)
			{
				if(question.getAnswer().equals("country"))
				{
					mRules.remove(question);
				}
			}
			if(mRules.size() > count )
			{
				for(int i =0; i<count; i++)
				{
					lastSeed = new java.util.Date().getTime() + lastSeed;
					Random random = new Random(lastSeed);
					int randomNo =  random.nextInt(mRules.size());
					XmlQuestion temp = mRules.get(randomNo);
					if(temp.getCode().equals("PPLC"))
					{
						if(!captialCheck)
						{
							captialCheck = true;
							selectedQuestion.add(mRules.get(randomNo));
							mRules.remove(randomNo);
						}
						else
						{
							mRules.remove(randomNo);
						}
					}
					else
					{
						selectedQuestion.add(mRules.get(randomNo));
						mRules.remove(randomNo);
					}
				}

				return selectedQuestion;

			}
			else
			{
				int capitalCount = 0;
				for(XmlQuestion question : mRules)
				{
					if(question.getCode().equals("PPLC"))
					{
						capitalCount++;
					}
				}
				if(capitalCount > 1)
				{
					Random random = new Random(lastSeed + new java.util.Date().getTime());
					int selected = random.nextInt(capitalCount);
					for(int i=0; i<mRules.size(); i++)
					{
						if(i != selected)
						{
							mRules.remove(i);
						}
					}

				}
				if(mRules.size() > 0)
				{
					int extra =  count - mRules.size();
					selectedQuestion = mRules;
					for(int i =0; i<extra; i++)
					{
						lastSeed = new java.util.Date().getTime() + lastSeed;
						Random random = new Random(lastSeed);
						int next = random.nextInt(mRules.size());
						XmlQuestion temp = mRules.get(next);
						if(temp.getCode().equals("PPLC"))
						{
							mRules.remove(i);
						}
						else
						{
							selectedQuestion.add(mRules.get(i));
						}

					}
					return selectedQuestion;
				}
				else
				{
					throw new QuestionModuleException("Insufficient question rules in XML");
				}

			}

		}
		else
		{
			//Normal Question
			for(int i =0; i<count; i++)
			{
				lastSeed = new java.util.Date().getTime() + lastSeed;
				Random random = new Random(lastSeed);
				int next = random.nextInt(mRules.size());
				selectedQuestion.add(mRules.get(next));
			}

			return selectedQuestion;

		}

	}


}
