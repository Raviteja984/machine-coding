package service.impl;

import java.math.BigDecimal;
import java.util.List;

import model.PercentSplit;
import model.Split;
import service.SplitStrategy;

public class PercentSplitStrategy implements SplitStrategy{

    @Override
    public void computeSplit(BigDecimal expenseAmount, List<Split> splits) {
        for(Split split : splits) {
            if(split instanceof PercentSplit ps){
                split.setAmount(expenseAmount.multiply(ps.getPercentage()).divide(new BigDecimal(100)));
            }
        }
    }

}
