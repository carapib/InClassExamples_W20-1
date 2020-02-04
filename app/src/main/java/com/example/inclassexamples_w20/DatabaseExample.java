package com.example.inclassexamples_w20;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DatabaseExample extends AppCompatActivity {

    ArrayList<Contact> contactsList = new ArrayList<>();
    private static int ACTIVITY_VIEW_CONTACT = 33;
    int positionClicked = 0;
    MyOwnAdapter myAdapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_layout);

        //Get the fields from the screen:
        EditText nameEdit = (EditText)findViewById(R.id.nameInput);
        EditText emailEdit = (EditText) findViewById(R.id.emailInput);
        Button insertButton = (Button)findViewById(R.id.insert);
        ListView theList = (ListView)findViewById(R.id.the_list);

        loadDataFromDatabase(); //get any previously saved Contact objects

        //create an adapter object and send it to the listVIew
        myAdapter = new MyOwnAdapter();
        theList.setAdapter(myAdapter);


        //This listens for items being clicked in the list view
        theList.setOnItemClickListener(( parent,  view,  position,  id) -> {
            showContact( position );
        });


        //Listen for an insert button click event:
        insertButton.setOnClickListener( click ->
        {
            //get the email and name that were typed
            String name = nameEdit.getText().toString();
            String email = emailEdit.getText().toString();


            //add to the database and get the new ID
            ContentValues newRowValues = new ContentValues();

            //Now provide a value for every database column defined in MyOpener.java:
            //put string name in the NAME column:
            newRowValues.put(MyOpener.COL_NAME, name);
            //put string email in the EMAIL column:
            newRowValues.put(MyOpener.COL_EMAIL, email);

            //Now insert in the database:
            long newId = db.insert(MyOpener.TABLE_NAME, null, newRowValues);

            //now you have the newId, you can create the Contact object
            Contact newContact = new Contact(name, email, newId);

            //add the new contact to the list:
            contactsList.add(newContact);
            //update the listView:
            myAdapter.notifyDataSetChanged();

            //clear the EditText fields:
            nameEdit.setText("");
            emailEdit.setText("");

            //Show the id of the inserted item:
            Toast.makeText(this, "Inserted item id:"+newId, Toast.LENGTH_LONG).show();
        });
    }


    private void loadDataFromDatabase()
    {
        //get a database connection:
        MyOpener dbOpener = new MyOpener(this);
        db = dbOpener.getWritableDatabase();


        // We want to get all of the columns. Look at MyOpener.java for the definitions:
        String [] columns = {MyOpener.COL_ID, MyOpener.COL_EMAIL, MyOpener.COL_NAME};
        //query all the results from the database:
        Cursor results = db.query(false, MyOpener.TABLE_NAME, columns, null, null, null, null, null, null);

        //Now the results object has rows of results that match the query.
        //find the column indices:
        int emailColumnIndex = results.getColumnIndex(MyOpener.COL_EMAIL);
        int nameColIndex = results.getColumnIndex(MyOpener.COL_NAME);
        int idColIndex = results.getColumnIndex(MyOpener.COL_ID);

        //iterate over the results, return true if there is a next item:
        while(results.moveToNext())
        {
            String name = results.getString(nameColIndex);
            String email = results.getString(emailColumnIndex);
            long id = results.getLong(idColIndex);

            //add the new Contact to the array list:
            contactsList.add(new Contact(name, email, id));
        }

        //At this point, the contactsList array has loaded every row from the cursor.
    }


    protected void showContact(int position)
    {
        Contact selectedContact = contactsList.get(position);

        View contact_view = getLayoutInflater().inflate(R.layout.contact_edit, null);
        //get the TextViews
        EditText rowName = contact_view.findViewById(R.id.row_name);
        EditText rowEmail = contact_view.findViewById(R.id.row_email);
        TextView rowId = contact_view.findViewById(R.id.row_id);

        //set the fields for the alert dialog
        rowName.setText(selectedContact.getName());
        rowEmail.setText(selectedContact.getEmail());
        rowId.setText("id:" + selectedContact.getId());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You clicked on item #" + position)
                .setMessage("You can update the fields and then click update to save in the database")
                .setView(contact_view) //add the 3 edit texts showing the contact information
                .setPositiveButton("Update", (click, b) -> {
                    selectedContact.update(rowName.getText().toString(), rowEmail.getText().toString());
                    updateContact(selectedContact);
                    myAdapter.notifyDataSetChanged(); //the email and name have changed so rebuild the list
                })
                .setNegativeButton("Delete", (click, b) -> {
                    deleteContact(selectedContact); //remove the contact from database
                    contactsList.remove(position); //remove the contact from contact list
                    myAdapter.notifyDataSetChanged(); //there is one less item so update the list
                })
                .setNeutralButton("dismiss", (click, b) -> { })
                .create().show();
    }

    protected void updateContact(Contact c)
    {
        //Create a ContentValues object to represent a database row:
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(MyOpener.COL_NAME, c.getName());
        updatedValues.put(MyOpener.COL_EMAIL, c.getEmail());

        //now call the update function:
        db.update(MyOpener.TABLE_NAME, updatedValues, MyOpener.COL_ID + "= ?", new String[] {Long.toString(c.getId())});
    }

    protected void deleteContact(Contact c)
    {
        db.delete(MyOpener.TABLE_NAME, MyOpener.COL_ID + "= ?", new String[] {Long.toString(c.getId())});
    }

    //This class needs 4 functions to work properly:
    protected class MyOwnAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return contactsList.size();
        }

        public Contact getItem(int position){
            return contactsList.get(position);
        }

        public View getView(int position, View old, ViewGroup parent)
        {
            View newView = getLayoutInflater().inflate(R.layout.contact_row, parent, false );

            Contact thisRow = getItem(position);

            //get the TextViews
            TextView rowName = (TextView)newView.findViewById(R.id.row_name);
            TextView rowEmail = (TextView)newView.findViewById(R.id.row_email);
            TextView rowId = (TextView)newView.findViewById(R.id.row_id);

            //update the text fields:
            rowName.setText(  thisRow.getName());
            rowEmail.setText( thisRow.getEmail());
            rowId.setText("id:" + thisRow.getId());

            //return the row:
            return newView;
        }

        //last week we returned (long) position. Now we return the object's database id that we get from line 73
        public long getItemId(int position)
        {
            return getItem(position).getId();
        }
    }
}
