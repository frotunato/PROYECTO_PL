import org.antlr.v4.misc.OrderedHashMap;

import java.util.Map;

public class AuxVariables {
    private final Map<String, String> memoria = new OrderedHashMap<>();
    //Set<Boolean> memoria = new HashSet<Object>();

    public Map<String, String> getVariablesDeclaradas () {
        return memoria;
    }

    public void declara (String nombreVariable, String tipoVariable) {
        if (memoria.containsKey(nombreVariable))
            throw new IllegalStateException("La variable " + nombreVariable + " ya ha sido declarada con anterioridad");
        switch (tipoVariable) {
            case "LOG" -> memoria.put(nombreVariable, "Boolean");
            case "NUM" -> memoria.put(nombreVariable, "Integer");
            case "SEQ(NUM)" -> memoria.put(nombreVariable, "ArrayList<Integer>");
            case "SEQ(LOG)" -> memoria.put(nombreVariable, "ArrayList<Boolean>");
            default -> throw new IllegalStateException("Unexpected value: " + tipoVariable);
        }
        System.out.println("[" + this.getClass() + "], mem <- " +
                nombreVariable + ", tipo " + tipoVariable);
    }

    public Boolean estaDeclarada (String nombreVariable) {
        //Boolean estado = this.memoria.containsKey(nombreVariable);
        //System.out.println("[" + this.getClass() + "] " + nombreVariable + " declarada? " + estado);
        return memoria.containsKey(nombreVariable);
    }
}
