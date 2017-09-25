package io.jeti.ui.editables;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.jeti.ui.layoutparams.ZeroMatch;
import io.jeti.ui.layoutparams.ZeroMatch1;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * This is an interface used by all of the Editable UI components, such as
 * {@link EditableLong} so that we can easily create layouts with such fields,
 * and save their values into {@link SharedPreferences}.
 */
abstract public class EditableField extends LinearLayout {

    private TextView textView = null;
    private EditText editText = null;
    private static final int textGravity = Gravity.CENTER_VERTICAL;
    private static final float textWeight = 1;

    public static class Values {

        public final String text;
        public final int editTextID;
        public final int textGravity;
        public final float textWeight;

        public Values(String text, int editTextID) {
            this(text, editTextID, Gravity.CENTER_VERTICAL);
        }

        public Values(String text, int editTextID, int textGravity) {
            this(text, editTextID, textGravity, 1);
        }

        public Values(String text, int editTextID, int textGravity, float textWeight) {
            this.text = text;
            this.editTextID = editTextID;
            this.textGravity = textGravity;
            this.textWeight = textWeight;
        }
    }

    /**
     * Get the format for the {@link EditText}. For instance, if this is an
     * integer, then we restrict the keyboard to only integer values.
     */
    abstract public int getInputType();

    /**
     * Return the object being saved in the {@link EditText}
     */
    abstract public Serializable get();

    /**
     * Set the object from a generic Serializable class. You should handle the
     * case where this is not correct in your derived class.
     */
    abstract public void set(Serializable value);

    public void set(String value) {
        getEditText().setText(value);
    }

    public EditableField(Context context, String text) {
        this(context, text, View.generateViewId(), textGravity);
    }

    public EditableField(Context context, String text, int editTextID) {
        this(context, text, editTextID, textGravity);
    }

    public EditableField(Context context, String text, int editTextID, int textGravity) {
        this(context, text, editTextID, textGravity, textWeight);
    }

    public EditableField(Context context, Values values) {
        this(context, values.text, values.editTextID, values.textGravity, values.textWeight);
    }

    public EditableField(Context context, String text, int editTextID, int textGravity,
        float textWeight) {

        super(context);
        setOrientation(LinearLayout.HORIZONTAL);

        textView = new TextView(context);
        textView.setText(text);
        textView.setGravity(textGravity);
        addView(textView, new ZeroMatch1());

        editText = new EditText(context);
        editText.setId(editTextID);
        editText.setInputType(getInputType());
        addView(editText, new ZeroMatch(textWeight));

        /* Make the textView look similar to the EditText */
        textView.setTypeface(editText.getTypeface());
    }

    public EditText getEditText() {
        return editText;
    }

    public static EditableField newInstance(Class<? extends EditableField> clazz,
        Values values) {
        try {
            return clazz.getConstructor(Values.class).newInstance(values);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
