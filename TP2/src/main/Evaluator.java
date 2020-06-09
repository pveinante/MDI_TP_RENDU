package main;

import tp6.analysis.Analysis;
import tp6.node.*;

import java.util.*;


public class Evaluator extends AbstractInterpreter implements Analysis
{
	private boolean getVariableByName = false;

	private Stack<Integer> instructionsStack = new Stack<>();

	private int leftExprCalc, rightExprCalc;
	private HashMap<Integer, Integer> variableList = new HashMap<>();


	private int getIdOfVariable(String varName)
	{
		int asciiVar = 0;

		for (char c : varName.toCharArray())
		{
			asciiVar += (int) c;
		}

		return asciiVar;
	}

	private int getOperatorPriority(String operator)
	{
		switch (operator)
		{
			case "*":
			case "/":
				return 0;

			case "+":
			case "-":
			case ">":
			case "<":
				return 1;

			default:
				return -1;
		}
	}

	@Override
	public void caseAAssignment(AAssignment node)
	{
		// Calculating expression
		node.getRhs().apply(this);
		int resultExpr = this.instructionsStack.pop();

		// Get variable
		this.getVariableByName = true;

		node.getVar().apply(this);
		int varId = this.instructionsStack.pop();

		this.getVariableByName = false;

		// Assign variable
		this.variableList.replace(varId, resultExpr);
	}

	@Override
	public void caseABinexpression(ABinexpression node)
	{
		//int operatorPriority = this.getOperatorPriority(node.getOperator().toString().trim());
		// TODO operator priority

		// Put in stack right expression
		node.getRExp().apply(this);

		// Put in stack left expression
		node.getLExp().apply(this);

		// Calculating
		node.getOperator().apply(this);
	}

	@Override
	public void caseABlock(ABlock node)
	{
		LinkedList<PStatement> statements = node.getStatement();

		for (PStatement statement : statements)
		{
			statement.apply(this);
		}
	}

	@Override
	public void caseAConditional(AConditional node)
	{
		node.getCond().apply(this);
		int cond = this.instructionsStack.pop();

		if (cond != 0)
		{
			node.getThenpart().apply(this);
		}
		else
		{
			node.getElsepart().apply(this);
		}
	}

	@Override
	public void caseAEqualOperatorlogique(AEqualOperatorlogique node)
	{
		int value = 0;

		if (this.leftExprCalc == this.rightExprCalc)
		{
			value = 1;
		}

		this.instructionsStack.push(value);
	}

	@Override
	public void caseAInfOperatorlogique(AInfOperatorlogique node)
	{
		int leftExpr = this.instructionsStack.pop();
		int rightExpr = this.instructionsStack.pop();
		int result = 0;

		if (leftExpr < rightExpr)
		{
			result = 1;
		}

		this.instructionsStack.push(result);
	}

	@Override
	public void caseAIntegervalue(AIntegervalue node)
	{
		int value = Integer.parseInt(node.getNumber().toString().trim());
		this.instructionsStack.push(value);
	}

	@Override
	public void caseAMinusOperatorarith(AMinusOperatorarith node)
	{
		int leftExpr = this.instructionsStack.pop();
		int rightExpr = this.instructionsStack.pop();

		int value = leftExpr - rightExpr;

		this.instructionsStack.push(value);
	}

	@Override
	public void caseAMultiplierOperatorarith(AMultiplierOperatorarith node)
	{
		int leftExpr = this.instructionsStack.pop();
		int rightExpr = this.instructionsStack.pop();

		int value = leftExpr * rightExpr;

		this.instructionsStack.push(value);
	}

	@Override
	public void caseAPlusOperatorarith(APlusOperatorarith node)
	{
		int leftExpr = this.instructionsStack.pop();
		int rightExpr = this.instructionsStack.pop();

		int value = leftExpr + rightExpr;

		this.instructionsStack.push(value);
	}

	@Override
	public void caseAPrintS(APrintS node)
	{
		// put value in stack
		node.getValue().apply(this);

		int value = this.instructionsStack.pop();
		System.out.println(value);
	}

	@Override
	public void caseAReadS(AReadS node)
	{
		Scanner sc = new Scanner(System.in);

		// Read value
		int readValue;
		System.out.print("Enter an integer value for " + node.getVar() + " : ");
		readValue = sc.nextInt();

		// Get variable
		this.getVariableByName = true;
		node.getVar().apply(this);
		int varId = this.instructionsStack.pop();
		this.getVariableByName = false;

		// Insert new value in variable
		this.variableList.replace(varId, readValue);
	}

	@Override
	public void caseADiviserOperatorarith(ADiviserOperatorarith node)
	{
		int leftExpr = this.instructionsStack.pop();
		int rightExpr = this.instructionsStack.pop();

		int res = Math.round((float) leftExpr / (float) rightExpr);

		this.instructionsStack.push(res);
	}

	@Override
	public void caseAModuloOperatorarith(AModuloOperatorarith node)
	{
		int leftExpr = this.instructionsStack.pop();
		int rightExpr = this.instructionsStack.pop();

		int res = leftExpr % rightExpr;

		this.instructionsStack.push(res);
	}

	@Override
	public void caseASupOperatorlogique(ASupOperatorlogique node)
	{
		int leftExpr = this.instructionsStack.pop();
		int rightExpr = this.instructionsStack.pop();
		int result = 0;


		if (leftExpr > rightExpr)
		{
			result = 1;
		}

		this.instructionsStack.push(result);
	}

	@Override
	public void caseAVariableref(AVariableref node)
	{
		String varName = node.getIdentifier().toString();
		int varId = this.getIdOfVariable(varName);

		if (!this.variableList.containsKey(varId))
		{
			variableList.put(varId, -1);
		}

		// Push variable
		if (this.getVariableByName)
		{
			this.instructionsStack.push(varId);
		}
		else
		{
			this.instructionsStack.push(this.variableList.get(varId));
		}
	}

	@Override
	public void caseAWhileS(AWhileS node)
	{
		int counter = 0;
		node.getCond().apply(this);
		int cond = this.instructionsStack.pop();

		while (cond != 0)
		{
			node.getBody().apply(this);


			// Update cond
			node.getCond().apply(this);
			cond = this.instructionsStack.pop();

			counter++;

			if (counter >= 5)
				break;
		}

		System.out.println("End while (nbLoop : " + counter + ")");
	}
}
