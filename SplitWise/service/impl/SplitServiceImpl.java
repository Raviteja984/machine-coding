package service.impl;

import java.math.BigDecimal;
import java.util.List;

import model.Split;
import service.SplitService;
import service.SplitStrategy;

public class SplitServiceImpl implements SplitService{

    SplitStrategy splitStrategy;

    public SplitServiceImpl(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy;
    }

    @Override
    public void split(BigDecimal expenseAmount, List<Split> splits) {
        splitStrategy.computeSplit(expenseAmount, splits);
    }

}
