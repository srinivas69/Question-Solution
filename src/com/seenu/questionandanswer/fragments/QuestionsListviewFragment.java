package com.seenu.questionandanswer.fragments;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONArray;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seenu.questionandanswer.QuestionAndAnswer;
import com.seenu.questionandanswer.R;
import com.seenu.questionandanswer.adapters.QuestionsListviewAdapter;
import com.seenu.questionandanswer.files.Utils;
import com.seenu.questionandanswer.interfaces.ChangeQuestionListener;
import com.seenu.questionandanswer.pojo.QuestAnsObj;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsListviewFragment extends Fragment {

	private QuestionsListviewAdapter adapter;
	private ListView lv;
	private ProgressBar aBar;
	private TextView loadingTv;
	private View footerView;

	private List<QuestAnsObj> qaobj;

	// Flag for current page
	private int current_page = 1;
	private View view;

	// OnURLSelectedListener mListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		if (null == view) {
			view = inflater.inflate(R.layout.quests_lv, null);
			lv = (ListView) view.findViewById(R.id.listView1);
			aBar = (ProgressBar) view.findViewById(R.id.progressBar1);
			loadingTv = (TextView) view.findViewById(R.id.textView1);
		} else
			((ViewGroup) view.getParent()).removeView(view);
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub

		if (!(activity instanceof ChangeQuestionListener))
			throw new ClassCastException();
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		footerView = getActivity().getLayoutInflater().inflate(
				R.layout.loadmore_lv, null);

		if (qaobj == null) {

			getDataFromtheServer();

		}

		lv.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

				int threshold = 1;
				int count = lv.getCount();

				if (scrollState == SCROLL_STATE_IDLE) {
					if (lv.getLastVisiblePosition() >= count - threshold) {
						// Execute loadMoreFromServer method
						loadMoreFromServer();
					}
				}

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				int newConfig = getActivity().getResources().getConfiguration().orientation;

				System.out.println("Configuration: " + newConfig);

				QuestAnsObj obj = (QuestAnsObj) adapter.getItem(position);
				((ChangeQuestionListener) getActivity()).onQuestionChange(obj);

			}
		});

	}

	private void getDataFromtheServer() {
		// TODO Auto-generated method stub

		aBar.setVisibility(View.VISIBLE);
		loadingTv.setVisibility(View.VISIBLE);

		JsonArrayRequest jsonArryReq = new JsonArrayRequest(Utils.url
				+ current_page, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				String result = response.toString();
				System.out.println(result);

				// parsing json with object using Gson
				Gson gson = new Gson();
				Type listOfTestObject = new TypeToken<List<QuestAnsObj>>() {
				}.getType();
				qaobj = gson.fromJson(result, listOfTestObject);

				System.out.println("qaobj:" + qaobj.size());

				adapter = new QuestionsListviewAdapter(getActivity(), qaobj);
				lv.setAdapter(adapter);
				lv.addFooterView(footerView);

				// increment current page
				current_page += 1;

				lv.setOnScrollListener(new OnScrollListener() {

					@Override
					public void onScrollStateChanged(AbsListView view,
							int scrollState) {
						// TODO Auto-generated method stub

						int threshold = 1;
						int count = lv.getCount();

						if (scrollState == SCROLL_STATE_IDLE) {
							if (lv.getLastVisiblePosition() >= count
									- threshold) {
								// Execute loadMoreFromServer method
								loadMoreFromServer();
							}
						}

					}

					@Override
					public void onScroll(AbsListView view,
							int firstVisibleItem, int visibleItemCount,
							int totalItemCount) {
						// TODO Auto-generated method stub

					}
				});

				aBar.setVisibility(View.GONE);
				loadingTv.setVisibility(View.GONE);

			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

				NetworkResponse response = error.networkResponse;

				if (response != null) {

				} else {

					Toast.makeText(getActivity(),
							"Something went wrong! Please try again",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		QuestionAndAnswer.getInstance().addToRequestQueue(jsonArryReq);

	}

	private void loadMoreFromServer() {

		String url = Utils.url + current_page;

		JsonArrayRequest jArryReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray response) {
						// TODO Auto-generated method stub

						String result = response.toString();
						System.out.println(result);

						// parsing json with object using Gson
						Gson gson = new Gson();
						Type listOfTestObject = new TypeToken<List<QuestAnsObj>>() {
						}.getType();
						List<QuestAnsObj> qaobj2 = gson.fromJson(result,
								listOfTestObject);

						if (qaobj2.size() != 0) {

							qaobj.addAll(qaobj2);

							// Locate listview last item
							int position = lv.getLastVisiblePosition();
							// Pass the results into ListViewAdapter.java
							adapter.notifyDataSetChanged();

							// Binds the Adapter to the ListView
							lv.setAdapter(adapter);
							// Show the latest retrived results on the top
							lv.setSelectionFromTop(position, 0);

							// increment current page
							current_page += 1;

						} else
							lv.removeFooterView(footerView);

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});

		QuestionAndAnswer.getInstance().addToRequestQueue(jArryReq);

	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

}
