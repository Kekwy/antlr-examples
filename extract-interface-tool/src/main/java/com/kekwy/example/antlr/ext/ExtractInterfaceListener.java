package com.kekwy.example.antlr.ext;

import com.kekwy.example.antlr.ext.parser.JavaBaseListener;
import com.kekwy.example.antlr.ext.parser.JavaParser;
import org.antlr.v4.runtime.TokenStream;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2024/1/15 9:43
 */
public class ExtractInterfaceListener extends JavaBaseListener {

    private JavaParser parser;

    public ExtractInterfaceListener(JavaParser parser) {
        this.parser = parser;
    }

    /**
     * 监听对类定义的匹配
     */
    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("interface I" + ctx.Identifier() + "{");
    }

    @Override
    public void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        System.out.println("}");
    }

    /**
     * 监听对方法定义的匹配
     */
    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        // 需要从语法分析器中获取词法符号
        TokenStream tokens = parser.getTokenStream();
        String type = "void";
        if (ctx.type() != null) {
            type = tokens.getText(ctx.type());
        }
        String args = tokens.getText(ctx.formalParameters());
        System.out.println("\t" + type + " " + ctx.Identifier() + args + ";");
    }

    /**
     * 保留 import 语句
     */
    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
        System.out.println(parser.getTokenStream().getText(ctx));
    }
}
