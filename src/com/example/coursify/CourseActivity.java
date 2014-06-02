package com.example.coursify;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.os.Build;

public class CourseActivity extends ActionBarActivity {

	private Course course;
	
	TextView rating;
	private RatingBar ratingBar;
	
	private EditText authorEdit;
	private EditText textEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Creating the look of CourseActivity
		//Ratings
		course=(Course)getIntent().getSerializableExtra("Course");
		setTitle(course.getName());
		
		ScrollView view=new ScrollView(this);
		
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		rating=new TextView(this);
		rating.setText(getRatingText());
		layout.addView(rating);
		
		ratingBar=new RatingBar(this);
		ratingBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		ratingBar.setNumStars(5);
		ratingBar.setStepSize(1);
		layout.addView(ratingBar);
		
		Button addRating=new Button(this);
		addRating.setText("Add Rating!");
		addRating.setOnClickListener(new OnClickListener() {           

			  @Override
			  public void onClick(View v) 
			  {
				  course.addRating((int)ratingBar.getRating());
				  rating.setText(getRatingText());
			  }    
		});
		layout.addView(addRating);
		
		//Comments
		for(Comment comment : course.comments)
		{
			TextView authorText=new TextView(this);
			authorText.setText(comment.author);
			authorText.setTypeface(Typeface.create("", Typeface.BOLD));
			layout.addView(authorText);
			
			TextView commentText=new TextView(this);
			commentText.setText(comment.text);
			layout.addView(commentText);
		}
		
		authorEdit=new EditText(this);
		authorEdit.setHint("Author");
		layout.addView(authorEdit);
		
		textEdit=new EditText(this);
		textEdit.setHint("Comment");
		layout.addView(textEdit);
		
		Button addComment=new Button(this);
		addComment.setText("Add Comment!");
		addComment.setOnClickListener(new OnClickListener() {           

			  @Override
			  public void onClick(View v) 
			  {
				  course.comments.add(new Comment(authorEdit.getText().toString(), textEdit.getText().toString()));
			  }    
		});
		layout.addView(addComment);
		
		view.addView(layout);
		
		setContentView(view);

		//Return course once activity is done
		Intent intent = new Intent();
		intent.putExtra("Course", course);
		setResult(RESULT_OK, intent);
	}

	private String getRatingText()
	{
		return "Rating: " +course.getAverageRating()+ " (" +course.getRatingsCount()+ ")";
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.course, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_course,
					container, false);
			return rootView;
		}
	}

}
