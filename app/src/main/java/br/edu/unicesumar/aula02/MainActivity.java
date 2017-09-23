package br.edu.unicesumar.aula02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;


public class MainActivity extends Activity implements View.OnClickListener, Runnable {

    private static final String CATEGORIA = "CONEXAO";
    private ProgressDialog dialog;
    private int idBotaoSelecionado;

    private static final String HOST = "projetoautenticacao.herokuapp.com";
    private static final String PATH = "/rest/usuario/";
    private static final String VALIDAR = "validar/";
    private static final String INSERIR = "inserir/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapearBotoesDaTela();
    }

    private void mapearBotoesDaTela() {
        Button btnLogar = (Button) findViewById(R.id.btnLogar);
        btnLogar.setOnClickListener(this);
    }

    public void onClick(View view) {
        dialog = ProgressDialog
                .show(this,
                        "Loading...",
                        "Acessando base de dados com web service, por favor aguarde...",
                        false, true);

        idBotaoSelecionado = view.getId();
        new Thread(this).start();
    }

    private void btnLogar() {
    }

    private String getValorFormatado(EditText editText) {
        return editText.getText().toString().toUpperCase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_configuracoes:
                Toast.makeText(getApplicationContext(), "clicou no menu configuracoes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mn_novo:
                Toast.makeText(getApplicationContext(), "clicou no menu novo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mn_sobre:
                Intent it = new Intent(getApplicationContext(), SobreActivity.class);
                startActivity(it);
            default:
                break;
        }

        return true;

    }

    @Override
    protected void onRestart() {
        // Toast.makeText(getApplicationContext(), "voltou a tela inicial", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        // Toast.makeText(getApplicationContext(), "parou a tela inicial", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    public static String converseGet(String host, int port, String path)
            throws IOException {
        HttpHost target = new HttpHost(host, port);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(path);
        HttpEntity results = null;

        try {
            HttpResponse response = client.execute(target, get);
            results = response.getEntity();
            return EntityUtils.toString(results);
        } catch (Exception e) {
            Log.e(CATEGORIA, e.getMessage(), e);
            throw new RuntimeException("Nao encontrou o webservice " + e.getMessage());
        } finally {
            if (results != null) {
                try {
                    results.consumeContent();
                } catch (IOException e) {

                }
            }
        }

    }

    @Override
    public void run() {
        final TextView textResultado = (TextView) findViewById(R.id.txtResult);
        EditText edtLogin = (EditText) findViewById(R.id.edtLogin);
        EditText edtSenha = (EditText) findViewById(R.id.edtSenha);

        String novoPath = "";
        String resultado = "";
        novoPath = PATH + VALIDAR + URLEncoder.encode(edtLogin.getText().toString()) + '/' + URLEncoder.encode(edtSenha.getText().toString());
        try {
            resultado = converseGet(HOST, 80, novoPath);
            apresentaResultadoNoTextView(textResultado, resultado);
        } catch (Exception e) {
            Log.e(CATEGORIA, e.getMessage(), e);
        } finally {
            dialog.dismiss();
        }
    }

    private void apresentaResultadoNoTextView(TextView textResultado, String resultado) {
        textResultado.setVisibility(View.VISIBLE);
        textResultado.setText("Resultado: " + resultado);
        Log.i(CATEGORIA, String.valueOf(resultado));
    }



}
