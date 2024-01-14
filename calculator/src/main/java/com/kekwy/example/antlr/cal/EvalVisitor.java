package com.kekwy.example.antlr.cal;

import com.kekwy.example.antlr.cal.grammar.ExprBaseVisitor;
import com.kekwy.example.antlr.cal.grammar.ExprParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过访问器实现计算器的功能
 *
 * @author Kekwy
 * @version 1.0
 * @since 2024/1/14 17:47
 */
public class EvalVisitor extends ExprBaseVisitor<Integer> {

    /** 计算器的“内存”，存放变量名和变量值的对应关系 */
    Map<String, Integer> memory = new HashMap<>();

    /** ID '=' expr NEWLINE */
    @Override
    public Integer visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText(); // id 在 '=' 的左侧
        int value = visit(ctx.expr());  // 计算右侧表达式的值
        memory.put(id, value);          // 将这个映射关系存储在计算器的 “内存” 中
        return value;
    }

    /** expr NEWLINE */
    @Override
    public Integer visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());  // 计算 expr 子节点的值
        System.out.println(value);          // 打印结果
        return 0;                           // 上面已经直接打印出了结果，因此这里返回一个假值即可
    }

    /** INT */
    @Override
    public Integer visitInt(ExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    /** ID */
    @Override
    public Integer visitId(ExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0;
    }

    /** expr op=('*'|'/') expr */
    @Override
    public Integer visitMulDiv(ExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == ExprParser.MUL) return left * right;
        return left / right;
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Integer visitAddSub(ExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == ExprParser.ADD) return left + right;
        return left - right;
    }

    /** '(' expr ')' */
    @Override
    public Integer visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    /** clear */
    @Override
    public Integer visitClear(ExprParser.ClearContext ctx) {
        memory.clear();
        return 0;
    }
}
