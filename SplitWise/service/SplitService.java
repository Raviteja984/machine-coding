package service;

import java.math.BigDecimal;
import java.util.List;

import model.Split;

public interface SplitService {

    void split(BigDecimal expenseAmount, List<Split> splits);
}
