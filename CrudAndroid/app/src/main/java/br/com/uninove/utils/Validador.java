package br.com.uninove.utils;

import android.widget.EditText;

import br.com.uninove.model.Produto;

public class Validador {

    public static String validar(EditText nome, EditText categoria, EditText valor){
        if(     categoria.getText().toString().trim().isEmpty() || categoria == null ||
                nome.getText().toString().trim().isEmpty() || nome == null ||
                valor.getText().toString().trim().isEmpty() || valor == null ) {
            return "Por favor preencha todos os campos";
        }
        // caso o numero informado seja invalido sera retornado um msg
        try{
            Double.parseDouble(valor.getText().toString());
        }catch(NumberFormatException ex){
            return "Campo valor deve ser numérico";
        }

        return null;
    }

}
