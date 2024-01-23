package com.kulswaminitech.sanjivanimentormeet;

import static android.app.ProgressDialog.show;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class UploadCertificateActivity extends AppCompatActivity {
    EditText certificate_name;
    Button select_certificatebtn,submit_certificatebtn;
    Spinner spinnerYear,spinnerRank;
    TextView selected_file_text;
    private static final int PICK_PDF_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_certificate);

        certificate_name = (EditText) findViewById(R.id.certificate_name);
        select_certificatebtn = (Button) findViewById(R.id.select_certificate);
        submit_certificatebtn = (Button) findViewById(R.id.submit_certificate);
        selected_file_text = (TextView) findViewById(R.id.selected_file_text);
        spinnerYear = (Spinner) findViewById(R.id.year_spinner);
        spinnerRank = (Spinner) findViewById(R.id.rank_spinner);

        String[] yearArraySpinner = new String[]{"FY", "SY", "TY"};
        String[] rankArraySpinner = new String[]{"FIRST","SECOND","THIRD","RUNNER UP","PARTICIPANT"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                yearArraySpinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,rankArraySpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerRank.setAdapter(adapter2);

        select_certificatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                gotoUrl("https://drive.google.com/drive/folders/1amUUbK-7dBN9SrdEp5G_LB8dglOj0ddP");
                // Open file chooser
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, PICK_PDF_REQUEST);
            }
        });

        submit_certificatebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(certificate_name.getText().length() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter cerificate name...!", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    //store the certificate to database table
                    String c_name = certificate_name.getText().toString();
                    String c_year = spinnerYear.getSelectedItem().toString();
                    String c_rank = spinnerRank.getSelectedItem().toString();
                    //pdfUri store it in table

                }
            }
        });
    }

    //open links
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the selected PDF file URI
            Uri pdfUri = data.getData();

            // Now you can use the pdfUri to do further processing
            // For example, you might want to display the file name or upload the file to a server
            String pdfFileName = getFileName(pdfUri);
            selected_file_text.setText("selected file : "+pdfFileName);
        }
    }
    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

}
