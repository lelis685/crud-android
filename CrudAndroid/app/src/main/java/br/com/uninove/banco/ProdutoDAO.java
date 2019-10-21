package br.com.uninove.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.uninove.model.Produto;

public class ProdutoDAO {

    private Conexao conexao;
    private SQLiteDatabase bd;


    public  ProdutoDAO(Context context){
        conexao = new Conexao(context);
        bd = conexao.getWritableDatabase();
    }

    public long inserir (Produto produto){
        // valores para inserir no banco
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("categoria", produto.getCategoria());
        values.put("valor", produto.getValor());

        // retorna id produto inserido
       return  bd.insert("produto",null,values);
    }



}
