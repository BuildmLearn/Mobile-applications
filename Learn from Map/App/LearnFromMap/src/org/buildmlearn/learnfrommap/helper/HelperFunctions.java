package org.buildmlearn.learnfrommap.helper;

import java.util.Random;

public class HelperFunctions {

	public static void ShuffleArray(String[] ar)
	{
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

}
