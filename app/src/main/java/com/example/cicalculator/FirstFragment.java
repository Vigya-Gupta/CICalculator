package com.example.cicalculator;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

public class FirstFragment extends Fragment {
    AutoCompleteTextView dropdownText;
    TextInputLayout outlinedYear;
    double interest;
     double num;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
    private  EditText principal,rate,time;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final int n;
        principal=(EditText)view.findViewById(R.id.principal);
        rate=(EditText)view.findViewById(R.id.rate);
        time=(EditText)view.findViewById(R.id.time);
        final RadioGroup rgrp = view.findViewById(R.id.rgrp);
        final TextView textResult=view.findViewById(R.id.textResult);
        Button btn_calculate=view.findViewById(R.id.btn_calculate);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double P=Double.parseDouble(principal.getText().toString());
                double r=Double.parseDouble(rate.getText().toString());
                int T=Integer.parseInt(time.getText().toString());
                DecimalFormat d=new DecimalFormat("#0.00");
                final int checkedRadioButtonId = rgrp.getCheckedRadioButtonId();
                if(checkedRadioButtonId==R.id.monthly)
                {
                    num=(1+(r/(12*100)));
                    interest=P*(Math.pow(num,(12*T)));
                }
                else if(checkedRadioButtonId==R.id.halfYearly)
                {
                    num=(1+(r/(2*100)));
                    interest=P*(Math.pow(num,(2*T)));
                }
                else{
                    num=(1+(r/(1*100)));
                    interest=P*(Math.pow(num,T));
                }

                double res=interest-P;
                textResult.setText("Compound Interest : "+ d.format(res));
            }
        });
    }
}
