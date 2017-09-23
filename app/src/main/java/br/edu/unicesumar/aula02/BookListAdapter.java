package br.edu.unicesumar.aula02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.unicesumar.model.Livro;

/**
 * Created by UniCesumar on 02/09/2017.
 */
public class BookListAdapter extends BaseAdapter {
    private final Context context;
    private final List<Livro> lista;

    public BookListAdapter(Context context, List<Livro> lista) {
        this.context = context;
        this.lista = lista;
    }

    public int getCount() {
        return lista.size();
    }

    public Object getItem(int position) {
        return lista.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View arg1, ViewGroup arg2) {

        Livro c = lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.books_list, null);

        TextView nomeCidade = (TextView) view.findViewById(R.id.grNomeLivro);
        nomeCidade.setText(c.getNomeLivro());

        TextView nomePais = (TextView) view.findViewById(R.id.grNomeAutor);
        nomePais.setText(c.getAutor());
        return view;
    }

}
