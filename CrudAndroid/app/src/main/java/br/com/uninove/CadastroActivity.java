package br.com.uninove;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.uninove.banco.ProdutoDAO;
import br.com.uninove.model.Produto;
import br.com.uninove.utils.Validador;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome, categoria,valor;
    private ProdutoDAO dao;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.editNome);
        categoria = findViewById(R.id.editCategoria);
        valor = findViewById(R.id.editValor);

        dao = new ProdutoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("produto")) {
            produto = (Produto) it.getSerializableExtra("produto");
            nome.setText(produto.getNome());
            categoria.setText(produto.getCategoria());
            valor.setText(produto.getValor().toString());
        }
    }


    private Produto criarProduto(EditText nome,EditText categoria,EditText valor){
        if(Validador.validar(nome,categoria,valor) == null){
            produto.setCategoria(categoria.getText().toString());
            produto.setNome(nome.getText().toString());
            produto.setValor(Double.parseDouble(valor.getText().toString()));
       }else{
            Toast toast = Toast.makeText(this,Validador.validar(nome,categoria,valor), Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.show();
        }
        return produto;
    }


    public void salvarOuAtualizar(View view){
        if(produto == null){
            Produto produto = criarProduto(nome, categoria, valor);

            // salvando produto no banco
            long id = dao.inserir(produto);

            // limpando campos
            nome.setText("");
            categoria.setText("");
            valor.setText("");

            Toast.makeText(this,"Produto inserido com id: "+ id, Toast.LENGTH_SHORT).show();
        }else{
            Produto produto = criarProduto(nome, categoria, valor);
            dao.atualizar(produto);
            Toast.makeText(this,"Produto foi atualizado", Toast.LENGTH_SHORT).show();
        }

    }



}
