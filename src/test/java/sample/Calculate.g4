grammar Calculate;

prog : stat+ EOF;

stat: expr                      # printExpr
    | ID '=' expr               # assign
    ;

expr: expr op=(MUL|DIV) expr    # MulDiv
| expr op=(ADD|SUB) expr        # AddSub
| NUMBER                        # NUMBER
| ID                            # id
| LPAREN expr RPAREN            # parens
;

MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;

LPAREN: '(';
RPAREN: ')';

ID : [a-zA-Z]+ ;
NUMBER : [0-9]+ ('.' [0-9]+)? ;

WS : [ \t]+ -> skip;