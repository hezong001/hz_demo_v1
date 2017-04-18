package com.hz.takeoff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import static com.hz.takeoff.R.id.iv;

public class MainActivity extends AppCompatActivity {

    private ImageView mIv;
    private Bitmap    mNewBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIv = (ImageView) findViewById(iv);     //从xml中找到底层图片

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pre15);   //加载进上层图片

        Display display = getWindowManager().getDefaultDisplay();   //获取屏幕

        //上层图片的宽和高
        mNewBitmap = bitmap.createBitmap(display.getWidth(), display.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(mNewBitmap);      //创建画布，关联上层图片


        Matrix matrix = new Matrix();   //根据尺寸

        float[] values = {
                (0f + display.getWidth()) / bitmap.getWidth(), 0, 0,
                0, (0f + display.getHeight()) / bitmap.getHeight(), 0,
                0, 0, 1
        };
        matrix.setValues(values);
        canvas.drawBitmap(bitmap, matrix, null);    //画布重新画 上层图片

        mIv.setImageBitmap(mNewBitmap);      //给底层图片设置上层图片
        mIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getX();
                        int y = (int) event.getY();

                        int startX = x - 40 < 0 ? 0 : x - 40;
                        int endX = x + 40 > mNewBitmap.getWidth() ? mNewBitmap.getWidth() : x + 40;

                        int startY = y - 40 < 0 ? 0 : y - 40;
                        int endY = y + 40 > mNewBitmap.getHeight() ? mNewBitmap.getHeight() : y + 40;

                        for (int i = startX; i < endX; i++) {

                            for (int j = startY; j < endY; j++) {
                                //把newBitmap对应坐标的像素点设置为透明
                                mNewBitmap.setPixel(i, j, Color.TRANSPARENT);
                            }
                        }
                        //重新将newBitmap设置给ImageView控件
                        mIv.setImageBitmap(mNewBitmap);
                        break;
                }
                return true;
            }
        });
    }
}
