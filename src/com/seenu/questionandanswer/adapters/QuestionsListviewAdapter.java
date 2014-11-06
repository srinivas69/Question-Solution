package com.seenu.questionandanswer.adapters;

import java.util.List;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.seenu.questionandanswer.R;
import com.seenu.questionandanswer.files.Utils;
import com.seenu.questionandanswer.pojo.QuestAnsObj;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionsListviewAdapter extends BaseAdapter {

	private Context context;
	private List<QuestAnsObj> qaobj;

	public QuestionsListviewAdapter(Context context) {
		// TODO Auto-generated constructor stub

		this.context = context;
	}

	public QuestionsListviewAdapter(Context context, List<QuestAnsObj> qaobj) {
		// TODO Auto-generated constructor stub

		this.context = context;
		this.qaobj = qaobj;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return qaobj.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return qaobj.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.lv_item, null);
			holder = new ViewHolder();

			holder.imageViewAdpIv = (ImageView) convertView
					.findViewById(R.id.imageView1);
			holder.titleAdpTv = (TextView) convertView
					.findViewById(R.id.textView1);
			holder.questAdpTv = (TextView) convertView
					.findViewById(R.id.textView2);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		String title = qaobj.get(position).getTitle();
		String question = "<b>Question:</b> "
				+ qaobj.get(position).getQuestion();
		String image = qaobj.get(position).getImage_url();

		holder.titleAdpTv.setText(title);
		holder.questAdpTv.setText(Html.fromHtml(question));

		UrlImageViewHelper.setUrlDrawable(holder.imageViewAdpIv,
				Utils.url_images + image, R.drawable.ic_launcher);

		return convertView;

	}

	private static class ViewHolder {

		private ImageView imageViewAdpIv;
		private TextView titleAdpTv;
		private TextView questAdpTv;
	}
}
