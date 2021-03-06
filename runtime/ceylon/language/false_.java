package ceylon.language;

import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Object;

@Ceylon(major = 6) @Object
public final class false_ extends Boolean {
    
    private final static false_ value = new false_();

    public static false_ get_(){
        return value;
    }

    @Override
    @Ignore
    public boolean booleanValue() {
        return false;
    }
    
    @Override
    public java.lang.String toString() {
        return "false";
    }
}
