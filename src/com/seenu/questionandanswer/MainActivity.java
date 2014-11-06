package com.seenu.questionandanswer;

import com.seenu.questionandanswer.fragments.DetailsFragment;
import com.seenu.questionandanswer.fragments.QuestionsListviewFragment;
import com.seenu.questionandanswer.interfaces.ChangeQuestionListener;
import com.seenu.questionandanswer.pojo.QuestAnsObj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends ActionBarActivity implements
		ChangeQuestionListener {

	private Fragment lvFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fm = getSupportFragmentManager();

		if (savedInstanceState == null) {

			FragmentTransaction t = fm.beginTransaction();
			lvFrag = new QuestionsListviewFragment();
			t.add(R.id.FrameLayout1, lvFrag);
			t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			t.commit();
		} else {
			lvFrag = (Fragment) fm.findFragmentById(R.id.FrameLayout1);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onQuestionChange(QuestAnsObj obj) {
		// TODO Auto-generated method stub

		if (findViewById(R.id.FrameLayout2) != null) {
			DetailsFragment wvf = (DetailsFragment) getSupportFragmentManager()
					.findFragmentById(R.id.FrameLayout2);

			if (wvf == null) {
				System.out.println("Dual fragment - 1");
				wvf = new DetailsFragment();
				wvf.init(obj);
				// We are in dual fragment (Tablet and so on)
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();

				// wvf.updateUrl(link);
				ft.replace(R.id.FrameLayout2, wvf);
				ft.commit();

			} else {
				Log.d("SwA", "Dual Fragment update");
				wvf.updateUrl(obj);
			}
		} else {
			DetailsFragment frg = new DetailsFragment();
			frg.init(obj);
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			ft.replace(R.id.FrameLayout1, frg, "Detail_Fragment2");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();
		}

	}

}
