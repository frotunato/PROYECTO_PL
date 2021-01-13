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
        this.tipo = "Integer";
        this.valorNumerico = valorNumerico;
    }

    public Valor (Boolean valorBooleano) {
        this.valor = valorBooleano;
        this.tipo = "Boolean";
        this.valorBooleano = valorBooleano;
    }

    public Valor(List<Integer> secuencia, Integer dummy) {
        this.valor = secuenciaNumerica;
        secuenciaNumerica = new ArrayList<>(secuencia);
        //ecuenciaNumerica.addAll(secuencia);
    }
    public Valor(List<Boolean> secuencia, Boolean dummy) {
        this.valor = secuenciaNumerica;
        secuenciaBooleana = new ArrayList<>(secuencia);
    }
    public Valor (List<Object> secuenciaGenerica) {
        this.valor = secuenciaGenerica;
        this.secuenciaGenerica.addAll(secuenciaGenerica);
    }

    public boolean getEsNulo () {
        return this.valor == null;
    }


    public Valor getUltimoValorSecuencia () {
        if (secuenciaNumerica != null)
            return new Valor(secuenciaNumerica.get(secuenciaNumerica.size() - 1));
        else if (secuenciaBooleana != null)
            return new Valor(secuenciaBooleana.get(secuenciaBooleana.size() - 1));
        else
            return null;
        //return new Valor(this.secuenciaGenerica.get(this.secuenciaGenerica.size() - 1));
    }

    public List<Valor> getValores () { return this.valores; }

    public void setValores (List<Valor> nValores) {
        this.valores = nValores;
    }

    public Object getValor () {
        return this.valor;
    }

    public String getTipo () { return this.tipo;}

    public Integer getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico (Integer valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public void setValorBooleano(Boolean valorBooleano) {
        this.valorBooleano = valorBooleano;
    }

    public Boolean getValorBooleano () { return this.valorBooleano; }

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
        if (!o.getTipo().equals(this.tipo)) return false;
        return this.valor.equals(o.valor);
    }

    public String toString () {
        if (valorBooleano != null)
            return "(bol " + getValorBooleano() + ")";
        else if (valorNumerico != null)
            return "(int " + getValorNumerico() + ")";
        else if (secuenciaNumerica != null)
            return "(seq(int) " + getSecuenciaNumerica().toString() + ")";
        else if (secuenciaBooleana != null)
            return "(seq(bool) " + getSecuenciaBooleana().toString() + ")";
        else
            return "(indef " + this.valor + ")";

    }

    public List<Boolean> getSecuenciaBooleana() {
        return this.secuenciaBooleana;
    }

    public List<Integer> getSecuenciaNumerica() {
        return this.secuenciaNumerica;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorNumerico, valorBooleano, secuenciaBooleana, secuenciaNumerica);
    }
}
