package io.jeti.editables;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import io.jeti.layoutparams.MatchZero1;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        ll.addView(new EditableDouble(this, "Double: "), new MatchZero1());
        ll.addView(new EditableFloat(this, "Float: "), new MatchZero1());
        ll.addView(new EditableInteger(this, "Integer: "), new MatchZero1());
        ll.addView(new EditableIP(this, "IP: "), new MatchZero1());
        ll.addView(new EditableLong(this, "Long: "), new MatchZero1());
        ll.addView(new EditableString(this, "String: "), new MatchZero1());

        setContentView(ll);
    }
}
