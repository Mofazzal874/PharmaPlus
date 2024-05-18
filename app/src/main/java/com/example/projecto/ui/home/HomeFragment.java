package com.example.projecto.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecto.MainActivity;
import com.example.projecto.R;
import com.example.projecto.activities.OrderPlaced;
import com.example.projecto.activities.Welcome;
import com.example.projecto.adapters.CategoryAdapters;
import com.example.projecto.adapters.SuggestedAdapters;
import com.example.projecto.adapters.ViewAllAdapters;
import com.example.projecto.databinding.FragmentHomeBinding;
import com.example.projecto.models.CategoryModel;
import com.example.projecto.models.SuggestedModel;
import com.example.projecto.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Enable options menu for this fragment
    }

    ScrollView scrollView;
    ProgressBar progressBar;

    List<SuggestedModel> suggestedModelList;
    SuggestedAdapters suggestedAdapters;

    List<CategoryModel> categoryModelList;
    CategoryAdapters categoryAdapters;


    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private RecyclerView recyclerViewSearch;
    private ViewAllAdapters viewAllAdapters;



    FirebaseFirestore db;

    RecyclerView suggested,category;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db = FirebaseFirestore.getInstance();

        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        viewAllModelList = new ArrayList<>();
        viewAllAdapters = new ViewAllAdapters(getContext(),viewAllModelList);
        search_box = root.findViewById(R.id.search_box);
        recyclerViewSearch = root.findViewById(R.id.search_rec);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllAdapters);
        recyclerViewSearch.setHasFixedSize(true);

        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("TextWatcher", "Text changed: " + s.toString());
                if (s.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapters.notifyDataSetChanged();
                }
                else{
                    searchProduct(s.toString());
                }
            }
        });


        suggested = root.findViewById(R.id.suggested);
        suggested.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        suggestedModelList = new ArrayList<>();
        suggestedAdapters = new SuggestedAdapters(getActivity(),suggestedModelList);
        suggested.setAdapter(suggestedAdapters);


        db.collection("Suggested")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document: task.getResult()){
                                SuggestedModel suggestedModel = document.toObject(SuggestedModel.class);
                                suggestedModelList.add(suggestedModel);
                                suggestedAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                            //Log.e("FirebaseError", "Error: " + task.getException().getMessage());
                        }
                    }
                });

        category = root.findViewById(R.id.category);
        category.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        categoryModelList = new ArrayList<>();
        categoryAdapters = new CategoryAdapters(getActivity(),categoryModelList);
        category.setAdapter(categoryAdapters);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document: task.getResult()){
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                            //Log.e("FirebaseError", "Error: " + task.getException().getMessage());
                        }
                    }
                });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Intercept back press in HomeFragment
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    // Callback to handle back press
    private final OnBackPressedCallback callback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            requireActivity().moveTaskToBack(true);
            requireActivity().finish();
        }
    };


    private void searchProduct(String type) {
        if (!type.isEmpty()){
            db.collection("Allproducts").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.getResult().size()!=0) {
                                if (task.isSuccessful() && task.getResult() != null) {
                                    viewAllModelList.clear();
                                    viewAllAdapters.notifyDataSetChanged();

                                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                        ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                        viewAllModelList.add(viewAllModel);
                                        viewAllAdapters.notifyDataSetChanged();
                                    }
                                }
                            }
                            else{
                                db.collection("Allproducts").whereEqualTo("name",type).get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.getResult().size()!=0) {
                                                    if (task.isSuccessful() && task.getResult() != null) {
                                                        viewAllModelList.clear();
                                                        viewAllAdapters.notifyDataSetChanged();

                                                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                                            ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                                            viewAllModelList.add(viewAllModel);
                                                            viewAllAdapters.notifyDataSetChanged();
                                                        }
                                                    }
                                                }
                                            }
                                        });
                            }
                        }
                    });

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if (menu.findItem(R.id.action_logout) != null) {
            menu.removeItem(R.id.action_logout);
        }

        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), Welcome.class);
        startActivity(intent);
        getActivity().finish();
    }

}