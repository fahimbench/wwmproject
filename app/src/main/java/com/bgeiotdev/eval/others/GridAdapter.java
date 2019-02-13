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

        cellule.setText("");
        cellule.setHeight((int) cellSize);
        cellule.setWidth((int) cellSize);
        cellule.setTextSize(pxtodp((int) Math.round(cellSize / 1.5)));
        int x = (int) Math.floor((double) position / 9) + 1;
        int y = (int) (((double) position) % 9) + 1;
        posxy = new Position(x, y);

        items[position] = new Cell(-1, posxy);

        cellule.setOnClickListener(new CellOnClickListener(cellule, items[position], position, activity));


        return convertView;
    }

    public float pxtodp(int px) {
        return (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public class CellOnClickListener implements View.OnClickListener {

        private TextView tv;
        private Position position;
        private int value;
        private Activity act;
        private Cell cell;


        public CellOnClickListener(TextView tv, Cell cell, int value, Activity act) {
            this.tv = tv;
            this.cell = cell;
            this.position = cell.getPosition();
            this.value = value;
            this.act = act;
        }

        @Override
        public void onClick(View v) {
            tv.setBackgroundColor(Color.parseColor("#80FF0000"));
            chooseNumberAppear();
        }

        public void chooseNumberAppear() {
            LinearLayout ll = act.findViewById(R.id.button_panel);
            ll.setVisibility(View.VISIBLE);

            for (int i = 1; i < 10; i++) {
                final int finalI = i;
                Button button = new Button(act);
                button.setText(String.valueOf(i));

                ll.addView(button);

                button.setOnClickListener(new NumberClickListener(i,ll,button,tv,cell));

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

        public NumberClickListener(int i, LinearLayout ll, Button button, TextView tv, Cell cell) {
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
            System.out.println(cell.getValue());
        }

    };
}