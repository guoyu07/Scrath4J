package cn.timd.Scratch4J.Strategy;

import cn.timd.Scratch4J.ParserFacade.IParserFacade;
import cn.timd.Scratch4J.ParserFacade.impl.DefaultParserFacadeImpl;

public abstract class AbstractStrategy {
    private IParserFacade facade = new DefaultParserFacadeImpl();
    private int maxFailureCount = 3;
    // 0 means infinite
    private int maxDepth = 0;

    public IParserFacade getFacade() {
        return facade;
    }

    public void setFacade(IParserFacade facade) {
        this.facade = facade;
    }

    public int getMaxFailureCount() {
        return maxFailureCount;
    }

    public void setMaxFailureCount(int maxFailureCount) {
        this.maxFailureCount = maxFailureCount;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
}
