package com.example.calculatrice100;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv, solutionTv;
    MaterialButton buttonc, buttonbrackopen, buttonbrackclose, buttondivide, buttonplus, buttonmoin, buttonmultiply, buttonequal;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonac, buttondot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assingID(buttonc, R.id.button_c);
        assingID(buttonbrackopen, R.id.button_open_bracket);
        assingID(buttonbrackclose, R.id.button_close_bracket);
        assingID(buttondivide, R.id.button_divide);
        assingID(buttonplus, R.id.button_plus);
        assingID(buttonmoin, R.id.button_minus);
        assingID(buttonmultiply, R.id.button_mult);
        assingID(buttonequal, R.id.button_equals);
        assingID(button0, R.id.button_0);
        assingID(button1, R.id.button_1);
        assingID(button2, R.id.button_2);
        assingID(button3, R.id.button_3);
        assingID(button4, R.id.button_4);
        assingID(button5, R.id.button_5);
        assingID(button6, R.id.button_6);
        assingID(button7, R.id.button_7);
        assingID(button8, R.id.button_8);
        assingID(button9, R.id.button_9);
        assingID(buttonac, R.id.button_AC);
        assingID(buttondot, R.id.button_point);
    }

    void assingID(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        solutionTv.setText(buttonText);
        String dataToCalculate = solutionTv.getText().toString();
        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(finalResult.equals("err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;

        }catch (Exception e){
            return "err";
        }
    }

}