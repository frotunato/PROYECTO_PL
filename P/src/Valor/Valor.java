package Valor;

import java.util.Objects;

public class Valor {
    String tipo;
    Object valor;
    Numero valorNumerico;
    Booleano valorBooleano;
    SecuenciaBooleana secuenciaBooleana;
    SecuenciaNumerica secuenciaNumerica;

    public Valor (Numero valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public Valor (Booleano valorBooleano) {
        this.valorBooleano = valorBooleano;
    }

    public Valor (SecuenciaNumerica secuenciaNumerica) {
        this.secuenciaNumerica = secuenciaNumerica;
    }

    public Valor (SecuenciaBooleana secuenciaBooleana) {
        this.secuenciaBooleana = secuenciaBooleana;
    }

    public Integer getValorNumerico() {
        return valorNumerico.getValor();
    }

    public Boolean getValorBooleano() {
        return valorBooleano.getValor();
    }

    public SecuenciaBooleana getSecuenciaBooleana() {
        return secuenciaBooleana;
    }

    public SecuenciaNumerica getSecuenciaNumerica() {
        return secuenciaNumerica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Valor)) return false;
        Valor valor = (Valor) o;
        return Objects.equals(valorNumerico, valor.valorNumerico) && Objects.equals(valorBooleano, valor.valorBooleano) && Objects.equals(secuenciaBooleana, valor.secuenciaBooleana) && Objects.equals(secuenciaNumerica, valor.secuenciaNumerica);
    }

    public String toString () {
        if (valorBooleano != null)
            return "(bol " + getValorBooleano() + ")";
        else
            return "(int " + getValorNumerico() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorNumerico, valorBooleano, secuenciaBooleana, secuenciaNumerica);
    }
}
