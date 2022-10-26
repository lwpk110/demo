SqlNode MdxCalculate() :
{
    SqlParserPos parsePos;
    SqlNode cubeName;
    SqlNode scopeNode;
}
{
    <USE>
    {
        parsePos = getPos();
    }
    cubeName = CompoundIdentifier()
    <SEMICOLON>
    scopeNode = ScopeNode()
   {
       return new Stmt(parsePos, cubeName, scopeNode);
   }
}

SqlNode ScopeNode() :
{
    SqlParserPos scopePos;
    SqlNodeList scopes;
    SqlNodeList calExprs;
}
{
   <SCOPE>
   {
       scopePos = getPos();
       SqlNode scope = null;
       scopes = new SqlNodeList(scopePos);
   }
   <LPAREN>
   scope = ScopeValue()
    {
       scopes.add(scope);
    }
    (
       <COMMA>
       scope = ScopeValue()
       {scopes.add(scope);}
    )*
   <RPAREN>
   <SEMICOLON>
   {
     calExprs = new SqlNodeList(getPos());
     SqlNode calExpr;
   }
   (
   calExpr = SingleCalExpr()
   {calExprs.add(calExpr);}
   )+
   <END> <SCOPE> <SEMICOLON>
   {
       return new ScopeNode(scopePos, scopes, calExprs);
   }
}


SqlNode SingleCalExpr():
{
   SqlParserPos calPos;
   SqlNode left;
   SqlNode right;
}
{
   left = ScopeValue()
   {calPos = getPos();}
   <EQ>
   right = ExprRight()
   <SEMICOLON>
   {
       return new CalExpr(calPos, left, right);
   }
}

SqlNode ExprRight():
{
   SqlNode left;
   SqlOperator operand;
   SqlNode right;
}
{
   left = OperateNode()
   operand =  BinaryRowOperator()
   right = OperateNode()
   {
       return new CalExprRight(getPos(), left, operand, right);
   }
}

SqlNode OperateNode():
{
   SqlNode node;
}
{
   node = ScopeValue()
   {
   return node;
   }
   |
   node = NumericLiteral()
    {
       return node;
    }
}

SqlNode ScopeValue() :
{
    SqlNode dimension;
    SqlNode member;
}
{
    dimension = MdxVarLiteral()
    <DOT>
    member = MdxVarLiteral()
    {
        return new ScopeBucket(getPos(), dimension, member);
    }
}

JAVACODE String StringLiteralValue() {
    SqlNode sqlNode = StringLiteral();
    return ((NlsString) SqlLiteral.value(sqlNode)).getValue();
}

SqlNode MdxVarLiteral() :
{
    String txt;
}
{
     <BRACKET_QUOTED_IDENTIFIER>
    {
        txt =SqlParserUtil.trim(SqlParserUtil.trim(token.image, "["),"]");
        return SqlLiteral.createCharString(txt, getPos());
    }
}

