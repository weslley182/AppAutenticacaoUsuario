package br.edu.unicesumar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.unicesumar.model.Livro;

/**
 * Created by UniCesumar on 02/09/2017.
 */
public class LivroDao {
    protected SQLiteDatabase db;
    private static final String NOME_TABELA = "livro";

    public LivroDao(Context ctx) {
        db = ctx.openOrCreateDatabase("conexao", Context.MODE_PRIVATE,
                null);
    }

    protected LivroDao() {

    }


    public List<Livro> listarLivros() {
        Cursor c = db.query(NOME_TABELA, new String[]{"nomeLivro","autor", "id"}, null, null, null, null, null);
        List<Livro> livros = new ArrayList<Livro>();

        if (c.moveToFirst()) {
            do {
                Livro livro = new Livro();
                livro.setNomeLivro(c.getString(0));
                livro.setAutor(c.getString(1));
                livro.setId(c.getLong(2));
                livros.add(livro);
            } while (c.moveToNext());
        }
        Log.i("BANCO", "Quantidade de registros:" + livros.size());
        return livros;
    }

    public int deletar(Livro livro) {
        String where = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(livro.getId())};

        int count = db.delete(NOME_TABELA, where, whereArgs);
        Log.i("BANCO", "Deletou " + count + " registros");
        return count;
    }

    public int salvar(Livro livro) {
        if (livro.getId() != null) {
            return atualizar(livro);
        } else {
            return inserir(livro);
        }
    }

    private int atualizar(Livro livro) {
        ContentValues values = new ContentValues();
        values.put("nomeLivro", livro.getNomeLivro());
        values.put("autor", livro.getAutor());

        String where = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(livro.getId())};

        int count = db.update(NOME_TABELA, values, where, whereArgs);
        Log.i("BANCO", "Atualizou " + count + " registros");
        return count;
    }

    private int inserir(Livro livro) {

        ContentValues values = new ContentValues();
        values.put("nomeLivro", livro.getNomeLivro());
        values.put("autor", livro.getAutor());


        long count = db.insert(NOME_TABELA, null, values);
        Log.i("BANCO", "Atualizou " + count + " registros");
        return (count == 1l ? 1 : 0);
    }

    public void fechar() {
        if (db != null) {
            db.close();
        }
    }

    public Livro buscar(Long id) {
        Cursor c = db.query(NOME_TABELA, new String[]{"nomeLivro",
                "autor"}, "id=" + id, null, null, null, null);
        Livro livro = new Livro();

        if (c.moveToFirst()) {
            do {
                livro.setId(id);
                livro.setNomeLivro(c.getString(0));
                livro.setAutor(c.getString(1));

            } while (c.moveToNext());
        }
        return livro;
    }


}
