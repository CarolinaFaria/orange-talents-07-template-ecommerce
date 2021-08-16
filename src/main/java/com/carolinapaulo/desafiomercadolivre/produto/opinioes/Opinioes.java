package com.carolinapaulo.desafiomercadolivre.produto.opinioes;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {

    private Set<OpiniaoModel> opinioes;

    public Opinioes(Set<OpiniaoModel> opinioes) {

        this.opinioes = opinioes;
    }

    public <T> Set<T> mapOpinioes(Function<OpiniaoModel,T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());

    }

    public double mediaNotas() {
        Set<Integer> notas = mapOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble media = notas.stream().mapToInt(nota -> nota).average();
        return media.orElseGet(()->0.0);
    }

    public int total() {
        return this.opinioes.size();
    }

}
