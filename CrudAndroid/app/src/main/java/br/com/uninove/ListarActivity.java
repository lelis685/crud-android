package br.com.uninove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.uninove.banco.ProdutoDAO;
import br.com.uninove.model.Produto;

public class ListarActivity extends AppCompatActivity {

    private ListView listView;
    private ProdutoDAO dao;
    private List<Produto> produtos;
    private List<Produto> produtosFiltrados = new ArrayList<Produto>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


        listView = findViewById(R.id.listProdutos);

        dao = new ProdutoDAO(this);
        produtos = dao.listarTodos();
        produtosFiltrados.addAll(produtos);

        ArrayAdapter<Produto> adaptador = new ArrayAdapter<Produto>(this,android.R.layout.simple_expandable_list_item_1, produtos);
        listView.setAdapter(adaptador);
    }

}
