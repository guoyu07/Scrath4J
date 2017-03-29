package cn.timd.Scratch4J.Strategy;

import cn.timd.Scratch4J.Facade.IFacade;
import cn.timd.Scratch4J.Facade.impl.DefaultFacadeImpl;

public abstract class AbstractStrategy {
    private IFacade facade = new DefaultFacadeImpl();
    private int maxFailureCount = 3;
    // 0 means infinite
    private int maxDepth = 10;

    public IFacade getFacade() {
        return facade;
    }

    public void setFacade(IFacade facade) {
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
