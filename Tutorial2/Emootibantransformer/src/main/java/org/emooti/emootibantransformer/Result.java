package org.emooti.emootibantransformer;


import java.io.IOException;

import java.util.*;
public class Result {

		public Object find(String input) throws IOException
			{
			//String Result ="{\"results\": [ {\"team\": \"Team A\",\"tags\": [{\"hashTag\": \"#Code\",\"green\": 28,\"orange\": 21,\"red\": 45,\"total_votes\": 94,\"date\": 1488957464291                       }]}],\"total_team_amount\": 2}";
			//String Result   = "{\"results\":[{\"team\":\"Team A\", \"tags\": [{\"orange\":\"4\",\"red\":\"7\",\"date\":\"201703251011560129\",\"green\":\"2\",\"total_votes\":\"13\",\"hashTag\":\"#Code\"}]}],\"total_team_amount\":1}";
			String Result = "{\"results\":[{\"team\":\"Team A\",     \"tags\": [{\"orange\":\"2\",\"red\":\"2\",\"green\":\"2\"         ,\"total_votes\":\"2\",\"date\":\"20170331754440524\",                                    \"hashTag\":\"#Code\"}]}],\"total_team_amount\":1}";
		return Result;
		}
}