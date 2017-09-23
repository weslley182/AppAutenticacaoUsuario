package br.edu.unicesumar.aula02;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class BemVindoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        TextView txtBoasVindas = (TextView) findViewById(R.id.txtBoasVindas);
        txtBoasVindas.setText(getString(R.string.txtBemVindo) + " " + getIntent().getStringExtra("nomeUsuario"));

        abrirSite();
        fazerLigacaoTelefonica();
        tirarUmaFoto();
    }

    private void tirarUmaFoto() {
        ImageButton btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(it);

            }
        });
    }

    private void fazerLigacaoTelefonica() {
        ImageButton btnLigar = (ImageButton) findViewById(R.id.btnLigar);
        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:99998888");
                Intent it = new Intent(Intent.ACTION_CALL,uri);
                startActivity(it);

            }
        });
    }

    private void abrirSite() {
        ImageButton btnSite = (ImageButton) findViewById(R.id.btnSite);
        btnSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtSite = (EditText) findViewById(R.id.edtSite);
                Uri uri = Uri.parse("http://" + edtSite.getText().toString());
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bem_vindo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
