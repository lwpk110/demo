SqlNode SqlCreateFunction() :
{
SqlParserPos createPos;
SqlParserPos propertyPos;
SqlNode functionName = null;
String className = null;
String methodName = null;
String comment = null;
SqlNodeList properties = null;
}
{
    <CREATE>
    {
    createPos = getPos();
    }
    <FUNCTION>
        functionName = CompoundIdentifier()
    <AS>
    { className = StringLiteralValue(); }
    [
        <METHOD>
        {
            methodName = StringLiteralValue();
        }
    ]
    [
        <PROPERTY>
        {
            propertyPos = getPos();
            SqlNode property;
            properties = new SqlNodeList(propertyPos);
        }
        <LPAREN>
        [
            property = PropertyValue()
            {
                properties.add(property);
            }
            (
                <COMMA>
                {
                    property = PropertyValue();
                    properties.add(property);
                }
            )*
        ]
        <RPAREN>
    ]
    [
        <COMMENT> {
            comment = StringLiteralValue();
        }
    ]

    {
    return new SqlCreateFunction(createPos, functionName, className, methodName, comment, properties);
    }
}

JAVACODE String StringLiteralValue() {
    SqlNode sqlNode = StringLiteral();
    return ((NlsString) SqlLiteral.value(sqlNode)).getValue();
}


SqlNode PropertyValue() :
{
    SqlNode key;
    SqlNode value;
    SqlParserPos pos;
}
{
    key = StringLiteral()
    { pos = getPos(); }
    <EQ> value = StringLiteral()
    {
    return new SqlProperty(getPos(), key, value);
    }
}