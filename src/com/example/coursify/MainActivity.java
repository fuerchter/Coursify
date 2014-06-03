package com.example.coursify;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private ArrayList<Course> courses=new ArrayList<Course>();
	private int lastCourse=0;
	
	@SuppressLint("NewApi")
	private void buildGui()
	{
		ArrayList<String> courseNames=new ArrayList<String>(); //Means no two courses can have the same name?
        for(Course course : courses)
        {
        	courseNames.add(course.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseNames.toArray(new String[courseNames.size()]));
        setListAdapter(adapter);
        
        ListView view = getListView();
        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
            	remove(pos);
            	return true;
            }
        });
        
        /*view.setRotation(30);
        view.setAlpha(0.2f);*/
        view.setBackgroundColor(Color.GREEN); //DESIGN: Change background color
	}
	
	private void remove(int pos)
    {
    	courses.remove(pos);
    	buildGui();    	
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Course cpp=new Course("Grundlagen der C++ Programmierung");
        cpp.addRating(3);
        cpp.addRating(3);
        cpp.addRating(4);
        cpp.addRating(4);
        cpp.addRating(5);
        courses.add(cpp);
        
        Course bv=new Course("Angewandte Bildverarbeitung");
        bv.addRating(3);
        bv.addRating(2);
        bv.comments.add(new Comment("Gerold Filip", "The legend rolls on."));
        bv.comments.add(new Comment("Raimundo Wilmar", "Beyond the conventional."));
        bv.comments.add(new Comment("Keaton Meginfrid", "Dangerously entertaining."));
        courses.add(bv);
        
        Course gag=new Course("Grundzüge der Algorithmischen Geometrie");
        gag.addRating(2);
        gag.addRating(1);
        gag.addRating(1);
        courses.add(gag);
        
        for(int i=1; i<=20; i++)
        {
	        Course swe=new Course("Software Engineering");
	        courses.add(swe);
        }
        
        buildGui();
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
