package com.example.alenpolakof.waterapp;
/**
 * Created by apolakof on 2/28/17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class FragmentTwoHistorical extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.historical, container,
                false);
        getActivity();


        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);


        graph.getViewport().setMinX(1990);
        graph.getViewport().setMaxX(2017);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(15.0);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);


        String opt = ((HistoricalCreateActivity) getActivity()).getFragmentOneH().getOption();
        double latitude = ((HistoricalCreateActivity) getActivity()).getFragmentOneH().getLat();
        double longitude = ((HistoricalCreateActivity) getActivity()).getFragmentOneH().getLong();
        LatLng location = new LatLng(latitude, longitude);

        ArrayList<DataPoint> points = new ArrayList<>();

        for (PurityReport rep : TempDB.getTempDB().getReportsYearLoc(location)) {
            if (opt.equals("Virus")) {
                points.add(new DataPoint(rep.getYear(), rep.getVPPM()));
            } else {
                points.add(new DataPoint(rep.getYear(), rep.getCPPM()));
            }
        };
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points.toArray(new DataPoint[points.size()]));
        graph.addSeries(series);


        Button back = (Button) rootView.findViewById(R.id.hback_fragment_two);
        Button ok = (Button) rootView.findViewById(R.id.hok_fragment_two);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewPager)getActivity().findViewById(R.id.pager)).setCurrentItem(0, true);

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });

        return rootView;

    }

}