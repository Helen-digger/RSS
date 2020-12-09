package util.filters;

import android.content.Context;
import android.graphics.*;
import com.squareup.picasso.Transformation;

public class ContrastTransformation implements Transformation {

    private final Context mContext;

    public ContrastTransformation(Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public Bitmap transform(Bitmap source) {
        float contrast = 2.0f;
        float brightness = 20.0f;

        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Bitmap ret = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());

        Canvas canvas = new Canvas(ret);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(source, 0, 0, paint);

        source.recycle();

        return ret;
    }

    @Override
    public String key() {
        return getClass().getSimpleName();
    }
}
