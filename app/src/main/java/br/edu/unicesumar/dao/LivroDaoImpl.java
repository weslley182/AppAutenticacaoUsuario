package br.edu.unicesumar.dao;

import android.content.Context;

import br.edu.unicesumar.bd.SQLiteHelper;

/**
 * Created by UniCesumar on 02/09/2017.
 */
public class LivroDaoImpl extends LivroDao {

    private SQLiteHelper sqlHelper;

    private static final String SCRIPT_DELETE = "DROP TABLE IF EXISTS livro";

    private static final String[] SCRIPT_CREATE = new String[]{
            "CREATE TABLE livro(id integer primary key autoincrement, "
                    + " nomeLivro text not null, "
                    + " autor text not null); ",
            "insert into livro (nomeLivro, autor) values('dominando o android', 'ricardo lecheta');" ,
            "insert into livro (nomeLivro, autor) values('clean coder', 'uncle bob');" ,
            "insert into livro (nomeLivro, autor) values('biblia', 'Deus');" ,
            "insert into livro (nomeLivro, autor) values('turma da monica', 'Mauricio de souza');"};

    public LivroDaoImpl(Context ctx) {
        sqlHelper = new SQLiteHelper(ctx, "wagnerfuscaDB", 3, SCRIPT_CREATE, SCRIPT_DELETE);
        db = sqlHelper.getWritableDatabase();

    }

    @Override
    public void fechar() {
        super.fechar();
        if (sqlHelper != null) {
            sqlHelper.close();
        }
    }

}

