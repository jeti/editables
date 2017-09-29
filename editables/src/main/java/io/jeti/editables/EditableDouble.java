package io.jeti.editables;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import java.io.Serializable;

public class EditableDouble extends EditableField {

    public EditableDouble(Context context, String text) {
        super(context, text, View.generateViewId());
    }

    public EditableDouble(Context context, String text, int editTextID) {
        super(context, text, editTextID);
    }

    public EditableDouble(Context context, String text, int editTextID, int textGravity) {
        super(context, text, editTextID, textGravity);
    }

    public EditableDouble(Context context, Values values) {
        super(context, values);
    }

    public void set(double value) {
        getEditText().setText(Double.toString(value));
    }

    @Override
    public void set(Serializable value) {
        if (value instanceof Double) {
            set(value);
        } else {
            throw new IllegalArgumentException(
                    "Trying to set the field using a " + value.getClass().getSimpleName() + " object.");
        }
    }

    @Override
    public Double get() {
        return Double.parseDouble(getEditText().getText().toString());
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
    }

}
