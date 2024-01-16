grammar CSV;

/* parser */
file: hdr row+;
hdr: row;
row: field(',' field)* '\r'? '\n';
field   // 允许两个逗号之间出现任意的文本、字符串，甚至什么都没有
    : TEXT
    | STRING
    |
    ;

/* lexer */
TEXT: ~[,\n\r"]+;
STRING: '"' ('""'|~'"')* '"'; // 两个双引号是对双引号的转义