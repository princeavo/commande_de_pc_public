package com.example.commande_pc;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.commande_pc.database.SqliteDatabaseHelper;
import com.example.commande_pc.entity.Administrator;
import com.example.commande_pc.entity.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.commande_pc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    private static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = Utils.getLoggedUser(MainActivity.this);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        this.removeAllMenuItems(navigationView);
        int [] accessibleItems = null;
        if(user.getRole().getName().equals("requester")){
            accessibleItems = new int[]{
                    R.id.nav_home,
                    R.id.nav_requester_commande_pc,
                    R.id.nav_requester_etat_commande,
                    R.id.nav_logout


            };
            this.addItemToMenu(navigationView,accessibleItems);
        }else if(user.getRole().getName().equals("administrator")){
            accessibleItems = new int[]{
                    R.id.nav_home,
                    R.id.nav_requester_add,
                    R.id.nav_requester_list,
                    R.id.nav_empty_database,
                    R.id.nav_reset_stock,
                    R.id.nav_reset_database,
                    R.id.nav_logout
            };
            this.addItemToMenu(navigationView,accessibleItems);
        }else if(user.getRole().getName().equals("storekeeper")){
            accessibleItems = new int[]{
                    R.id.nav_home,
                    R.id.nav_store_almenter_stock,
                    R.id.nav_show_stock,
                    R.id.nav_logout
            };
            this.addItemToMenu(navigationView,accessibleItems);
        }else if(user.getRole().getName().equals("assembler")){
            navigationView.getMenu().findItem(R.id.nav_requester_add).setVisible(false);
        }
        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                User.logout(MainActivity.this);
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });
        navigationView.getMenu().findItem(R.id.nav_empty_database).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                ((Administrator)MainActivity.getUser()).emptyDatabase();
                Utils.showMessageDialog(MainActivity.this,"Opération réussie","Information");
                return true;
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        if(accessibleItems == null){
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home,R.id.nav_logout)
                    .setOpenableLayout(drawer)
                    .build();
        }else{
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    accessibleItems)
                    .setOpenableLayout(drawer)
                    .build();
        }
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View headerView = navigationView.getHeaderView(0);

        System.out.println("User: " + user.getLastName());
        TextView userNameTextView = headerView.findViewById(R.id.login_user_name);
        System.out.println(userNameTextView);
        userNameTextView.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        TextView userEmailTextView = headerView.findViewById(R.id.login_user_email);
        userEmailTextView.setText(user.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void removeAllMenuItems(NavigationView navigation){
        int [] menuItems = {
                R.id.nav_requester_add,
                R.id.nav_gallery,
                R.id.nav_requester_etat_commande,
                R.id.nav_requester_commande_pc,
                R.id.nav_store_delete,
                R.id.nav_accpter_commande,
                R.id.nav_alimenter_stock,
                R.id.nav_cloture_commande,
                R.id.nav_etat_stock,
                R.id.nav_rejeter_coommande,
                R.id.nav_requester_list,
                R.id.nav_requester_suprimer_commande,
                R.id.nav_store_almenter_stock,
                R.id.nav_slideshow,
                R.id.nav_logout,
                R.id.nav_home,
                R.id.nav_show_stock,
                R.id.nav_reset_stock,
                R.id.nav_reset_database,
                R.id.nav_empty_database
        };
        for(int menu_item_id : menuItems){
            MenuItem menuItem = navigation.getMenu().findItem(menu_item_id);
            if(menuItem != null)
                menuItem.setVisible(false);
        }
    }
    private void addItemToMenu(NavigationView navigation,int[] menu_item_ids){
        for(int id : menu_item_ids)
            navigation.getMenu().findItem(id).setVisible(true);
    }
    public static User getUser(){
        return user;
    }
}
