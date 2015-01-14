package com.example.catalin.weatherb.Weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.catalin.weatherb.R;

import java.util.List;


/* Aceatsa clasa reprezinta adaptorul pentru lista exploreru-lui de fisiere */
public class ItemArrayAdapter extends ArrayAdapter<Item> {

    private Context c;
    private int id;
    private List<Item> items;

    public ItemArrayAdapter(Context context, int textViewResourceId,
                            List<Item> objects) {

        super(context, textViewResourceId, objects);
        c = context;
        id = textViewResourceId;//id resurselor
        items = objects;//obiectele care vor aparea in lista
    }
    public Item getItem(int i)
    {
        return items.get(i);
    }
    @Override
    //Implementeaza metoda getView
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(id, null);
        }

         /* creaza un nou view al layout-ului si afiseaza in randul listei*/


        final Item o = items.get(position);
        if (o != null) {
            //Acceaseaza fiecare element din rand
            TextView t1 = (TextView) v.findViewById(R.id.TextView01);
            TextView t2 = (TextView) v.findViewById(R.id.TextView02);
            TextView t3 = (TextView) v.findViewById(R.id.TextViewDate);
             /* Preia ImageView si seteaza iimaginea din resurse */
            ImageView imageCity = (ImageView) v.findViewById(R.id.fd_Icon1);
            String uri = "drawable/" + o.getImg();
//            int imageResource = c.getResources().getIdentifier(uri, null, c.getPackageName());
            Drawable image = c.getResources().getDrawable(o.getImg());
            imageCity.setImageResource(o.getImg());
            //Seteaza textul din fiecare View
            //Nume fisier....
            if(t1!=null)
                t1.setText("Name: "+o.getName());
            if(t2!=null)
                t2.setText("Age: "+o.getAge());
            if(t3!=null)
                t3.setText("Score:"+o.getScore());
        }
        return v;
    }
}