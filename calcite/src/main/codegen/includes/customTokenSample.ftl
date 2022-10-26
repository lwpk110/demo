SqlNode ArrowStmt():
{
    String txt;
}
{
    LOOKAHEAD(2)
    <ARROW>
    {
        txt = SqlParserUtil.trim( SqlParserUtil.trim(token.image, "->"),"<-");
        return SqlLiteral.createCharString(txt,getPos());
    }
}

<DEFAULT> TOKEN :
{
< ARROW :
        "->"
            (~["\n","\r"])+
        "<-"
>
}