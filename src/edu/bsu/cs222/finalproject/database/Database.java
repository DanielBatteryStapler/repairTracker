package edu.bsu.cs222.finalproject.database;

import java.util.ArrayList;

public interface Database{
    boolean isUsable();

    void connectToServer(String address, String username, String password, String database);
    void close();

    void insertEmployee(Employee newEmployee);
    void dropEmployee(long employeeId);
    Employee getEmployeeWithId(long employeeId);
    Employee getEmployeeWithNumber(String employeeNumber);
    ArrayList<Employee> getAllEmployees();

    void insertUser(User newUser);//sets the "id" of the user to match the internal database id
    void updateUser(User user);
    void dropUser(long userId);
    User getUserWithId(long userId);
    User getUserWithPhoneNumber(String phoneNumber);
    ArrayList<User> searchUsersWithName(String name);

    void insertItem(Item newItem);
    void updateItem(Item item);
    void dropItem(long itemId);
    Item getItemWithId(long itemId);
    ArrayList<Item> searchItemsWithSerial(String serialNumber);

    void insertPurchase(Purchase newPurchase);
    void dropPurchase(long purchaseId);
    Purchase getPurchaseWithId(long purchaseId);
    ArrayList<Purchase> getPurchasesWithPurchaser(long purchaserId);
    Purchase getPurchaseOnItem(long itemId);

    void insertRepair(Repair newRepair);
    void updateRepair(Repair repair);
    void dropRepair(long repairId);
    Repair getRepairWithId(long repairId);
    ArrayList<Repair> getRepairsOnItem(long itemId);
    ArrayList<Repair> getRepairsWithUser(long userId);
    Repair getLatestRepair();
    ArrayList<Repair> getInProgressRepairs();

    void insertRepairPart(RepairPart newRepairPart);
    void updateRepairPart(RepairPart repairPart);
    void dropRepairPart(long repairPartId);
    ArrayList<RepairPart> getRepairPartsOnRepair(long repairId);
    ArrayList<RepairPart> getRepairPartsInQueue();
}