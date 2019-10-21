package br.com.uninove;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.uninove.banco.ProdutoDAO;
import br.com.uninove.model.Produto;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome, categoria,valor;
    private ProdutoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.editNome);
        categoria = findViewById(R.id.editCategoria);
        valor = findViewById(R.id.editValor);

        dao = new ProdutoDAO(this);
    }


    public void salvar(View view){
        Produto produto = new Produto();
        produto.setCategoria(categoria.getText().toString());
        produto.setNome(nome.getText().toString());
        produto.setValor(Double.parseDouble(valor.getText().toString()));

        // salvando produto no banco
        long id = dao.inserir(produto);
        Toast.makeText(this,"Produto inserido com id: "+ id, Toast.LENGTH_SHORT).show();
    }



}
