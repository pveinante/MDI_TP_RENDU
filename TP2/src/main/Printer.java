package main;

import tp6.node.*;

import java.util.LinkedList;

public class Printer extends AbstractInterpreter
{
    private int imbricationLevel = 0;

    private void print(Object object)
    {
        for (int i = 0; i < imbricationLevel; i++)
        {
            System.out.print("\t");
        }

        System.out.print(object);

        System.out.println();
    }

    @Override
    public void caseAAssignment(AAssignment node)
    {
        print(node);
    }

    @Override
    public void caseABinexpression(ABinexpression node)
    {
        print(node);
    }

    @Override
    public void caseABlock(ABlock node)
    {
        print(node.getObrace());

        imbricationLevel++;

        LinkedList<PStatement> statements = node.getStatement();

        for (PStatement statement : statements)
        {
            statement.apply(this);
        }

        imbricationLevel--;

        print(node.getCbrace());
    }

    @Override
    public void caseAConditional(AConditional node)
    {
        print(node.getIf().toString() + node.getLPar().toString() + node.getCond().toString() + node.getRPar().toString());

        node.getThenpart().apply(this);

        if(node.getElsepart() != null)
        {
            node.getElsepart().apply(this);
        }
    }

    @Override
    public void caseAEqualOperatorlogique(AEqualOperatorlogique node)
    {
        print(node);
    }

    @Override
    public void caseAInfOperatorlogique(AInfOperatorlogique node)
    {
        print(node);
    }

    @Override
    public void caseAIntegervalue(AIntegervalue node)
    {
        print(node);
    }

    @Override
    public void caseAMinusOperatorarith(AMinusOperatorarith node)
    {
        print(node);
    }

    @Override
    public void caseAMultiplierOperatorarith(AMultiplierOperatorarith node)
    {
        print(node);
    }

    @Override
    public void caseAPlusOperatorarith(APlusOperatorarith node)
    {
        print(node);
    }

    @Override
    public void caseAPrintS(APrintS node)
    {
        print(node);
    }

    @Override
    public void caseAReadS(AReadS node)
    {
        print(node);
    }

    @Override
    public void caseADiviserOperatorarith(ADiviserOperatorarith node)
    {
        print(node);
    }

    @Override
    public void caseAModuloOperatorarith(AModuloOperatorarith node)
    {
        print(node);
    }

    @Override
    public void caseASupOperatorlogique(ASupOperatorlogique node)
    {
        print(node);
    }

    @Override
    public void caseAVariableref(AVariableref node)
    {
        print(node);
    }

    @Override
    public void caseAWhileS(AWhileS node)
    {
        print(node.getWhile().toString() + node.getLPar().toString() + node.getCond().toString() + node.getRPar().toString());
        node.getBody().apply(this);
    }
}
