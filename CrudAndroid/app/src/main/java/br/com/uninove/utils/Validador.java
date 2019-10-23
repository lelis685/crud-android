package br.com.uninove.utils;

import android.widget.EditText;

import br.com.uninove.model.Produto;

public class Validador {


    public static String validar(EditText nome, EditText categoria, EditText valor){
        if(     categoria.getText().toString().trim().isEmpty() ||
                nome.getText().toString().trim().isEmpty() ||
                valor.getText().toString().trim().isEmpty()) {
            return "Por favor preencha todos os campos";
        }
        try{
            Double.parseDouble(valor.getText().toString());
        }catch(NumberFormatException ex){
            return "Campo valor deve ser numérico";
        }

        return null;
    }

}
