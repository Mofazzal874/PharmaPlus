package com.example.projecto.ui.category;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projecto.R;
import com.example.projecto.activities.UseDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    ArrayList<String> UseList = new ArrayList<String>();
    ArrayList<String> NameList = new ArrayList<String>();

    ListView list;

    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category,container,false);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("MedInfo");

        list = root.findViewById(R.id.list_item);
        progressBar = root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        extractData();
        return root;
    }

    private void extractData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://www.myjsons.com/v/bruh";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("medicines");
                    for(int i=0; i<array.length();i++){
                        JSONObject element = array.getJSONObject(i);
                        String use = element.getString("use");
                        String name = element.getString("name");
                        UseList.add(use);
                        NameList.add(name);

                        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,NameList);
                        list.setAdapter(arrayAdapter);

                        progressBar.setVisibility(View.GONE);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), UseDetails.class);
                                intent.putExtra("use",UseList.get(position));
                                startActivity(intent);
                            }
                        });

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

}