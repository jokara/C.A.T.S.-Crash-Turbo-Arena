package com.example.catscrasharenaturbostars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {

    private Paint linePaint = null;

    private List<Coordinate> coordinates = new ArrayList<>();
    Drawable raketa;
    Drawable busilica;
    Drawable buzdovan;
    Drawable sasija;
    Drawable tocak_prednji;
    Drawable tocak_zadnji;
    Drawable forklift;
    Drawable testera;
    Drawable mehanicar;
    Drawable x1;
    Drawable x2;
    Drawable x3;
    Drawable x4;
    Drawable x5;
    Drawable x6;
    Drawable x7;
    Drawable selektovano1;
    Drawable selektovano2;
    Drawable selektovano3;
    Drawable selektovano4;
    Drawable selektovano5;
    Drawable selektovano6;
    Drawable selektovano7;
    Drawable raketa_atribut;
    Drawable busilica_atribut;
    Drawable buzdovan_atribut;
    Drawable tocak_p_atribut;
    Drawable tocak_z_atribut;
    Drawable forklift_atribut;
    Drawable testera_atribut;
    Drawable sasija_atribut;



    public CustomView(Context context) {
        super(context);
        initCustomView();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCustomView();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomView();
    }

    void initCustomView() {
        raketa= getResources().getDrawable(R.drawable.raketa_without);
        raketa.setBounds(0,0,150,200);
        busilica= getResources().getDrawable(R.drawable.busilica_without);
        busilica.setBounds(220,0,500,250);
        buzdovan= getResources().getDrawable(R.drawable.buzdovan_without);
        buzdovan.setBounds(520,0,800,200);
        sasija= getResources().getDrawable(R.drawable.sasija_without_back);
        sasija.setBounds(500,350,1200,800);
        tocak_prednji= getResources().getDrawable(R.drawable.tocak_prednji_without);
        tocak_prednji.setBounds(820,0,1000,200);
        tocak_zadnji= getResources().getDrawable(R.drawable.tocak_zadnji_without);
        tocak_zadnji.setBounds(1020,20,1200,200);
        forklift= getResources().getDrawable(R.drawable.forklift_without);
        forklift.setBounds(1220,0,1500,250);
        testera = getResources().getDrawable(R.drawable.testera_without);
        testera.setBounds(1520,0,1760,200);
        x1 = getResources().getDrawable(R.drawable.xx);
        x2 = getResources().getDrawable(R.drawable.xx);
        x3 = getResources().getDrawable(R.drawable.xx);
        x4 = getResources().getDrawable(R.drawable.xx);
        x5 = getResources().getDrawable(R.drawable.xx);
        x6 = getResources().getDrawable(R.drawable.xx);
        x7 = getResources().getDrawable(R.drawable.xx);
        x1.setBounds(0,0,150,200);
        x2.setBounds(220,0,500,200);
        x3.setBounds(520,0,800,200);
        x4.setBounds(820,0,1000,200);
        x5.setBounds(1020,0,1200,200);
        x6.setBounds(1220,0,1500,200);
        x7.setBounds(1520,0,1800,200);
        selektovano1 = getResources().getDrawable(R.drawable.selektovano);
        selektovano2 = getResources().getDrawable(R.drawable.selektovano);
        selektovano3 = getResources().getDrawable(R.drawable.selektovano);
        selektovano4 = getResources().getDrawable(R.drawable.selektovano);
        selektovano5 = getResources().getDrawable(R.drawable.selektovano);
        selektovano6 = getResources().getDrawable(R.drawable.selektovano);
        selektovano7 = getResources().getDrawable(R.drawable.selektovano);
        selektovano1.setBounds(0,0,0,0);
        selektovano2.setBounds(0,0,0,0);
        selektovano3.setBounds(0,0,0,0);
        selektovano4.setBounds(0,0,0,0);
        selektovano5.setBounds(0,0,0,0);
        selektovano6.setBounds(0,0,0,0);
        selektovano7.setBounds(0,0,0,0);
        raketa_atribut = getResources().getDrawable(R.drawable.raketa_atribut);
        busilica_atribut = getResources().getDrawable(R.drawable.busilica_atributi);
        buzdovan_atribut = getResources().getDrawable(R.drawable.buzdovan_atribut);
        tocak_p_atribut = getResources().getDrawable(R.drawable.tockovi_atributi);
        tocak_z_atribut = getResources().getDrawable(R.drawable.tockovi_atributi);
        forklift_atribut = getResources().getDrawable(R.drawable.forklift_atribut);
        testera_atribut = getResources().getDrawable(R.drawable.testera_atribut);
        sasija_atribut = getResources().getDrawable(R.drawable.sasija_atributi);
        raketa_atribut.setBounds(0,0,0,0);
        busilica_atribut.setBounds(0,0,0,0);
        buzdovan_atribut.setBounds(0,0,0,0);
        tocak_p_atribut.setBounds(0,0,0,0);
        tocak_z_atribut.setBounds(0,0,0,0);
        forklift_atribut.setBounds(0,0,0,0);
        testera_atribut.setBounds(0,0,0,0);
        sasija_atribut.setBounds(0,0,0,0);



    }

    public void insertCoordinate(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        sasija.draw(canvas);
        raketa.draw(canvas);
        busilica.draw(canvas);
        buzdovan.draw(canvas);
        tocak_prednji.draw(canvas);
        tocak_zadnji.draw(canvas);
        forklift.draw(canvas);
        testera.draw(canvas);
        x1.draw(canvas);
        x2.draw(canvas);
        x3.draw(canvas);
        x4.draw(canvas);
        x5.draw(canvas);
        x6.draw(canvas);
        x7.draw(canvas);
        selektovano1.draw(canvas);
        selektovano2.draw(canvas);
        selektovano3.draw(canvas);
        selektovano4.draw(canvas);
        selektovano5.draw(canvas);
        selektovano6.draw(canvas);
        selektovano7.draw(canvas);
        raketa_atribut.draw(canvas);
        busilica_atribut.draw(canvas);
        buzdovan_atribut.draw(canvas);
        tocak_p_atribut.draw(canvas);
        tocak_z_atribut.draw(canvas);
        forklift_atribut.draw(canvas);
        testera_atribut.draw(canvas);
        sasija_atribut.draw(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public static class Coordinate {

        private float coordinateX;
        private float coordinateY;

        public Coordinate(float coordinateX, float coordinateY) {
            this.coordinateX = coordinateX;
            this.coordinateY = coordinateY;
        }

        public float getCoordinateX() {
            return coordinateX;
        }

        public void setCoordinateX(float coordinateX) {
            this.coordinateX = coordinateX;
        }

        public float getCoordinateY() {
            return coordinateY;
        }

        public void setCoordinateY(float coordinateY) {
            this.coordinateY = coordinateY;
        }

    }

}

