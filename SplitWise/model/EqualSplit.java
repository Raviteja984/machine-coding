package model;

import enums.SplitType;

public class EqualSplit extends Split{
    
    public EqualSplit(int id, User user) {
        super(id, user, SplitType.EQUAL);
    }
}
