package br.edu.unicesumar.aula02;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import br.edu.unicesumar.dao.LivroDaoImpl;
import br.edu.unicesumar.model.Livro;


public class BooksActivity extends ListActivity {

    private LivroDaoImpl dao;
    private List<Livro> livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new LivroDaoImpl(getApplicationContext());
        listarLivrosEmTela();
    }

    private void listarLivrosEmTela() {
        livros = dao.listarLivros();
        setListAdapter(new BookListAdapter(getApplicationContext(), livros));
        registerForContextMenu(getListView());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_books, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_tela_bem_vindo:
                Intent it = new Intent(getApplicationContext(), BemVindoActivity.class);
                startActivity(it);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_edit_livros, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo selecionado = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.mn_delete:
                dao.deletar(livros.get(selecionado.position));
                listarLivrosEmTela();
                break;
            case R.id.mn_edit:
                break;
            default:
                break;
        }

        return true;
    }
}
