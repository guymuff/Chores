package com.guyamuff.chores;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    static final String TAG = "Chore Scheduler";
    static final double MAGIC_SCALER = Math.log(4.0);


    ListView lView;
    List<Chore> selected;
    ArrayAdapter<Chore> arrayAdapter;
    DBManager dbManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selected = new ArrayList<Chore>();
        lView = (ListView) findViewById(R.id.ListView01);
        //arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice);
        arrayAdapter = new MultilineAdapter(this, android.R.layout.simple_list_item_multiple_choice);
        arrayAdapter.registerDataSetObserver(new ChoreObserver());
        lView.setAdapter(arrayAdapter);
        lView.setOnItemClickListener(new ClickListener());
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        new GenerateChoresTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (editor != null) {
            editor.commit();
        }
        if (dbManager != null) {
            dbManager.close();
        }
    }

    boolean doChore(Chore chore, long now, Random rand) {
        boolean ret = false;

        double daysUntilDue = Math.round(((double) (chore.frequency - (now - chore.lastCompleted))) / ((double) DBManager.Frequency.DAILY.millis));
        double p = probability(daysUntilDue);
        if (rand.nextDouble() <= p) {
            ret = true;
        }

        return ret;
    }

    double probability(double daysUntilDue) {
        return 1 / (1 + Math.exp(MAGIC_SCALER * daysUntilDue));
    }


    private class ChoreObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            super.onChanged();

            for(int i=0; i<arrayAdapter.getCount(); i++) {
                if(arrayAdapter.getItem(i).previousTime > 0) {
                    lView.setItemChecked(i, true);
                }
            }
        }
    }

    private class ClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Chore chore = arrayAdapter.getItem(i);
            chore.toggleLastCompleted();
            dbManager.updateCompletionTime(chore);
        }
    }

    private class GenerateChoresTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            dbManager = new DBManager(getApplicationContext());

            String today = dateFormat.format(new Date());
            String last_ran = getLastRun();

            if (today.equals(last_ran)) {
                Chore[] chores = dbManager.getTodaysChores();
                selected = Arrays.asList(chores);
            } else {
                dbManager.clearTodays();

                Chore[] chores = dbManager.queryChores();
                long now = System.currentTimeMillis();
                Random rand = new Random();
                for (Chore chore : chores) {
                    if (doChore(chore, now, rand)) {
                        selected.add(chore);
                        dbManager.updateTodays(chore);
                    }
                }

                editor = sharedPreferences.edit();
                editor.putString("last_run", today);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            arrayAdapter.addAll(selected);
        }
    }

    String getLastRun() {
        return sharedPreferences.getString("last_run", dateFormat.format(new Date(0)));
    }

    class MultilineAdapter extends ArrayAdapter {

        ViewGroup.LayoutParams lp;

        public MultilineAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);

            lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView ret = (TextView) super.getView(position, convertView, parent);

            ret.setLayoutParams(lp);

            return ret;
        }
    }
}
