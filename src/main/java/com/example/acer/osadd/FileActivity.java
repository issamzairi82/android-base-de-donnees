package com.example.acer.osadd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class FileActivity extends AppCompatActivity {

    public final static int ADD_ITEM_ACTIVITY = 50;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    final ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //insertion dans le fichier
        Fichier_info fichier_info_ios=new Fichier_info("IOS","APPLE","iOS, anciennement iPhone OS, est le système d'exploitation mobile développé par Apple pour plusieurs de ses appareils. Il est dérivé de OS X dont il partage les fondations.Le système d'exploitation occupe au maximum 3 Go de la capacité mémoire totale de l'appareil, selon l'appareil.","350 millions","10","450 millions");

        try {
            write(fichier_info_ios);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Fichier_info fichier_info_ando=new Fichier_info("Android","andro","c'est un système d'exploitation mobile basé sur le noyau Linux et développé actuellement par Google Lancé en juin 2007 à la suite du rachat par Google en 2005 de la startup, le système avait d'abord été conçu pour les smartphones et tablettes tactiles, puis s'est diversifié dans les objets connectés et ordinateurs comme les télévisions, les voitures, les ordinateurs et les smartwatch.","1.4 milliard","15","2 milliards");

        try {
            write(fichier_info_ando);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Fichier_info fichier_info_wind=new Fichier_info("Windows phone","windo","Windows Phone est un système d'exploitation mobile développé par Microsoft pour succéder à Windows Mobile,Cependant à partir de Windows Phone 8, Microsoft a proposé des fonctions avancées pour les entreprises ainsi qu'un espace d'applications réservé aux professionnels.","150 millions","4","165 millions");

        try {
            write(fichier_info_wind);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Fichier_info fichier_info_lin=new Fichier_info("linux","lin","c' est un système d'exploitation développé par Microsoft pour succéder à Windows Mobile,Cependant à partir de Windows Phone 8, Microsoft a proposé des fonctions avancées pour les entreprises ainsi qu'un espace d'applications réservé aux professionnels.","150 millions","4","165 millions");

        try {
            write(fichier_info_lin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "l'ecriture dans le fichier est faite", Toast.LENGTH_LONG).show();
        list.add("Android");
        list.add("IOS");
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fichier_info os_data_file1 = null;
                try {

                    os_data_file1 = rechreche(read(),(String)((TextView) view).getText());
                }
                catch (IOException e)
                {
                    Toast.makeText(getApplicationContext(),"erreur s'est produit lors de chargement de fichier ",Toast.LENGTH_LONG).show();
                }

                if(os_data_file1==null)
                {
                    Toast.makeText(getApplicationContext()," objet null ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), AfficheOS.class);
                    intent.putExtra("os",os_data_file1);
                    startActivityForResult(intent, ADD_ITEM_ACTIVITY);

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_ajout) {
            Intent intent = new Intent(getApplicationContext(), ADD_OS.class);
            startActivityForResult(intent, ADD_ITEM_ACTIVITY);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK )
        {
            list.add(data.getExtras().getString("item"));
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "AJOUT EFFECTUER AVEC SUCCEE ", Toast.LENGTH_LONG).show();
        }
        if(resultCode == RESULT_CANCELED)
        {
            Toast.makeText(getApplicationContext()," AJOUT ANNULER ",Toast.LENGTH_LONG).show();
        }
    }

    public Fichier_info  rechreche(ArrayList<Fichier_info> table , String s)
    {
        for (Fichier_info os :table
                ) {
            if(os.getOps().equals(s))
            {
                return os;
            }
        }
        return null;
    }


    public  void write(Fichier_info state) throws IOException

    {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {

            fos = new FileOutputStream(new File(getApplicationContext().getFilesDir(),"file.txt"));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(state);
            oos.writeObject(state);
            oos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<Fichier_info> read() throws IOException {
        ArrayList<Fichier_info> statistiques = new ArrayList<Fichier_info>();

        ObjectInputStream ois = null;
        FileInputStream fis;
        try {
            fis = new FileInputStream(new File(getApplicationContext().getFilesDir(), "file.txt"));
            ois = new ObjectInputStream(fis);
            for ( Fichier_info i =(Fichier_info) ois.readObject() ; i!=null ;  i =(Fichier_info) ois.readObject() )
            {
                statistiques.add(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally
        {
            ois.close();
            return statistiques ;
        }

    }
}