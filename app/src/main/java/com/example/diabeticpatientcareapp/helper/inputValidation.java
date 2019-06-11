package com.example.diabeticpatientcareapp.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class inputValidation {
    private Context context;
     public inputValidation (Context context) {
         this.context = context;
     }

    public boolean isEditTextFilled(EditText edittext, TextInputLayout textInputLayout, String message) {
         String value = edittext.getText().toString().trim();
         if(value.isEmpty()){
          textInputLayout.setError(message);
          hideKeyboardFrom(edittext);
          return false;

         }else{
             textInputLayout.setErrorEnabled(false);
         }
         return true;
    }

    private void hideKeyboardFrom(View view){
         InputMethodManager inm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
         inm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
