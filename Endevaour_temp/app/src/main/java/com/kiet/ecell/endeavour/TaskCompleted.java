package com.kiet.ecell.endeavour;

import org.json.JSONException;

public interface TaskCompleted {
	void onTaskCompleted(String result, int resultType) throws JSONException;

}
