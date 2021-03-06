package edu.bsu.cs222.finalproject.database;

import java.sql.Date;

//represents one row in the "Repair" database table
public class Repair {
    public Repair(){
        id = -1;
        repairNumber = -1;
        userId = -1;
        itemId = -1;
        dateStarted = null;
        dateCompleted = null;
        description = "";
        descriptionCompleted = "";
    }

    public Repair(Repair c){
        id = c.id;
        repairNumber = c.repairNumber;
        userId = c.userId;
        itemId = c.itemId;
        dateStarted = c.dateStarted;
        dateCompleted = c.dateCompleted;
        description = c.description;
        descriptionCompleted = c.descriptionCompleted;
    }

    public long id;
    public long repairNumber;
    public long userId;
    public long itemId;
    public Date dateStarted;
    public Date dateCompleted;
    public String description;
    public String descriptionCompleted;
}
