package com.example.automotivecalculator_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentActivity2 extends Fragment

{
    private TextView tvReport,tvMonthly,tvLoanterm;

    public static FragmentActivity2 newInstance(){
        return new FragmentActivity2();
    }



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment2,null);
        tvReport = view.findViewById(R.id.tv_report);
        tvMonthly = view.findViewById(R.id.tv_monthly);
        tvLoanterm = view.findViewById(R.id.tv_loanTerm);

        Bundle extra = this.getArguments();
        double price = 0;
        double downPayment = 0;
        String term ="";

        if(extra!=null){
            extra = getArguments();
            price = extra.getDouble("mPrice");
            downPayment = extra.getDouble("mDownpayment");
            term = extra.getString("mTerm");
        }

        Auto auto = new Auto();
        auto.setPrice(price);
        auto.setDownPayment(downPayment);
        auto.setLoanTerm(term);

        final double monthlyPayment = auto.monthlyPayment();
        double carStickerPayment = auto.getPrice();
        double downpay = auto.getDownPayment();
        double taxAmount = auto.taxAmount();
        double cost = auto.getTotalCost();
        double borrowedAmount= auto.borrowedAmount();
        double interestAmount = auto.interestAmount();

        final int loanTerm = auto.getLoanTerm();

        final String report = " Car Sticker Payment: $\t" + carStickerPayment + "\n You will put down $\t " + downpay +
                "\n Taxed Amount: $\t "+ taxAmount + "\n Your cost: $\t" + cost + "\n Borrowed Amount: $\t " + borrowedAmount +
                "\n Interest Amount: $\t "+ interestAmount;


        tvMonthly.setText("Monthly Payment: $"+monthlyPayment);
        tvReport.setText(report);
        tvLoanterm.setText("Loan Term is " + loanTerm + " years.");

        Button btnReturn =(Button)view.findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentActivity1();
                Bundle bundle = new Bundle();
                bundle.putDouble("monthly",monthlyPayment);
                bundle.putString("report", report);
                bundle.putInt("loan",loanTerm);
                fragment.setArguments(bundle);
                ((MainActivity)getActivity()).replaceFragment(fragment);
            }
        });

        return view;
    }

}
