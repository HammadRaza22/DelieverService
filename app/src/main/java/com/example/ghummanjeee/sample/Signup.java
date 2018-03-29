package com.example.ghummanjeee.sample;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Signup extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    Button mCreate;
    String id;

    EditText firstname, lastname, emailadd, password, phone, address;
    TextView mLogin;
    ImageView vi;
    int Image_Request_Code = 7;
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private String userID;
    private String mfisrt;
    private String mlast;
    private String mphone;
    private String madd;
    private String mpass;
    private String email;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private StorageReference storageReference;
    public static final String STORAGE_PATH = "Customer/images/";
    public static final String DATABASE_PATH = "Users";
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firstname = (EditText) findViewById(R.id.F_name);
        lastname = (EditText) findViewById(R.id.L_name);
        emailadd = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.qr);
        phone = (EditText) findViewById(R.id.Pheno);
        address = (EditText) findViewById(R.id.Adress);

        createAuthStateListener();
        //mAuth = FirebaseAuth.getInstance();
firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReferenceFromUrl("https://sampleproject-8f6f9.firebaseio.com/Users/Customers/");
        storageReference= FirebaseStorage.getInstance().getReference();
//        databaseReference= FirebaseDatabase.getInstance().getReference(DATABASE_PATH);

      //  userID = mAuth.getCurrentUser().getUid();
        vi = (ImageView) findViewById(R.id.img);
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),0);
            }
        });
        mCreate = (Button) findViewById(R.id.btn_next);
        mLogin = (TextView) findViewById(R.id.link_login);

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             final DatabaseReference id=databaseReference.push();
                id.child("FirstName").setValue(firstname.getText().toString());
                id.child("LastName").setValue(lastname.getText().toString());
                id.child("Email").setValue(emailadd.getText().toString());
                id.child("Password").setValue(password.getText().toString());
                id.child("Phone NO").setValue(phone.getText().toString());
                id.child("Address").setValue(address.getText().toString());
                if(imageUri != null) {
                StorageReference filePath = FirebaseStorage.getInstance().getReferenceFromUrl("https://sampleproject-8f6f9.firebaseio.com/Users/Customers/");

                    Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = filePath.putBytes(data);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        finish();
                        return;
                    }
                });
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        //Map newImage = new HashMap();
                       // newImage.put("profileImageUrl", downloadUrl.toString());
                        id.child("profileImageUrl").setValue(downloadUrl.toString());

                        finish();
                        return;
                    }
                });}
            }

             /*   mfisrt = firstname.getText().toString();
                mlast = lastname.getText().toString();
                email = emailadd.getText().toString();
                mpass = password.getText().toString();
                mphone = phone.getText().toString();
                madd = address.getText().toString();
                Map userInfo = new HashMap();
                userInfo.put("FirstName", mfisrt);
                userInfo.put("LastName", mlast);
                userInfo.put("Email", email);
                userInfo.put("Password", mpass);
                userInfo.put("Phone", mphone);
                userInfo.put("Address", madd);
                databaseReference.updateChildren(userInfo);

                if(imageUri != null) {
                   //  String user_id = mAuth.getCurrentUser().getUid();
                  //  databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(mfisrt);
                    final ProgressDialog progressDialog = new ProgressDialog(Signup.this, R.style.AppTheme_Dark_Dialog);
                    progressDialog.setTitle("Uploading");
                    progressDialog.show();


                    StorageReference filePath = FirebaseStorage.getInstance().getReference().child("profile_images");
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                    byte[] data = baos.toByteArray();
                    UploadTask uploadTask = filePath.putBytes(data);

                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                            finish();

                            return;
                        }
                    });
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();

                            Map newImage = new HashMap();
                            newImage.put("profileImageUrl", downloadUrl.toString());
                            databaseReference.updateChildren(newImage);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Data uploaded", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double totalProgress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded % " + (int) totalProgress);
                        }

                    });}
                    else{
                        Toast.makeText(getApplicationContext(), "Please select data first", Toast.LENGTH_LONG).show();
                        finish();

                }
*/

        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is = new Intent(Signup.this, Login.class);
                startActivity(is);
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(Signup.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Signup Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK){
            imageUri = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
             vi.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getActualImage(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }




}