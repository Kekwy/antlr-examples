package com.kekwy.example.antlr.cal;

import com.kekwy.example.antlr.cal.grammar.ExprLexer;
import com.kekwy.example.antlr.cal.grammar.ExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.Scanner;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2024/1/14 10:39
 */
public class Main {

    public static void main(String[] args) throws IOException {
        EvalVisitor visitor = new EvalVisitor();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            CharStream input = CharStreams.fromString(scanner.nextLine());
            ExprLexer lexer = new ExprLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokens);
            ParseTree tree = parser.prog(); // 从 prog 规则开始进行语法分析

            visitor.visit(tree);
        }
    }

}
