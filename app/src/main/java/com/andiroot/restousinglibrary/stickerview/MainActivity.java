package com.andiroot.restousinglibrary.stickerview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.andiroot.restousinglibrary.sticker.BitmapStickerIcon;
import com.andiroot.restousinglibrary.sticker.DeleteIconEvent;
import com.andiroot.restousinglibrary.sticker.DrawableSticker;
import com.andiroot.restousinglibrary.sticker.FlipHorizontallyEvent;
import com.andiroot.restousinglibrary.sticker.Sticker;
import com.andiroot.restousinglibrary.sticker.StickerView;
import com.andiroot.restousinglibrary.sticker.TextSticker;
import com.andiroot.restousinglibrary.sticker.ZoomIconEvent;
import com.andiroot.restousinglibrary.stickerview.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {
    Spinner spinnerTable;
    int realm_pos = 0;
  private static final String TAG = MainActivity.class.getSimpleName();
  public static final int PERM_RQST_CODE = 110;
  private StickerView stickerView;
  //private TextSticker sticker;
  public Realm realm;
  public Position position;
  public int id = 1;
    private ArrayList<TableItem> mTableList;
    Sticker sticker1;
  //public Realm realm;
  private void initList()
  {
      mTableList = new ArrayList<>();
      mTableList.add(new TableItem(R.drawable.table2));
      mTableList.add(new TableItem(R.drawable.chair2));
      mTableList.add(new TableItem(R.drawable.table3));
      mTableList.add(new TableItem(R.drawable.table4));
      mTableList.add(new TableItem(R.drawable.table5));
      mTableList.add(new TableItem(R.drawable.bed1));
      mTableList.add(new TableItem(R.drawable.pot1));
      mTableList.add(new TableItem(R.drawable.pot2));
      mTableList.add(new TableItem(R.drawable.sofa));

  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {

      Intent intent = getIntent();
      realm_pos = intent.getExtras().getInt("Position");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
      initList();
      spinnerTable = (Spinner) findViewById(R.id.tblChoice);
      TableAdapter mAdapter = new TableAdapter(this, mTableList);
      spinnerTable.setAdapter(mAdapter);
    Realm.init(this);
    RealmConfiguration realmConfiguration1 = new RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .name("position_" + realm_pos)
            .build();


      realm = Realm.getInstance(realmConfiguration1);

    stickerView = (StickerView) findViewById(R.id.sticker_view);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    BitmapStickerIcon deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
            com.andiroot.restousinglibrary.sticker.R.drawable.sticker_ic_close_white_18dp),
            BitmapStickerIcon.LEFT_TOP);

    deleteIcon.setIconEvent(new DeleteIconEvent());

    BitmapStickerIcon zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
            com.andiroot.restousinglibrary.sticker.R.drawable.sticker_ic_scale_white_18dp),
            BitmapStickerIcon.RIGHT_BOTOM);


    zoomIcon.setIconEvent(new ZoomIconEvent());

    BitmapStickerIcon flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
            com.andiroot.restousinglibrary.sticker.R.drawable.sticker_ic_flip_white_18dp),
            BitmapStickerIcon.RIGHT_TOP);
    flipIcon.setIconEvent(new FlipHorizontallyEvent());

    stickerView.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon));

    stickerView.setBackgroundColor(Color.WHITE);
    stickerView.setLocked(false);
    stickerView.setConstrained(true);

    stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {


      @Override
      public void onStickerAdded(@NonNull Sticker sticker)
      {
        sticker1 = sticker;
        Matrix sizeMatrix = new Matrix();
        float width = sticker1.getWidth();
        float height = sticker1.getHeight();
        sizeMatrix.postTranslate(width,height);
        sticker1.setMatrix(sizeMatrix);
          Log.d(TAG, "onStickerAdded | Angle: " + sticker1.getCurrentAngle() + " | X: " + sticker1.getCenterPoint().x + " | Y: " + sticker1.getCenterPoint().y + " | Height: " + sticker1.getHeight() + " | Width: " + sticker1.getWidth() + " | H: " + sticker1.isFlippedHorizontally() + " | V: " + sticker1.isFlippedVertically());

      }

      @Override
      public void onStickerClicked(@NonNull Sticker sticker) {


        Log.d(TAG, "onStickerClicked + " + sticker1.getMatrix());
      }

      @Override
      public void onStickerDeleted(@NonNull Sticker sticker)
      {
        Log.d(TAG, "onStickerDeleted");
      }

      @Override
      public void onStickerDragFinished(@NonNull Sticker sticker)
      {
          Log.d(TAG, "onStickerAdded | Angle: " + sticker1.getCurrentAngle() + " | X: " + sticker1.getCenterPoint().x + " | Y: " + sticker1.getCenterPoint().y + " | Height: " + sticker1.getHeight() + " | Width: " + sticker1.getWidth() + " | H: " + sticker1.isFlippedHorizontally() + " | V: " + sticker1.isFlippedVertically());
          Log.d(TAG, "onStickerDragFinished ");

      }

      @Override
      public void onStickerZoomFinished(@NonNull Sticker sticker)
      {
          Log.d(TAG, "onStickerAdded | Angle: " + sticker1.getCurrentAngle() + " | X: " + sticker1.getCenterPoint().x + " | Y: " + sticker1.getCenterPoint().y + " | Height: " + sticker1.getHeight() + " | Width: " + sticker1.getWidth() + " | H: " + sticker1.isFlippedHorizontally() + " | V: " + sticker1.isFlippedVertically());
          Log.d(TAG, "onStickerZoomFinished ");
      }


      @Override
      public void onStickerFlipped(@NonNull Sticker sticker)
      {
        Log.d(TAG, "onStickerAdded | Angle: " + sticker1.getCurrentAngle() + " | X: " + sticker1.getCenterPoint().x + " | Y: " + sticker1.getCenterPoint().y + " | Height: " + sticker1.getHeight() + " | Width: " + sticker1.getWidth() + " | H: " + sticker1.isFlippedHorizontally() + " | V: " + sticker1.isFlippedVertically());
        Log.d(TAG, "onStickerFlipped");
      }


      @Override
      public void onStickerDoubleTapped(@NonNull Sticker sticker)
      {
        Log.d(TAG, "onDoubleTapped: double tap will be with two click");
      }

    });

      if(!realm.isEmpty())
      {
          Toast.makeText(this,"Arranging now.", Toast.LENGTH_SHORT).show();
          RealmResults<Position> result = realm.where(Position.class).findAll();
          id = (int) realm.where(Position.class).count();
          Log.d("No of elements in Realm", " " + id );
          for(Position p : result)
          {
              addImageFromRealm(p);
          }
      }
      else
      {
          Toast.makeText(this,"Nothing to Arrange.", Toast.LENGTH_SHORT).show();
          Log.d("No of elements in Realm", " " + id );
      }

    if (toolbar != null) {
      toolbar.setTitle(R.string.app_name);
    }
  }

  public void testLock(View view)
  {
    stickerView.setLocked(!stickerView.isLocked());
  }



  public void testRemoveAll(View view)
  {
    stickerView.removeAllStickers();
    realm.beginTransaction();
    realm.deleteAll();
    realm.commitTransaction();
    id = 1;
    Log.d(TAG, "testRemoveAll");

  }


  public void testAdd(View view) {
      addImageToRealm();
  }

    public void addImageToRealm() {

        int no = spinnerTable.getSelectedItemPosition();
        Drawable drawable1 =
                ContextCompat.getDrawable(this, mTableList.get(no).getmFlagImage()
                );
        stickerView.addSticker(new DrawableSticker(drawable1));
        position = new Position();
        position.setId(id);
        position.setAngle(sticker1.getCurrentAngle());
        position.setX(sticker1.getCenterPoint().x);
        position.setY(sticker1.getCenterPoint().y);
        position.setHeight(sticker1.getHeight());
        position.setWidth(sticker1.getWidth());
        position.setFlippedHorizontally(sticker1.isFlippedHorizontally());
        position.setFlippedVertically(sticker1.isFlippedVertically());
        position.setType(no);
        id++;
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(position);
        realm.commitTransaction();

        Position rslt;
        rslt = realm.where(Position.class).equalTo("id", id).findFirst();

        if (rslt != null) {
            Toast.makeText(this, "ID: " + rslt.getId() + " | X/Y: " + rslt.getX() + "/" + rslt.getY(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addImageFromRealm(Position position) {

      Drawable drawable1;
      if(position.getType() == 0) {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.table2);
      }
      else if (position.getType() == 1)
      {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.chair2);
      }
      else if (position.getType() == 2)
      {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.table3);
      }
      else if (position.getType() == 3)
      {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.table4);
      }
      else if (position.getType() == 4)
      {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.table5);
      }
      else if (position.getType() == 5)
      {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.bed1);
      }
      else if (position.getType() == 6)
      {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.pot1);
      }
      else if (position.getType() == 7)
      {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.pot2);
      }
      else
      {
          drawable1 =
                  ContextCompat.getDrawable(this, R.drawable.sofa);
      }

      stickerView.addSticker(new DrawableSticker(drawable1));
      Log.d("Adding from Realm", "ID: " + position.getId() + " | X/Y: " + position.getX() + "/" + position.getY());
        //Toast.makeText(this, "ID: " + position.getId() + " | X/Y: " + position.getX() + "/" + position.getY(), Toast.LENGTH_SHORT).show();
    }

}
