import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestForEnhancement {
    int    id();
    String synopsis();
    String engineer() default "[unassigned]"; 
    String date()    default "[unimplemented]"; 
}
