package com.example.farmersurveyapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.riddhimanadib.formmaster.FormBuilder;
import me.riddhimanadib.formmaster.listener.OnFormElementValueChangedListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormElementPickerSingle;
import me.riddhimanadib.formmaster.model.FormElementSwitch;
import me.riddhimanadib.formmaster.model.FormElementTextNumber;
import me.riddhimanadib.formmaster.model.FormElementTextSingleLine;
import me.riddhimanadib.formmaster.model.FormHeader;

public class MainActivity extends AppCompatActivity {
    /*
    TAGS
    123- Plot Type (mcounter)
    124- Land Preparations (mcounter1)
     */

    RecyclerView mRecyclerView ;
    FormBuilder mFormBuilder;
    List<BaseFormElement> formItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// initialize variables
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mFormBuilder = new FormBuilder(this, mRecyclerView, new OnFormElementValueChangedListener() {
            @Override
            public void onValueChanged(BaseFormElement baseFormElement) {
                //Plot Status is changed
                if(baseFormElement.getTag()==123 && baseFormElement.getValue()=="Before Planting"){
                    BaseFormElement yesNo = FormElementSwitch.createInstance().setTitle("Land Preparation").setTag(124).setSwitchTexts("Yes", "No");
                    formItems.add(yesNo);
                    mFormBuilder.addFormElements(formItems);
                }
            }
        }
        );

// General Information Group
        BaseFormElement header = FormHeader.createInstance("General Information");
        BaseFormElement farmerID = FormElementTextNumber.createInstance().setTitle("Farmer ID").setHint("01-80").setRequired(true).setType(FormElementTextSingleLine.TYPE_EDITTEXT_NUMBER);
        BaseFormElement farmerName = FormElementTextSingleLine.createInstance().setTitle("Farmer Name").setHint("Enter Farmer's Name");

        List<String> crops = new ArrayList<>();
        crops.add("Wheat");
        crops.add("Rice");
        List<String> plotType = new ArrayList<>();
        plotType.add("Before Planting");
        plotType.add("After Planting");

        BaseFormElement cropType = FormElementPickerSingle.createInstance().setTitle("Crop Type").setHint("Tap to select a crop").setOptions(crops).setPickerTitle("Pick a crop");
        BaseFormElement plotStatus = FormElementPickerSingle.createInstance().setTag(123).setTitle("What is the current status of the plot?").setHint("Plot Status").setOptions(plotType).setPickerTitle("Plot Status");
        FormElementSwitch yesNo = FormElementSwitch.createInstance().setTitle("Plot Status").setSwitchTexts("Before Planting", "After Planting");

// add them in a list
        formItems = new ArrayList<BaseFormElement>();
        formItems.add(header);
        formItems.add(farmerID);
        formItems.add(farmerName);
        formItems.add(cropType);
        formItems.add(plotStatus);

// build and display the General Information form
        mFormBuilder.addFormElements(formItems);
        mFormBuilder.isValidForm(); // returns boolean whether the form is valid or not
    }

}