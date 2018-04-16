package com.andiroot.restousinglibrary.stickerview;

import android.Manifest;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends AppCompatActivity {
  private static final String TAG = MainActivity.class.getSimpleName();
  public static final int PERM_RQST_CODE = 110;
  private StickerView stickerView;
  //private TextSticker sticker;
  public Realm realm;
  public Position position;
  Sticker sticker1;
  //public Realm realm;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Realm.init(this);
    RealmConfiguration config = new RealmConfiguration
            .Builder()
            .deleteRealmIfMigrationNeeded()
            .build();
    Realm.setDefaultConfiguration(config);
    realm = Realm.getDefaultInstance();
    position = new Position();

    if(!realm.isEmpty())
    {
      Toast.makeText(this,"Arranging now.", Toast.LENGTH_SHORT).show();
//      id = (int) realm.where(Position.class).count();
//      Log.d("No of elements in Realm", " " + id );
    }
    else
    {
      Toast.makeText(this,"Nothing to Arrange.", Toast.LENGTH_SHORT).show();
    }

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
        Log.d(TAG, "onStickerAdded | Angle: " + sticker1.getCurrentAngle() + " | X: " + sticker1.getCenterPoint().x + " | Y: " + sticker1.getCenterPoint().y);

      }

      @Override
      public void onStickerClicked(@NonNull Sticker sticker) {


        Log.d(TAG, "onStickerClicked");
      }

      @Override
      public void onStickerDeleted(@NonNull Sticker sticker)
      {
        Log.d(TAG, "onStickerDeleted");
      }

      @Override
      public void onStickerDragFinished(@NonNull Sticker sticker)
      {
          Log.d(TAG, "onStickerAdded | Angle: " + sticker.getCurrentAngle() + " | X: " + sticker.getMappedCenterPoint().x + " | Y: " + sticker.getMappedCenterPoint().y);
        Log.d(TAG, "onStickerDragFinished ");

      }

      @Override
      public void onStickerZoomFinished(@NonNull Sticker sticker)
      {
        Log.d(TAG, "onStickerZoomFinished ");
      }


      @Override
      public void onStickerFlipped(@NonNull Sticker sticker)
      {
        Log.d(TAG, "onStickerFlipped");
      }


      @Override
      public void onStickerDoubleTapped(@NonNull Sticker sticker)
      {
        Log.d(TAG, "onDoubleTapped: double tap will be with two click");
      }

    });

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
    Log.d(TAG, "testRemoveAll");

  }


  public void testAdd(View view) {

    Drawable drawable1 =
            ContextCompat.getDrawable(this, R.drawable.table2);


    stickerView.addSticker(new DrawableSticker(drawable1));
  }

  public void testaddchair(View view) {

    Drawable drawable1 =
            ContextCompat.getDrawable(this, R.drawable.chair2);


    stickerView.addSticker(new DrawableSticker(drawable1));
  }
}
