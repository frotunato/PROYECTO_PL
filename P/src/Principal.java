import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.util.Arrays;

public class Principal {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName(args[0]);
        Analex analex = new Analex(input);
        CommonTokenStream tokens = new CommonTokenStream(analex);
        Anasint anasint = new Anasint(tokens);
        ParseTree tree = anasint.bloque_programa();


        // Pase 1: análisis semántico
        VisitorP s = new VisitorP();
        s.visit(tree);
        // Pase 2: intérprete
        System.out.println("//////////// INTERPRETE ////////////");
        VisitorInterprete interprete = new VisitorInterprete();
        interprete.visit(tree);
/*
        ParseTreeWalker walker = new ParseTreeWalker();
        ListenerInterprete interpreteListener = new ListenerInterprete();
        walker.walk(interpreteListener, tree);
*/
        JFrame frame = new JFrame("Árbol de Análisis");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                anasint.getRuleNames()),tree);
        viewer.setScale(0.8);//scale a little



        panel.add(viewer);
        frame.add(panel);

        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Then, add the jScrollPane to your frame
        frame.getContentPane().add(jScrollPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1440,920);
        frame.setVisible(true);
/*

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // add something to you panel...
        // panel.add(...);

        // add the panel to a JScrollPane
        JScrollPane jScrollPane = new JScrollPane(panel);
        // only a configuration to the jScrollPane...
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Then, add the jScrollPane to your frame
        frame.getContentPane().add(jScrollPane);
        */
/*

        JFrame frameInterprete = new JFrame("Árbol de Análisis");
        JPanel panelInterprete = new JPanel();
        TreeViewer viewerInterprete = new TreeViewer(Arrays.asList(
                s.getRuleNames()),tree);
        viewer.setScale(0.8);//scale a little



        panel.add(viewer);
        frame.add(panel);

        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Then, add the jScrollPane to your frame
        frame.getContentPane().add(jScrollPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setVisible(true);
        */
    }
}