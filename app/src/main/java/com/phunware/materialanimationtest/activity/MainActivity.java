package com.phunware.materialanimationtest.activity;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.phunware.materialanimationtest.R;
import com.phunware.materialanimationtest.adapter.BasicItemListAdapter;
import com.phunware.materialanimationtest.adapter.viewholder.BasicItemListViewHolder;
import com.phunware.materialanimationtest.model.BasicItem;
import com.phunware.materialanimationtest.model.BasicItemSource;


public class MainActivity extends ActionBarActivity implements BasicItemListAdapter.OnItemClickListener {

    private RecyclerView mGrid;
    private Spinner mTransitionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGrid = (RecyclerView)findViewById(R.id.grid);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mGrid.setLayoutManager(layoutManager);
        mGrid.setAdapter(getAdapter());
    }

    public BasicItemListAdapter getAdapter(){
        BasicItemListAdapter adapter = new BasicItemListAdapter(BasicItemSource.getItems(this));
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if(menu.findItem(R.id.action_anim) != null){

            mTransitionSpinner = (Spinner)menu.findItem(R.id.action_anim).getActionView();
            SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.transitions, R.layout.item_spinner);
            mTransitionSpinner.setAdapter(adapter);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_details) {
            showTransitionDetails();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showTransitionDetails(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(mTransitionSpinner.getSelectedItem().toString());
        switch (mTransitionSpinner.getSelectedItemPosition()){
            case 0: // default
                builder.setMessage(R.string.default_transition_details);
                break;
            case 1: // slide in
                builder.setMessage(R.string.slidein_transition_details);
                break;
            case 2: // zoom in
                builder.setMessage(R.string.zoomin_transition_details);
                break;
            case 3: // cross fade
                builder.setMessage(R.string.crossfade_transition_details);
                break;
            case 4: // scale up
                builder.setMessage(R.string.scaleup_transition_details);
                break;
            case 5: // shared elements
                builder.setMessage(R.string.sharedelements_transition_details);
                break;
            case 6: // reveal
                builder.setMessage(R.string.reveal_transition_details);
                break;
        }
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }

    @Override
    public void onItemClicked(BasicItemListAdapter adapter, int pos, BasicItem item) {
        Intent intent = DetailActivity.getStartIntent(this, item);
        BasicItemListViewHolder vh = (BasicItemListViewHolder)mGrid.findViewHolderForAdapterPosition(pos);

        ActivityOptionsCompat options = null;
        if(mTransitionSpinner != null){
            switch (mTransitionSpinner.getSelectedItemPosition()){
                case 0: // default
                    break;
                case 1: // slide in
                    options = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.zoom_exit);
                    break;
                case 2: // zoom in
                    options = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.zoom_enter, R.anim.zoom_exit);
                    break;
                case 3: // cross fade
                    options = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.fade_in, R.anim.fade_out);
                    break;
                case 4: // scale up
                {
                    View v = vh.mThumbnail;
                    options = ActivityOptionsCompat.makeScaleUpAnimation(vh.itemView, v.getLeft(), v.getTop(), v.getWidth(), v.getHeight());
                }
                    break;
                case 5: // shared elements
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            vh.mThumbnail,
                            "thumbnail");
                    break;
                case 6: // reveal
                    intent = DetailActivity.getStartIntent(this, item, true);
                    break;
            }
        }

        if(options != null){
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

}
