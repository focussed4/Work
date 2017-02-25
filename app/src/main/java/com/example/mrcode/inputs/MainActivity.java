package com.example.mrcode.inputs;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Payment> payments;
    Payment payment;
    String log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // array to hold the Payment


        DbHandler db = new DbHandler(this);

        // adding Payment to database
        //db.addContent(new Payment(1,"Food out", 200));
        //db.addContent(new Payment(2,"Food in", 35));
        //db.addContent(new Payment(3,"Taxes", 12));

       /* for(int i = 0; i < 100; i++){
            db.DeletePayment(i);
        }*/

        //payment = db.getContent(2);


        payment = new Payment(1, "Food outty", 100);

        // getting database records
       /* Payment.add(db.getContent(1));
        Payment.add(db.getContent(2));
        Payment.add(db.getContent(3));*/

        db.updatePayment(payment);

        //db.DeleteTable();

        List<Payment> payments = db.getAllPayment();

        EditText editTextName = (EditText) findViewById(R.id.editText2);

        for (Payment out : payments){
            log = "id: " + out.getId() + "name: "  + out.getName() +
                    "amount: " + out.getAmount();

            Log.d("payment: ", log);
        }


        //Log.d("Payment: ", log);

        //values.moveToFirst();

     /*   // print out the records of each payment
        for (Payment out : Payment){
            String log = "id: " + out.GetId() + "name: "  + out.GetName() +
                    "amount: " + out.GetAmount();
        }*/

        editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener(){
                          @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent){

                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT){
                    // show toast for input
                    String inputText = textView.getText().toString();
                    //Toast.makeText(MainActivity.this, "You've spent: "
                    //        + inputText, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, log, Toast.LENGTH_SHORT).show();

                }
                return handled;
            }

    });


    }
}
