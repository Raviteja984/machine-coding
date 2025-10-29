package service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import model.Split;
import service.SplitStrategy;

public class EqualSplitStrategy implements SplitStrategy{

    @Override
    public void computeSplit(BigDecimal expenseAmount, List<Split> splits) {
        BigDecimal splitAmount = expenseAmount.divide(new BigDecimal(splits.size()), 2, RoundingMode.HALF_UP);

        for(Split split : splits) {
            split.setAmount(splitAmount);
        }
    }

}
