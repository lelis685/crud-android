package br.com.uninove.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public List<Produto> listarTodos (){
        List<Produto> produtos = new ArrayList<Produto>();

        // select id, nome , valor, categoria from produto;
        // percorrer cursor e add na lista de produtos
        Cursor cursor = bd.query("produto",
                new String[]{"id","nome","valor","categoria"},
                null,null,null,null,null);

        while(cursor.moveToNext()){
            Produto produto = new Produto();
            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            produto.setValor(cursor.getDouble(2));
            produto.setCategoria(cursor.getString(3));
            produtos.add(produto);
        }

        return produtos;
    }

    // delete from produto where id = ?
    public void excluir(Produto p){
        bd.delete("produto","id = ?" , new String[]{p.getId().toString()});
    }


    // update produto set nome = ?, categoria =? , valor = ? where id = ?
    public void atualizar(Produto produto) {
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("categoria", produto.getCategoria());
        values.put("valor", produto.getValor());
        bd.update("produto",values, "id = ?", new String[]{produto.getId().toString()});
    }
}
