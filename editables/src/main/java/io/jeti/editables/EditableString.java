package io.jeti.editables;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import java.io.Serializable;

public class EditableString extends EditableField {

    public EditableString(Context context, String text) {
        super(context, text, View.generateViewId());
    }

    public EditableString(Context context, String text, int editTextID) {
        super(context, text, editTextID);
    }

    public EditableString(Context context, String text, int editTextID, int textGravity) {
        super(context, text, editTextID, textGravity);
    }

    public EditableString(Context context, Values values) {
        super(context, values);
    }

    public void set(String value) {
        getEditText().setText(value);
    }

    @Override
    public void set(Serializable value) {
        if (value instanceof String) {
            set(value);
        } else {
            throw new IllegalArgumentException(
                    "Trying to set the field using a " + value.getClass().getSimpleName() + " object.");
        }
    }

    @Override
    public String get() {
        return getEditText().getText().toString();
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_TEXT;
    }
}
