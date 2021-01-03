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



        VisitorP s = new VisitorP();
        s.visit(tree);
        /*
        ParseTreeWalker walker = new ParseTreeWalker();
        Anasem anasem = new Anasem();
        walker.walk(anasem, tree);
        */
        JFrame frame = new JFrame("Árbol de Análisis");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                anasint.getRuleNames()),tree);
        viewer.setScale(0.8);//scale a little

        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setVisible(true);
    }
}