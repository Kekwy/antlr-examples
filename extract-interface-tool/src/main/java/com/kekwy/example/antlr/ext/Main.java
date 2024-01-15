package com.kekwy.example.antlr.ext;

import com.kekwy.example.antlr.ext.parser.JavaLexer;
import com.kekwy.example.antlr.ext.parser.JavaParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2024/1/14 23:04
 */
public class Main {

    public static void main(String[] args) throws IOException {
        JavaLexer lexer = new JavaLexer(CharStreams.fromFileName(
                "extract-interface-tool/src/main/java/com/kekwy/example/antlr/ext/ExtractInterfaceListener.java"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();      // 开始语法分析的过程

        ParseTreeWalker walker = new ParseTreeWalker(); // 新建一个标准的遍历器
        ExtractInterfaceListener extractor = new ExtractInterfaceListener(parser);
        walker.walk(extractor, tree);                   // 使用监听器初始化对语法分析树的遍历
    }

}
