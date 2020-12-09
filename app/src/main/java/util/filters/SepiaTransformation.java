package util.filters;

import android.content.Context;
import android.graphics.*;
import com.squareup.picasso.Transformation;

public class SepiaTransformation implements Transformation {

    private final Context context;

    public SepiaTransformation(Context context) {
        this.context = context;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int width, height, r,g, b, c, gry;
        height = source.getHeight();
        width = source.getWidth();
        int depth = 20;

        Bitmap bmpSephia = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpSephia);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setScale(.3f, .3f, .3f, 1.0f);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        canvas.drawBitmap(source, 0, 0, paint);
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                c = source.getPixel(x, y);

                r = Color.red(c);
                g = Color.green(c);
                b = Color.blue(c);

                gry = (r + g + b) / 3;
                r = g = b = gry;

                r = r + (depth * 2);
                g = g + depth;

                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                bmpSephia.setPixel(x, y, Color.rgb(r, g, b));
            }
        }
        source.recycle();
        return bmpSephia;
    }

    @Override
    public String key() {
        return getClass().getSimpleName();
    }
}
