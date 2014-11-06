package com.seenu.questionandanswer;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class QuestionAndAnswer extends Application {

	private static QuestionAndAnswer questans;

	private RequestQueue mRequestQueue;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		questans = this;
	}

	public static QuestionAndAnswer getInstance() {
		return questans;
	}

	// Volley Library Methods
	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		// req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		// req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

}
