package com.bgeiotdev.eval.others;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bgeiotdev.eval.R;
import com.bgeiotdev.eval.classes.Cell;
import com.bgeiotdev.eval.classes.Position;

public class GridAdapter extends ArrayAdapter {
    private Context context;
    private Cell[] items;
    private int resource;
    private double cellSize;
    private double screenSize;
    private Activity activity;
    private LayoutInflater inflater;
    private ViewGroup parent;

    public GridAdapter(Context context, int resource, Cell[] items, double screenSize, Activity activity) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
        this.screenSize = screenSize;
        this.activity = activity;

        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Cell getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView cellule;
        GridView grid;
        Position posxy;


        cellSize = (int) Math.floor(screenSize / 9);

        if (convertView == null) {
            convertView = inflater.inflate(resource, null);
        }

        cellule = convertView.findViewById(R.id.cell);
        this.parent = parent;
        cellule.setText((items[position].getValue() == 0) ? "" : String.valueOf(items[position].getValue()));
        if(items[position].getValue() != 0){
            cellule.setEnabled(false);
            cellule.setTextColor(Color.parseColor("#009933"));
        }else{
            cellule.setTextColor(Color.parseColor("#000000"));
        }

        cellule.setHeight((int) cellSize);
        cellule.setWidth((int) cellSize);
        cellule.setTextSize(pxtodp((int) Math.round(cellSize / 1.5)));
        int x = (int) Math.floor((double) position / 9) + 1;
        int y = (int) (((double) position) % 9) + 1;


        cellule.setOnClickListener(new CellOnClickListener(items, cellule, items[position], activity));


        return convertView;
    }

    public float pxtodp(int px) {
        return (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public void clearAllBackground(){
        for (int i = 0; i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            v.setBackgroundColor(0);
        }
    }
    public class CellOnClickListener implements View.OnClickListener {

        private TextView tv;
        private Position position;
        private int value;
        private Activity act;
        private Cell cell;
        private Cell[] items;

        public CellOnClickListener(Cell[] items, TextView tv, Cell cell, Activity act) {
            this.items = items;
            this.tv = tv;
            this.cell = cell;
            this.position = cell.getPosition();
            this.value = value;
            this.act = act;
        }

        @Override
        public void onClick(View v) {
            clearAllBackground();
            tv.setBackgroundColor(Color.parseColor("#80FF0000"));
            chooseNumberAppear();
            System.out.println(items.toString());
        }

        public void chooseNumberAppear() {

            LinearLayout ll = act.findViewById(R.id.button_panel);
            ll.setVisibility(View.VISIBLE);
            ll.removeAllViews();
            for (int i = 1; i < 10; i++) {
                final int finalI = i;
                Button button = new Button(act);
                button.setText(String.valueOf(i));
                button.setHeight((int) cellSize);
                button.setWidth((int) cellSize);
                button.setTextSize(pxtodp((int) Math.round(cellSize / 1.5)));
                ll.addView(button);

                button.setOnClickListener(new NumberClickListener(items, i,ll,button,tv,cell));

            }
        }
    }

    public class NumberClickListener implements View.OnClickListener
    {

        int i;
        LinearLayout ll;
        Button button;
        TextView tv;
        Cell cell;
        Cell[] items;

        public NumberClickListener(Cell[] items, int i, LinearLayout ll, Button button, TextView tv, Cell cell) {
            this.items =items;
            this.i = i;
            this.ll = ll;
            this.button = button;
            this.tv = tv;
            this.cell = cell;
        }

        @Override
        public void onClick(View v)
        {
            cell.setValue(Integer.valueOf((String) button.getText()));
            tv.setText(button.getText());
            ll.removeAllViews();
            tv.setBackgroundColor(0);
        }

    }
}