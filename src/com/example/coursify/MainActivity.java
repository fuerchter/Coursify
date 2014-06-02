package com.example.coursify;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ListActivity {
	private ArrayList<Course> courses=new ArrayList<Course>();
	private int lastCourse=0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Course einfinf=new Course("Einfinf");
        
        einfinf.addRating(5);
        einfinf.addRating(1);
        einfinf.comments.add(new Comment("Ich", "asdf"));
        einfinf.comments.add(new Comment("Du", "blablabla"));
        
        courses.add(einfinf);
        courses.add(new Course("Mathe"));
        courses.add(new Course("Algogeo"));
        
        ArrayList<String> courseNames=new ArrayList<String>(); //Means no two courses can have the same name?
        for(Course course : courses)
        {
        	courseNames.add(course.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseNames.toArray(new String[courseNames.size()]));
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	//Once item is clicked on, give the chosen course to the CourseActivity and start it
    	Intent intent = new Intent(this, CourseActivity.class);
    	intent.putExtra("Course", courses.get(position));
    	lastCourse=position;
    	startActivityForResult(intent, 1);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	courses.set(lastCourse, (Course)data.getSerializableExtra("Course"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
