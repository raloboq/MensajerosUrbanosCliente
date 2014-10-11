package co.mensajeros.cliente;

import android.graphics.Bitmap;

/**
 * Created by Rene on 9/3/14.
 */
public class DrawableItems {

    String Text1;
    String Text2;
    int Icon;
    int colorBackground;
    Bitmap Background;

    public DrawableItems(String text1, String text2, Bitmap background, int icon, int colorBackground) {
        Text1 = text1;
        Text2 = text2;
        Background = background;
        Icon = icon;
        this.colorBackground = colorBackground;
    }
    public String getText1() {
        return Text1;
    }

    public void setText1(String text1) {
        Text1 = text1;
    }



    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public String getText2() {
        return Text2;
    }

    public void setText2(String text2) {
        Text2 = text2;
    }

    public Bitmap getBackground() {
        return Background;
    }

    public void setBackground(Bitmap background) {
        Background = background;
    }

    public int getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(int colorBackground) {
        this.colorBackground = colorBackground;
    }


}
