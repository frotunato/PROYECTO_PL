package Valor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Valor {
    private String tipo;
    private Object valor;
    private Integer valorNumerico;
    private Boolean valorBooleano;
    private List<Boolean> secuenciaBooleana;
    private List<Integer> secuenciaNumerica;
    private List<Object> secuenciaGenerica = new ArrayList<>();
    private List<Valor> valores = new ArrayList<>();

    public Valor (Integer valorNumerico) {
        this.valor = valorNumerico;
        this.valorNumerico = valorNumerico;
    }

    public Valor (Boolean valorBooleano) {
        this.valor = valorBooleano;
        this.valorBooleano = valorBooleano;
    }

    public Valor (List<Object> secuenciaGenerica) {
        this.valor = secuenciaGenerica;
        this.secuenciaGenerica.addAll(secuenciaGenerica);
    }
/*
    public Valor (SecuenciaNumerica secuenciaNumerica) {
        this.valor = secuenciaNumerica;
        this.secuenciaNumerica = secuenciaNumerica;
    }

    public Valor (SecuenciaBooleana secuenciaBooleana) {
        this.valor = secuenciaBooleana;
        this.secuenciaBooleana = secuenciaBooleana;
    }
*/
    public List<Valor> getValores () { return this.valores; }

    public void setValores (List<Valor> nValores) {
        this.valores = nValores;
    }

    public Object getValor () {
        return this.valor;
    }

    public Integer getValorNumerico() {
        return valorNumerico;
    }

    public Boolean getValorBooleano() {
        return valorBooleano;
    }

    public List<Object> getSecuenciaGenerica () {
        return this.secuenciaGenerica;
    }
/*
    public SecuenciaBooleana getSecuenciaBooleana() {
        return secuenciaBooleana;
    }

    public SecuenciaNumerica getSecuenciaNumerica() {
        return secuenciaNumerica;
    }
*/
    public boolean equals(Valor o) {
        if (this == o) return true;
        if (!(o instanceof Valor)) return false;
        Valor valor = (Valor) o;
        return o.getValor().equals(o.valor);
    }

    public String toString () {
        if (valorBooleano != null)
            return "(bol " + getValorBooleano() + ")";
        else if (valorNumerico != null)
            return "(int " + getValorNumerico() + ")";
        else
            return "(seq " + getSecuenciaGenerica() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorNumerico, valorBooleano, secuenciaBooleana, secuenciaNumerica);
    }
}
