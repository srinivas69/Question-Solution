package com.seenu.questionandanswer.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestAnsObj implements Parcelable {

	public String created_at;
	public String difficulty;
	public String id;
	public String image_url;
	public String question;
	public String solution;
	public String title;
	public String updated_at;

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

		dest.writeString(created_at);
		dest.writeString(difficulty);
		dest.writeString(id);
		dest.writeString(image_url);
		dest.writeString(question);
		dest.writeString(solution);
		dest.writeString(title);
		dest.writeString(updated_at);

	}

	private QuestAnsObj(Parcel in) {

		this.created_at = in.readString();
		this.difficulty = in.readString();
		this.id = in.readString();
		this.image_url = in.readString();
		this.question = in.readString();
		this.solution = in.readString();
		this.title = in.readString();
		this.updated_at = in.readString();

	}

	public static final Parcelable.Creator<QuestAnsObj> CREATOR = new Parcelable.Creator<QuestAnsObj>() {

		@Override
		public QuestAnsObj createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new QuestAnsObj(source);
		}

		@Override
		public QuestAnsObj[] newArray(int size) {
			// TODO Auto-generated method stub
			return new QuestAnsObj[size];
		}
	};

}
