package com.example.caculator_application;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString()))
                {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd)
    {
        String oldStr = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPosition);
        String rightStr = oldStr.substring(cursorPosition);
        if(getString(R.string.display).equals(display.getText().toString()))
        {
            display.setText(strToAdd);
            display.setSelection(cursorPosition + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPosition + 1);
        }
    }


    public void zeroBTN(View view)
    {
        updateText("0");
    }

    public void oneBTN(View view)
    {
        updateText("1");
    }
    public void twoBTN(View view)
    {
        updateText("2");
    }
    public void threeBTN(View view)
    {
        updateText("3");
    }
    public void fourBTN(View view)
    {
        updateText("4");
    }
    public void fiveBTN(View view)
    {
        updateText("5");
    }
    public void sixBTN(View view)
    {
        updateText("6");
    }
    public void sevenBTN(View view)
    {
        updateText("7");
    }
    public void eightBTN(View view)
    {
        updateText("8");
    }
    public void nineBTN(View view)
    {
        updateText("9");
    }

    public void multiplyBTN(View view)
    {
        updateText("×");
    }
    public void divideBTN(View view)
    {
        updateText("÷");
    }

    public void subtractBTN(View view)
    {
        updateText("-");
    }
    public void addBTN(View view)
    {
        updateText("+");
    }
    public void clearBTN(View view)
    {
        display.setText("");
    }
    public void parBTN(View view)
    {
        int cursorPosition = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLength = display.getText().length();

        for(int i = 0; i < cursorPosition; i++){
            if(display.getText().toString().substring(i, i+1).equals("(")){
                openPar += 1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")){
                closedPar += 1;
            }
        }

        if(openPar == closedPar || display.getText().toString().substring(textLength - 1, textLength).equals(""))
        {
            updateText("(");
        } else if(closedPar < openPar && !display.getText().toString().substring(textLength - 1, textLength).equals(""))
        {
            updateText(")");
        }
        display.setSelection(cursorPosition + 1);

    }
    public void expBTN(View view)
    {
        updateText("^");
    }

    public void plusMinusBTN(View view)
    {
        updateText("-");
    }
    public void backspaceBTN(View view)
    {
        int cursorPositon = display.getSelectionStart();
        int textLength = display.getText().length();

        if(cursorPositon != 0 && textLength != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPositon - 1, cursorPositon, "");
            display.setText(selection);
            display.setSelection(cursorPositon - 1);
        }
    }
    public void equalBTN(View view)
    {
        String userExpression = display.getText().toString();
        userExpression = userExpression.replaceAll("÷", "/");
        userExpression = userExpression.replaceAll("×", "*");

        Expression expression = new Expression(userExpression);

        String result = String.valueOf((expression.calculate()));
        display.setText(result);
        display.setSelection(result.length());

    }

}