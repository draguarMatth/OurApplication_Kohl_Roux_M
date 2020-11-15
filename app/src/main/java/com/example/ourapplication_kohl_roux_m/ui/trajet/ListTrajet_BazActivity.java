package com.example.ourapplication_kohl_roux_m.ui.trajet;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapter;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListTrajet_BazActivity extends BaseActivity {

    private static final String TAG = "ListTrajet";

    private List<TrajetEntity> trajets;
    private RecyclerAdapter<TrajetEntity> adapter;
    private TrajetListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_list_trajet, frameLayout);

        setTitle(/* getString(R.string.title_activity_accounts) */ "Liste Trajets");
        navigationView.setCheckedItem(position);

        RecyclerView recyclerView = findViewById(R.id.trajetsRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        // mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
//        String user = settings.getString(BaseActivity.PREFS_USER, null);

/*        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trajet);

//        listView = findViewById(R.id.listview);

        TrajetRepository repositTrajet;
//        TrajetListByNameViewModel listTest = new TrajetListByNameViewModel(getApplicationContext(), "Jvéoboulo",repositTrajet);
        String[] values = new String[]{
                "TrajetEntity 24.10.20    12km", "TrajetEntity 25.10.20    12km", "TrajetEntity 26.10.20    13km"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);
*/
        trajets = new ArrayList<>();
        adapter = new RecyclerAdapter<TrajetEntity>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + trajets.get(position).getName());

                Intent intent = new Intent(ListTrajet_BazActivity.this, TrajetActivity.class);
                intent.setFlags(
                        Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                Intent.FLAG_ACTIVITY_NO_HISTORY
                );
                intent.putExtra("TraejtId", trajets.get(position).getUid());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + trajets.get(position).getName());

                createDeleteDialog(position);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
                    Intent intent = new Intent(ListTrajet_BazActivity.this, TrajetActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    startActivity(intent);
                }
        );

     //   String traj = "Jvéoboulot";
        TrajetListViewModel.Factory factory = new TrajetListViewModel.Factory(
                getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(TrajetListViewModel.class);
        viewModel.getTrajetsviewMod().observe(this, trajetsL -> {
            if (trajetsL != null) {
                trajets = trajetsL;
                adapter.setData(trajets);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }


        /*
        The activity has to be finished manually in order to guarantee the navigation hierarchy working.
        */
        finish();
        return super.onNavigationItemSelected(item);

    }

    private void createDeleteDialog(final int position) {
   /*     final TrajetEntity account = trajets.get(position);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.row_delete_item, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.title_activity_delete_account));
        alertDialog.setCancelable(false);

        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
        deleteMessage.setText(String.format(getString(R.string.account_delete_msg), account.getName()));

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_accept), (dialog, which) -> {
            Toast toast = Toast.makeText(this, getString(R.string.account_deleted), Toast.LENGTH_LONG);
            viewModel.deleteAccount(account, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "deleteAccount: success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "deleteAccount: failure", e);
                }
            });
            toast.show();
        });
*/
    }

}
