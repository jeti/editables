package io.jeti.editables;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import java.io.Serializable;

public class EditableLong extends EditableField {

    public EditableLong(Context context, String text) {
        super(context, text, View.generateViewId());
    }

    public EditableLong(Context context, String text, int editTextID) {
        super(context, text, editTextID);
    }

    public EditableLong(Context context, String text, int editTextID, int textGravity) {
        super(context, text, editTextID, textGravity);
    }

    public EditableLong(Context context, Values values) {
        super(context, values);
    }

    public void set(long value) {
        getEditText().setText(Long.toString(value));
    }

    @Override
    public void set(Serializable value) {
        if (value instanceof Long) {
            set(value);
        } else {
            throw new IllegalArgumentException(
                    "Trying to set the field using a " + value.getClass().getSimpleName() + " object.");
        }
    }

    @Override
    public Long get() {
        return Long.parseLong(getEditText().getText().toString());
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_NUMBER;
    }
}
