package com.seenu.questionandanswer.fragments;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.seenu.questionandanswer.R;
import com.seenu.questionandanswer.files.DateTimeParser;
import com.seenu.questionandanswer.files.Utils;
import com.seenu.questionandanswer.pojo.QuestAnsObj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

	private TextView titleTv;
	private TextView createdTv;
	private TextView updatedTv;
	private TextView levelTv;
	private TextView questTv;
	private TextView solTv;
	private ImageView questIv;

	private QuestAnsObj obj;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.lv_details, null);

		titleTv = (TextView) view.findViewById(R.id.textView1);
		createdTv = (TextView) view.findViewById(R.id.textView2);
		updatedTv = (TextView) view.findViewById(R.id.textView3);
		levelTv = (TextView) view.findViewById(R.id.textView4);
		questTv = (TextView) view.findViewById(R.id.textView5);
		solTv = (TextView) view.findViewById(R.id.textView6);
		questIv = (ImageView) view.findViewById(R.id.imageView1);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		populateData();

	}

	public void init(QuestAnsObj obj) {
		this.obj = obj;
	}

	public void updateUrl(QuestAnsObj obj) {

		this.obj = obj;

		populateData();

	}

	private void populateData() {
		// TODO Auto-generated method stub

		String title = obj.title;
		String createdDate = obj.created_at;
		String updatedDate = obj.updated_at;
		String level = obj.difficulty;
		String question = "<b>Question:</b> " + obj.question;
		String solution = "<b>Solution:</b> " + obj.solution;
		String image = obj.image_url;

		titleTv.setText(title);
		createdTv.setText("created: "
				+ DateTimeParser.startEndTime(createdDate));
		updatedTv.setText("Last Updated: "
				+ DateTimeParser.startEndTime(updatedDate));
		levelTv.setText(level);
		questTv.setText(Html.fromHtml(question));
		solTv.setText(Html.fromHtml(solution));

		UrlImageViewHelper.setUrlDrawable(questIv, Utils.url_images + image,
				R.drawable.ic_launcher);

	}
}
