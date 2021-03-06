package com.example.catscrasharenaturbostars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class CustomViewFight extends View {

    Drawable left_wall;
    Drawable right_wall;
    /****************IGRAC 1**********************/
    Drawable p1_sasija;
    Drawable p1_raketa;
    Drawable p1_busilica;
    Drawable p1_buzdovan;
    Drawable p1_tocak_p;
    Drawable p1_tocak_z;
    Drawable p1_forklift;
    Drawable p1_testera;
    Drawable p1_buzdovan_dole;
    Drawable p1_buzdovan_desno;
    Drawable p1_buzdovan_gore;
    Drawable p1_forklift_dole;
    Drawable p1_forklift_desno;
    Drawable p1_forklift_gore;
    /***************IGRAC 1 ROTIRAN************************/
    Drawable p1_sasija_rotirana;
    Drawable p1_raketa_rotirana;
    Drawable p1_busilica_rotirana;
    Drawable p1_testera_rotirana;
    Drawable p1_ispaljena_raketa_rotirana;
    /****************IGRAC 2**********************/
    Drawable p2_sasija;
    Drawable p2_raketa;
    Drawable p2_busilica;
    Drawable p2_buzdovan;
    Drawable p2_tocak_p;
    Drawable p2_tocak_z;
    Drawable p2_forklift;
    Drawable p2_testera;
    Drawable p2_buzdovan_dole;
    Drawable p2_buzdovan_levo;
    Drawable p2_buzdovan_gore;
    Drawable p2_forklift_dole;
    Drawable p2_forklift_levo;
    Drawable p2_forklift_gore;
    /***************IGRAC 2 ROTIRAN************************/
    Drawable p2_sasija_rotirana;
    Drawable p2_raketa_rotirana;
    Drawable p2_busilica_rotirana;
    Drawable p2_ispaljena_raketa_rotirana;
    Drawable p2_testera_rotirana;
    /***************EKSPLOZIJA**********************/
    Drawable eksplozija;
    Drawable porazena_macka;
    Drawable ispaljena_raketa_p1;
    Drawable ispaljena_raketa_p2;

    /*************************************************/
    Drawable return_to_garage;
    Drawable fire_button;

    public CustomViewFight(Context context) {
        super(context);
        initCustomView();
    }

    public CustomViewFight(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCustomView();
    }

    public CustomViewFight(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomView();
    }

    void initCustomView() {
        left_wall = getResources().getDrawable(R.drawable.left_wall_without);
        left_wall.setBounds(-50,200,150,800);
        right_wall = getResources().getDrawable(R.drawable.right_wall_without);
        right_wall.setBounds(1670,200,1870,800);
        /**********************DOHVATANJE SLIKA P1*****************************************/
        p1_sasija = getResources().getDrawable(R.drawable.sasija_without_back);
        p1_raketa = getResources().getDrawable(R.drawable.raketa_without);
        p1_busilica = getResources().getDrawable(R.drawable.busilica_without);
        p1_buzdovan = getResources().getDrawable(R.drawable.buzdovan_without);
        p1_tocak_p = getResources().getDrawable(R.drawable.tocak_prednji_without);
        p1_tocak_z = getResources().getDrawable(R.drawable.tocak_zadnji_without);
        p1_forklift = getResources().getDrawable(R.drawable.forklift_without);
        p1_testera = getResources().getDrawable(R.drawable.testera_without);
        p1_buzdovan_desno = getResources().getDrawable(R.drawable.buzdovan_without_p2);
        p1_buzdovan_gore = getResources().getDrawable(R.drawable.buzdovan_p1_atack2);
        p1_buzdovan_dole = getResources().getDrawable(R.drawable.buzdovan_p1_atack1);
        p1_forklift_desno = getResources().getDrawable(R.drawable.forklift_without_p2);
        p1_forklift_gore = getResources().getDrawable(R.drawable.forklift_without_gore);
        p1_forklift_dole = getResources().getDrawable(R.drawable.forklift_without_dole);
        /**********************DOHVATANJE SLIKA P1 ROTIRAN*****************************************/
        p1_sasija_rotirana = getResources().getDrawable(R.drawable.sasija_without_back_rotated);
        p1_raketa_rotirana = getResources().getDrawable(R.drawable.raketa_without_rotated);
        p1_busilica_rotirana = getResources().getDrawable(R.drawable.busilica_without_rotated);
        p1_testera_rotirana = getResources().getDrawable(R.drawable.testera_without_rotated);
        p1_ispaljena_raketa_rotirana = getResources().getDrawable(R.drawable.ispaljena_raketa_without_rotated);
        /**********************DOHVATANJE SLIKA P2*****************************************/
        p2_sasija = getResources().getDrawable(R.drawable.sasija_without_back_p2);
        p2_raketa = getResources().getDrawable(R.drawable.raketa_without_p2);
        p2_busilica = getResources().getDrawable(R.drawable.busilica_without_p2);
        p2_buzdovan = getResources().getDrawable(R.drawable.buzdovan_without_p2);
        p2_tocak_p = getResources().getDrawable(R.drawable.tocak_prednji_without);
        p2_tocak_z = getResources().getDrawable(R.drawable.tocak_zadnji_without);
        p2_forklift = getResources().getDrawable(R.drawable.forklift_without_p2);
        p2_testera = getResources().getDrawable(R.drawable.testera_without_p2);
        p2_buzdovan_levo = getResources().getDrawable(R.drawable.buzdovan_without);
        p2_buzdovan_gore = getResources().getDrawable(R.drawable.buzdovan_p1_atack2);
        p2_buzdovan_dole = getResources().getDrawable(R.drawable.buzdovan_p1_atack1);
        p2_forklift_levo = getResources().getDrawable(R.drawable.forklift_without);
        p2_forklift_gore = getResources().getDrawable(R.drawable.forklift_without_gore);
        p2_forklift_dole = getResources().getDrawable(R.drawable.forklift_without_dole);
        /**********************DOHVATANJE SLIKA P2 ROTIRAN*****************************************/
        p2_sasija_rotirana = getResources().getDrawable(R.drawable.sasija_without_back_rotated2);
        p2_raketa_rotirana = getResources().getDrawable(R.drawable.raketa_without_rotated2);
        p2_busilica_rotirana = getResources().getDrawable(R.drawable.busilica_without_rotated2);
        p2_testera_rotirana = getResources().getDrawable(R.drawable.testera_without_rotated2);
        p2_ispaljena_raketa_rotirana = getResources().getDrawable(R.drawable.ispaljena_raketa_without_rotated2);
        /**********************POSTAVLJANJE GRANICA P1 ROTIRANOG*****************************************/
        p1_sasija_rotirana.setBounds(0,0,0,0);
        p1_raketa_rotirana.setBounds(0,0,0,0);
        p1_busilica_rotirana.setBounds(0,0,0,0);
        p1_testera_rotirana.setBounds(0,0,0,0);
        p1_ispaljena_raketa_rotirana.setBounds(0,0,0,0);
        /**********************POSTAVLJANJE GRANICA P1 ROTIRANOG*****************************************/
        p2_sasija_rotirana.setBounds(0,0,0,0);
        p2_raketa_rotirana.setBounds(0,0,0,0);
        p2_busilica_rotirana.setBounds(0,0,0,0);
        p2_testera_rotirana.setBounds(0,0,0,0);
        p2_ispaljena_raketa_rotirana.setBounds(0,0,0,0);
        /**********************POSTAVLJANJE GRANICA P1*****************************************/
        p1_sasija.setBounds(160,400,660,750);
        p1_tocak_z.setBounds(260,640,360,750);
        p1_tocak_p.setBounds(475,630,585,750);
        p1_raketa.setBounds(250,520,380,640);
        p1_busilica.setBounds(510,530,740,680);
        p1_testera.setBounds(510,530,740,680);
        p1_forklift.setBounds(340,530,600,700);
        p1_buzdovan.setBounds(340,520,600,700);
        /**********************POSTAVLJANJE GRANICA P2*********************************************/
        p2_sasija.setBounds(1160,400,1660,750);
        p2_tocak_p.setBounds(1220,635,1360,750);
        p2_tocak_z.setBounds(1450,630,1570,730);
        p2_raketa.setBounds(1430,515,1560,640);
        p2_busilica.setBounds(1090,530,1320,680);
        p2_testera.setBounds(1090,530,1320,680);
        p2_forklift.setBounds(1210,530,1470,700);
        p2_buzdovan.setBounds(1210,520,1470,700);
        /*********************EKSPLOZIJA***********************************************************/
        eksplozija= getResources().getDrawable(R.drawable.eksplozija_without);
        eksplozija.setBounds(0,0,0,0);
        porazena_macka = getResources().getDrawable(R.drawable.lossing_cat);
        porazena_macka.setBounds(0,0,0,0);
        /********************ISPALJENE RAKETE******************************************************/
        ispaljena_raketa_p1 = getResources().getDrawable(R.drawable.ispaljena_raketa_without);
        ispaljena_raketa_p1.setBounds(0,0,0,0);
        ispaljena_raketa_p2 = getResources().getDrawable(R.drawable.ispaljena_raketa_without_p2);
        ispaljena_raketa_p2.setBounds(0,0,0,0);
        /*******************NAZAD U GARAZU*********************************************************/
        return_to_garage = getResources().getDrawable(R.drawable.ok_img);
        return_to_garage.setBounds(0,0,0,0);
        fire_button = getResources().getDrawable(R.drawable.fire_button);
        fire_button.setBounds(0,0,0,0);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        left_wall.draw(canvas);
        right_wall.draw(canvas);
        p1_sasija.draw(canvas);
        p1_sasija_rotirana.draw(canvas);
        p1_tocak_z.draw(canvas);
        p1_tocak_p.draw(canvas);
        p1_raketa.draw(canvas);
        p1_busilica.draw(canvas);
        p1_testera.draw(canvas);
        p1_forklift.draw(canvas);
        p1_buzdovan.draw(canvas);
        p1_buzdovan_dole.draw(canvas);
        p1_buzdovan_gore.draw(canvas);
        p1_forklift_dole.draw(canvas);
        p1_forklift_gore.draw(canvas);


        p2_sasija.draw(canvas);
        p2_sasija_rotirana.draw(canvas);
        p2_tocak_p.draw(canvas);
        p2_tocak_z.draw(canvas);
        p2_raketa.draw(canvas);
        p2_busilica.draw(canvas);
        p2_testera.draw(canvas);
        p2_forklift.draw(canvas);
        p2_buzdovan.draw(canvas);


        p1_raketa_rotirana.draw(canvas);
        p1_busilica_rotirana.draw(canvas);
        p1_testera_rotirana.draw(canvas);
        p1_ispaljena_raketa_rotirana.draw(canvas);


        p2_raketa_rotirana.draw(canvas);
        p2_busilica_rotirana.draw(canvas);
        p2_testera_rotirana.draw(canvas);
        p2_ispaljena_raketa_rotirana.draw(canvas);

        /***********DA BI SE LEPO VIDELI NA CANVASU****/
        p1_buzdovan_desno.draw(canvas);
        p1_forklift_desno.draw(canvas);
        /*******************************************/
        p2_buzdovan_levo.draw(canvas);
        p2_buzdovan_dole.draw(canvas);
        p2_buzdovan_gore.draw(canvas);
        p2_forklift_levo.draw(canvas);
        p2_forklift_dole.draw(canvas);
        p2_forklift_gore.draw(canvas);
        eksplozija.draw(canvas);
        porazena_macka.draw(canvas);

        ispaljena_raketa_p1.draw(canvas);
        ispaljena_raketa_p2.draw(canvas);

        return_to_garage.draw(canvas);
        fire_button.draw(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }


}

