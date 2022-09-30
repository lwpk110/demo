grammar Arithmetic;

@header{
package cn.deep.antlr4.parser;
}

prog
    : expr EOF
    ;

expr
    : expr op=(MUL|DIV|MOD) expr    # mulDiv
    | expr op=(ADD|SUB) expr        # addSub
    | str_expr                      # calEle
    | LPAREN expr RPAREN            # parens
    ;

MUL: '*' ;
DIV: '/' ;
ADD: '+' ;
SUB: '-' ;
MOD: '%';

LPAREN: '(';
RPAREN: ')';

str_expr: STR_TYPE | NUMBER | '-' NUMBER  ;

NUMBER: [0-9]+ ('.' [0-9]+)? ;
STR_TYPE: ('a' .. 'z' | 'A' .. 'Z'  | '0' .. '9' )+;

WS: [ \t]+ -> skip;
COMMENT: '#' '!' ~('\n'|'\r')* -> channel(HIDDEN);