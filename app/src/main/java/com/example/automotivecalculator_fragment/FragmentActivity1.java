package com.example.automotivecalculator_fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentActivity1 extends Fragment {

    EditText price, downpayment;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnReport;
    Bundle bundle;
    double monthlyPay;
    int loanTerm;
    String report;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_fragment1,null);

        radioGroup = view.findViewById(R.id.radioGroup);
        btnReport = view.findViewById(R.id.btn_report);
        price = view.findViewById(R.id.et_price);
        downpayment = view.findViewById(R.id.et_donwpayment);

        bundle = this.getArguments();
        if(bundle!=null){
            Context mContext = getContext();
            //LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //View layout = layoutInflater.inflate(R.layout.dialog,null);
            monthlyPay = bundle.getDouble("monthly");
            loanTerm = bundle.getInt("loan");
            report = bundle.getString("report");
            alertPopup(mContext,report);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_2year : radioButton = view.findViewById(R.id.rb_2year); break;
                    case R.id.rb_3year : radioButton = view.findViewById(R.id.rb_3year); break;
                    case R.id.rb_4year : radioButton = view.findViewById(R.id.rb_4year); break;
                }
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentActivity2();
                if(price.getText().toString().length()==0 || downpayment.getText().toString().length()==0 || radioButton==null ){
                    Toast.makeText(getActivity(),"Enter required form",Toast.LENGTH_LONG).show();
                }else{
                    double mPrice = Double.parseDouble(price.getText().toString());
                    double mDownpayment = Double.parseDouble(downpayment.getText().toString());
                    String mTerm = radioButton.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putDouble("mPrice", mPrice);
                    bundle.putDouble("mDownpayment", mDownpayment);
                    bundle.putString("mTerm",mTerm);

                    fragment.setArguments(bundle);
                    ((MainActivity)getActivity()).replaceFragment(fragment);

                }
            }
        });

        return view;
    }

    private void alertPopup(Context context,String string){
        new AlertDialog.Builder(context).setTitle("Your Loan Information").setMessage(string).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
}
