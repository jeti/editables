package io.jeti.editables;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import java.io.Serializable;

public class EditableInteger extends EditableField {

    public EditableInteger(Context context, String text) {
        super(context, text, View.generateViewId());
    }

    public EditableInteger(Context context, String text, int editTextID) {
        super(context, text, editTextID);
    }

    public EditableInteger(Context context, String text, int editTextID, int textGravity) {
        super(context, text, editTextID, textGravity);
    }

    public EditableInteger(Context context, Values values) {
        super(context, values);
    }

    public void set(int value) {
        getEditText().setText(Integer.toString(value));
    }

    @Override
    public void set(Serializable value) {
        if (value instanceof Integer) {
            set(value);
        } else {
            throw new IllegalArgumentException(
                "Trying to set the field using a " + value.getClass().getSimpleName() + " object.");
        }
    }

    @Override
    public Integer get() {
        return Integer.parseInt(getEditText().getText().toString());
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_NUMBER;
    }
}
