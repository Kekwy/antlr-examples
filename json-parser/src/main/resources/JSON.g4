 grammar JSON;

json
    :  object
    |  array
    ;

object
    : '{' pair (',' pair)* '}'
    | '{' '}' // 空对象
    ;

pair: STRING ':' value;

array
    : '[' value (',' value)* ']'
    | '[' ']' // 空数组
    ;

value
    : STRING
    | NUMBER
    | object  // 递归调用
    | array   // 递归调用
    | 'true'  // 关键字
    | 'false'
    | 'null'
    ;



/* lexer */
STRING: '"' (ESC | ~["\\])* '"';

fragment ESC: '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE: 'u' HEX HEX HEX;
fragment HEX: [0-9a-fA-F];

NUMBER
    : '-'? INT '.' EXP?     // 1.35, 1.35E-9, 0.3, -4.5
    | '-'? INT EXP          // 1e10, -3e4
    | '-'? INT              // -3, 45
    ;

fragment INT: '0' | [1-9] [0-9]*;   // 除 0 外的数字不允许以 0 开始
fragment EXP: [Ee] [+\-]? INT;      // \- 是对 - 的转义，因为 [...] 中的 - 用于表达 “范围” 语义

WS: [ \t\n\r]+ -> skip;
