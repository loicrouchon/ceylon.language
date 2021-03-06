package ceylon.language;

import com.redhat.ceylon.compiler.java.metadata.CaseTypes;
import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.Class;
import com.redhat.ceylon.compiler.java.metadata.Defaulted;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;

@Ceylon(major = 6)
@Class(extendsType = "ceylon.language::Basic")
@CaseTypes({"ceylon.language::Error", "ceylon.language::Exception"})
public abstract class Throwable extends java.lang.Object {

    @Ignore
    public final static TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(Throwable.class);

	private static final long serialVersionUID = -1790691559137471641L;

	private java.lang.String description;
    
    public Throwable(
            @TypeInfo("ceylon.language::String|ceylon.language::Null")
            @Name("description")
            @Defaulted
            java.lang.String description,
            @TypeInfo("ceylon.language::Throwable|ceylon.language::Null")
            @Name("cause")
            @Defaulted
            java.lang.Throwable cause) {
        //super(description==null ? null : description.toString(), cause);
        this.description = description;
    }
    
    @Ignore
    public Throwable(java.lang.String description) {
        this(description, $default$cause(description));
    }
    
    @Ignore
    public Throwable() {
        this($default$description());
    }
        
    @TypeInfo("ceylon.language::Throwable|ceylon.language::Null")
    public java.lang.Throwable getCause() {
        return null;//super.getCause();
    }
    
    @TypeInfo("ceylon.language::String")
    public java.lang.String getMessage() {
        if (description != null
                && description != null) {
            return description.toString();
        } 
        else if (getCause() != null 
                && getCause().getMessage() != null) {
            return getCause().getMessage();
        }
        return "";
    }

    @TypeInfo("ceylon.language::String")
    public java.lang.String toString() {
        return className_.className(this) + " \"" + getMessage() +"\""; 
    }
    
    //@Override
    public void printStackTrace() {
    	//super.printStackTrace();
    }

    @Ignore
    public static java.lang.String $default$description(){
        return null;
    }
    @Ignore
    public static java.lang.Throwable $default$cause(java.lang.String description){
        return null;
    }
}
