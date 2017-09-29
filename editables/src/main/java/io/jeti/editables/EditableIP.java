package io.jeti.editables;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.jeti.layoutparams.WrapMatch0;
import io.jeti.layoutparams.ZeroMatch;
import io.jeti.layoutparams.ZeroMatch1;
import java.util.ArrayList;
import java.util.List;

public class EditableIP extends LinearLayout {

    private TextView             textView         = null;
    private final static int     fields           = 4;
    private final List<EditText> editableIntegers = new ArrayList<>();
    private static final int     defaultGravity   = Gravity.CENTER_VERTICAL;

    public EditableIP(Context context, String text) {
        this(context, text, generateIDs(fields));
    }

    public EditableIP(Context context, String text, List<Integer> editTextIDs) {
        this(context, text, editTextIDs, defaultGravity);
    }

    public EditableIP(Context context, String text, List<Integer> editTextIDs, int textGravity) {

        super(context);
        setOrientation(LinearLayout.HORIZONTAL);

        textView = new TextView(context);
        textView.setText(text);
        textView.setGravity(textGravity);
        addView(textView, new ZeroMatch(2));

        /* Split the IP address at the periods */
        int i;
        for (i = 0; i < fields - 1; i++) {
            EditText editText = new EditText(context);
            editText.setId(editTextIDs.get(i));
            editText.setInputType(getInputType());
            addView(editText, new ZeroMatch1());
            editableIntegers.add(editText);

            /* Place a period after each integer. */
            TextView textView = new TextView(context);
            textView.setText(".");
            textView.setGravity(textGravity);
            addView(textView, new WrapMatch0());
        }
        i = fields - 1;
        EditText editText = new EditText(context);
        editText.setId(editTextIDs.get(i));
        editText.setInputType(getInputType());
        addView(editText, new ZeroMatch1());
        editableIntegers.add(editText);

        /* Make the textView look similar to the EditText */
        textView.setTypeface(editableIntegers.get(0).getTypeface());
    }

    public void set(String text) {
        String[] ints = text.split("\\.");
        for (int i = 0; i < fields; i++) {
            editableIntegers.get(i).setText(ints[i]);
        }
    }

    public String get() {
        StringBuilder ip = new StringBuilder();
        for (int i = 0; i < fields - 1; i++) {
            ip.append(Integer.parseInt(editableIntegers.get(i).getText().toString())).append(".");
        }
        ip.append(Integer.parseInt(editableIntegers.get(fields - 1).getText().toString()));
        return ip.toString();
    }

    public int getInputType() {
        return InputType.TYPE_CLASS_NUMBER;
    }

    private static List<Integer> generateIDs(int n) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ids.add(View.generateViewId());
        }
        return ids;
    }
}