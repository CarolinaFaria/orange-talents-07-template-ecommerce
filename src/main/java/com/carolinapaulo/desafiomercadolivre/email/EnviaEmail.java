package com.carolinapaulo.desafiomercadolivre.email;

public interface EnviaEmail {

    void send(String corpoEmail, String titulo, String nameFrom, String from, String to);

}
