grammar Expr;
import CommonLexerRules;

/** 起始规则，语法分析的起点 **/
prog:   stat+;

stat:   expr (NEWLINE|EOF)         # printExpr
    |   ID '=' expr (NEWLINE|EOF)  # assign
    |   NEWLINE                    # blank
    |   'clear' (NEWLINE|EOF)      # clear
    ;

expr:   expr op=('*'|'/') expr  # mulDiv     // 若不写 op= 则在 ctx 的成员中没有 op 字段
    |   expr op=('+'|'-') expr  # addSub
    |   INT                     # int
    |   ID                      # id
    |   '(' expr ')'            # parens
    ;

MUL:    '*';
DIV:    '/';
ADD:    '+';
SUB:    '-';
