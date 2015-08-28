package com.phunware.materialanimationtest.model;

import android.content.Context;
import android.content.res.Resources;

import com.phunware.materialanimationtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cef on 8/27/15.
 */
public class BasicItemSource {

    public static List<BasicItem> getItems(Context context){
        List<BasicItem> items = new ArrayList<BasicItem>();

        Resources res = context.getResources();

        // mario
        BasicItem mario = new BasicItem();
        mario.setTitle("Mario");
        mario.setSubtitle("Master Plumber");
        mario.setImageURL("http://img2.wikia.nocookie.net/__cb20130611171456/fantendo/images/1/10/Mario_Artwork_(alt)_-_Super_Smash_Bros._Wii_U_3DS.png");
        mario.setPrimaryColor(res.getColor(R.color.mario_primary_color));
        mario.setSecondaryColor(res.getColor(R.color.mario_secondary_color));
        mario.setTextColor(res.getColor(R.color.mario_text_color));
        items.add(mario);

        // luigi
        BasicItem luigi = new BasicItem();
        luigi.setTitle("Luigi");
        luigi.setSubtitle("Brother Plumber");
        luigi.setImageURL("http://newsupermariobrosu.nintendo.com/_ui/img/luigi/characters-luigi-jump.png");
        luigi.setPrimaryColor(res.getColor(R.color.luigi_primary_color));
        luigi.setSecondaryColor(res.getColor(R.color.luigi_secondary_color));
        luigi.setTextColor(res.getColor(R.color.luigi_text_color));
        items.add(luigi);

        // peach
        BasicItem peach = new BasicItem();
        peach.setTitle("Princess Peach");
        peach.setSubtitle("Mushroom Royalty");
        peach.setImageURL("https://upload.wikimedia.org/wikipedia/en/d/d5/Peach_(Super_Mario_3D_World).png");
        peach.setPrimaryColor(res.getColor(R.color.peach_primary_color));
        peach.setSecondaryColor(res.getColor(R.color.peach_secondary_color));
        peach.setTextColor(res.getColor(R.color.peach_text_color));
        items.add(peach);

        // toad
        BasicItem toad = new BasicItem();
        toad.setTitle("Toadstool");
        toad.setSubtitle("Mushroom Chair");
        toad.setImageURL("http://oyster.ignimgs.com/mediawiki/apis.ign.com/super-mario-wii-u/thumb/d/de/Toad_Artwork_-_Super_Mario_3D_World.png/440px-Toad_Artwork_-_Super_Mario_3D_World.png");
        toad.setPrimaryColor(res.getColor(R.color.toad_primary_color));
        toad.setSecondaryColor(res.getColor(R.color.toad_secondary_color));
        toad.setTextColor(res.getColor(R.color.toad_text_color));
        items.add(toad);

        // bowser
        BasicItem bowser = new BasicItem();
        bowser.setTitle("Bowser");
        bowser.setSubtitle("King of Koopas");
        bowser.setImageURL("http://img2.wikia.nocookie.net/__cb20120110144057/nintendo/en/images/9/98/Bowser_NSMBW.png");
        bowser.setPrimaryColor(res.getColor(R.color.bowser_primary_color));
        bowser.setSecondaryColor(res.getColor(R.color.bowser_secondary_color));
        bowser.setTextColor(res.getColor(R.color.bowser_text_color));
        items.add(bowser);

        return items;
    }

}
