SqlNode SqlJacky() :
{
    SqlNode stringNode;
}
{
    <JACKY> <JOB>
    stringNode = StringLiteral()
    {
        return new SqlJacky(getPos(), token.image);
    }
}