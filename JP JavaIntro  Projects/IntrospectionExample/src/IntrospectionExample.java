import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

public class IntrospectionExample {
    
    public static void main( String[] args )
    throws IntrospectionException {
        
        // Get bean information via Introspection
        BeanInfo info = Introspector.getBeanInfo( SimpleBean.class );
        
        // Display properties of a bean
        for ( PropertyDescriptor pd : info.getPropertyDescriptors() ){
            System.out.println( "Field of SimpleBean = " + pd.getName() );
        }
        
        // Display methods of a bean
        for ( MethodDescriptor md : info.getMethodDescriptors() ){
            System.out.println( "Methods of SimpleBean = " + md.getName() );
        }
        
    }
}
