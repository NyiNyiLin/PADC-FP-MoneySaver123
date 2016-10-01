package com.padc.nyi.moneysaver123.data.models;

import com.padc.nyi.moneysaver123.data.vos.BillVO;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;

/**
 * Created by ZMTH on 9/25/2016.
 */
public class MoneySaverModel extends BaseModel{

    private static MoneySaverModel objInstance;

   // private List<ExpenseVO> mExpenseList;

    public MoneySaverModel() {
        super();
        //mExpenseList = new ArrayList<>();

    }

    public static MoneySaverModel getInstance() {
        if (objInstance == null) {
            objInstance = new MoneySaverModel();
        }
        return objInstance;
    }

    public void saveExpense(ExpenseVO expenseVO){
        ExpenseVO.saveExpense(expenseVO);
    }
    public void updateExpense(ExpenseVO expenseVO){
        ExpenseVO.updateExpense(expenseVO);
    }

    public void saveIncome(IncomeVO incomeVO){
        IncomeVO.saveIncome(incomeVO);
    }

    public void saveReminderForBill(BillVO billVO){
        billVO.saveReminderForBill(billVO);
    }
}
