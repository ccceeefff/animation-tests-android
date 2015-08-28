package com.phunware.materialanimationtest.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phunware.materialanimationtest.R;
import com.phunware.materialanimationtest.model.BasicItem;

import org.w3c.dom.Text;

public class DetailActivity extends ActionBarActivity {

    private static final String EXTRA_ITEM = "item";
    private static final String EXTRA_RUN_INTRO_ANIM = "intro_anim";

    protected View mBackground;
    protected View mHeaderContainer;
    protected TextView mTitle;
    protected TextView mSubtitle;
    protected ImageView mThumbnail;
    protected RecyclerView mGrid;

    private boolean mEntered = false;

    public static Intent getStartIntent(Context context, BasicItem item){
        return getStartIntent(context, item, false);
    }

    public static Intent getStartIntent(Context context, BasicItem item, boolean introAnim){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_ITEM, item);
        intent.putExtra(EXTRA_RUN_INTRO_ANIM, introAnim);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mBackground = findViewById(R.id.background);
        mHeaderContainer = findViewById(R.id.header);
        mTitle = (TextView) findViewById(R.id.title);
        mSubtitle = (TextView) findViewById(R.id.subtitle);
        mThumbnail = (ImageView) findViewById(R.id.thumbnail);
        mGrid = (RecyclerView) findViewById(R.id.grid);

        BasicItem item = getItem();
        mTitle.setText(item.getTitle());
        mSubtitle.setText(item.getSubtitle());
        mTitle.setTextColor(item.getTextColor());
        mSubtitle.setTextColor(item.getTextColor());
        Glide.with(this).load(item.getImageURL()).into(mThumbnail);
        mHeaderContainer.setBackgroundColor(item.getPrimaryColor());
        mBackground.setBackgroundColor(item.getSecondaryColor());
    }

    public BasicItem getItem(){
        return getIntent().getExtras().getParcelable(EXTRA_ITEM);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mEntered){
            if(getIntent().getBooleanExtra(EXTRA_RUN_INTRO_ANIM, false)){
                findViewById(R.id.main).setVisibility(View.INVISIBLE);
                mBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        onEnter();
                        mBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });
            }
            mEntered = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onEnter(){
        BasicItem item = getItem();
        findViewById(R.id.main).setVisibility(View.VISIBLE);
        mHeaderContainer.setAlpha(0);
        mHeaderContainer.setTranslationY(-mHeaderContainer.getHeight());
        int revealTime = 300;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            revealTime = 300;
            int cx = (mBackground.getLeft() + mBackground.getRight())/2;
            int cy = (mBackground.getTop() + mBackground.getBottom())/2;

            Animator animator = ViewAnimationUtils.createCircularReveal(mBackground, cx, cy, 0,
                    Math.max(mBackground.getWidth(), mBackground.getHeight()));
            animator.setDuration(revealTime);
            animator.start();
        } else {
            mBackground.setScaleX(0);
            mBackground.setScaleY(0);
            mBackground.animate().scaleX(1).scaleY(1).setStartDelay(150).setDuration(revealTime).start();
            revealTime = 450;
        }
        mHeaderContainer.animate().alpha(1).translationY(0).setStartDelay(revealTime).setDuration(300).start();
    }
}
