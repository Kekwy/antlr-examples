package com.kekwy.example.antlr.rows;

import com.kekwy.example.antlr.rows.parser.RowsLexer;
import com.kekwy.example.antlr.rows.parser.RowsParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2024/1/15 14:13
 */
public class Main {

    public static void main(String[] args) throws IOException {
        RowsLexer lexer = new RowsLexer(CharStreams.fromFileName("rows/src/main/resources/t.rows"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        int col = 2;
        RowsParser parser = new RowsParser(tokens, col); // 传递列号作为参数
        parser.setBuildParseTree(false); // 不需要建立语法分析树
        parser.file();  // 开始语法分析
    }

}
