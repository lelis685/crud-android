package br.com.uninove;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

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

        // relacionando obj java com xml
        listView = findViewById(R.id.listProdutos);
        dao = new ProdutoDAO(this);
        produtos = dao.listarTodos();
        produtosFiltrados.addAll(produtos);

        // criando adaptador pra listagem
        ArrayAdapter<Produto> adaptador = new ArrayAdapter<Produto>(this, android.R.layout.simple_expandable_list_item_1, produtosFiltrados);
        listView.setAdapter(adaptador);

        // abri o contexto de menu com opcao (editar,excluir)
        registerForContextMenu(listView);

    }

    // exibir menu na tela de listagem
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_pesquisar, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        // verifica se houve algo digitado
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                pesquisarProduto(s);
                return false;
            }
        });

        return true;
    }


    // metodo resposavel pela pesquisa por nome
    public void pesquisarProduto(String nome) {
        produtosFiltrados.clear();
        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                produtosFiltrados.add(p);
            }
        }
        // para mostrar dados atualizados
        listView.invalidateViews();
    }


    // refresh na lista
    @Override
    public void onResume() {
        super.onResume();
        produtos = dao.listarTodos();
        produtosFiltrados.clear();
        produtosFiltrados.addAll(produtos);
        listView.invalidateViews();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_contexto, menu);
    }


    public void excluir(MenuItem item){
        // obtem posicao item lista
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Produto p = produtosFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o produto ?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        produtosFiltrados.remove(p);
                        produtos.remove(p);
                        dao.excluir(p);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }


    public void atualizar(MenuItem item){
        // obtem posicao item lista
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
         // criando produto de acordo com a posicao na lista 'produtosFiltrados'
        final Produto p = produtosFiltrados.get(menuInfo.position);

        // chamada para tela de cadastro
        Intent it = new Intent(this,CadastroActivity.class);
        it.putExtra("produto", p);
        startActivity(it);
    }

    // metodo usado para navegacao entre telas
    public  void irHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}