package io.jeti.editables;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import java.io.Serializable;

public class EditableFloat extends EditableField {

    public EditableFloat(Context context, String text) {
        super(context, text, View.generateViewId());
    }

    public EditableFloat(Context context, String text, int editTextID) {
        super(context, text, editTextID);
    }

    public EditableFloat(Context context, String text, int editTextID, int textGravity) {
        super(context, text, editTextID, textGravity);
    }

    public EditableFloat(Context context, Values values) {
        super(context, values);
    }

    public void set(float value) {
        getEditText().setText(Float.toString(value));
    }

    @Override
    public void set(Serializable value) {
        if (value instanceof Float) {
            set(value);
        } else {
            throw new IllegalArgumentException(
                    "Trying to set the field using a " + value.getClass().getSimpleName() + " object.");
        }
    }

    @Override
    public Float get() {
        return Float.parseFloat(getEditText().getText().toString());
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
    }

}
