package service.impl;

import java.math.BigDecimal;
import java.util.List;

import model.ExactSplit;
import model.Split;
import service.SplitStrategy;

public class ExactSplitStrategy implements SplitStrategy{

    @Override
    public void computeSplit(BigDecimal expenseAmount, List<Split> splits) {
        for(Split split : splits) {
            if(split instanceof ExactSplit es){
                split.setAmount(es.getExactAmount());
            }
        }
    }
}
