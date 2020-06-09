/* This file was generated by SableCC (http://www.sablecc.org/). */

package tp6.node;

import tp6.analysis.*;

@SuppressWarnings("nls")
public final class TIf extends Token
{
    public TIf()
    {
        super.setText("if");
    }

    public TIf(int line, int pos)
    {
        super.setText("if");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TIf(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTIf(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TIf text.");
    }
}
