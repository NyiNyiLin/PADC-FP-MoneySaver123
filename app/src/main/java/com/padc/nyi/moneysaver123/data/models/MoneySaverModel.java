package com.padc.nyi.moneysaver123.data.models;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.data.vos.BillVO;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;
import com.padc.nyi.moneysaver123.util.DateUtil;

import java.util.Calendar;

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

    public int saveReminderForBill(BillVO billVO){
        return billVO.saveReminderForBill(billVO);
    }

    public void transferBillToExpense(BillVO billVO){
        ExpenseVO expenseVO = new ExpenseVO();
        expenseVO.setTitle(billVO.getTitle());
        expenseVO.setCategory_id(9);
        expenseVO.setAmount(billVO.getAmount());

        //To set Date
        Calendar now = Calendar.getInstance();
        expenseVO.setDate(DateUtil.channgeTimeToMilliTime(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)));

        BillVO.deleteBill(billVO.getBillID());
        ExpenseVO.saveExpense(expenseVO);
    }
}
