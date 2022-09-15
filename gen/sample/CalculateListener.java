// Generated from C:/Users/luwei/Desktop/study/antlr4/demo/src/test/java/sample\Calculate.g4 by ANTLR 4.10.1
package sample;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculateParser}.
 */
public interface CalculateListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculateParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CalculateParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculateParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CalculateParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link CalculateParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(CalculateParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link CalculateParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(CalculateParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CalculateParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(CalculateParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CalculateParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(CalculateParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NUMBER}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNUMBER(CalculateParser.NUMBERContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NUMBER}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNUMBER(CalculateParser.NUMBERContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(CalculateParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(CalculateParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(CalculateParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(CalculateParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(CalculateParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(CalculateParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CalculateParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CalculateParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CalculateParser.IdContext ctx);
}