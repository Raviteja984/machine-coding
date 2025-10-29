package service;

import java.math.BigDecimal;
import java.util.List;

import model.Split;

public interface SplitStrategy {

    void computeSplit(BigDecimal expenseAmount , List<Split> splits);
}
