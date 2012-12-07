
package assertexample;

import javax.swing.*;

class MyOwnNegativeValueEnteredException extends ArithmeticException{    
    MyOwnNegativeValueEnteredException(String str){
        super(str);
    }
}
class MyOwnZeroValueEnteredException extends ArithmeticException{    
}
class Diamond {
    
    static void printDiamond(int size) {
        String diamond = "";
        /* print upper triangle */
        for (int r = 1, a = 1; r <= size; r++, a+=2) {
            /* print spaces */
            for (int i = size - r; i >= 1; i--) {
                diamond += " ";
            }
            /* print *'s */
            for (int j = 1; j <= a; j++) {
                diamond += "*";
            }
            diamond += "\n";
        }
        /* print lower triangle */
        for (int r = size - 1, a = 2*(size-1)-1; r >= 1; r--, a-=2) {
            /* print spaces */
            for (int i = size - r; i >= 1; i--) {
                diamond += " ";
            }
            /* print *'s */
            for (int j = 1; j <= a; j++) {
                diamond += "*";
            }
            diamond += "\n";
        }
        JOptionPane.showMessageDialog(null, diamond);
        System.out.println(diamond);
    }
    
    public static void main(String args[]) {
        String strSize;
        
        // Get the size of the diamond to draw
        strSize = JOptionPane.showInputDialog(null, "Enter diamond size:");
        try {
            //assert(size > 0) && (size <20);
            int size = Integer.parseInt(strSize);
            if(size<0)
                throw new MyOwnNegativeValueEnteredException(strSize);
            if(size==0)
                throw new MyOwnZeroValueEnteredException();
            printDiamond(size);
        } catch (MyOwnNegativeValueEnteredException e) {
            JOptionPane.showMessageDialog(null, "MyOwnNegativeValueEnteredException is captured: Size should be > 0. Size entered:" + e.getMessage());
        }catch(MyOwnZeroValueEnteredException e){
            JOptionPane.showMessageDialog(null, "MyOwnZeroValueEnteredException is captured: Size should be > 0: " );
        }catch(Exception e){
            /**Any Exception*/
            JOptionPane.showMessageDialog(null, e);
        }
        System.exit(0);
    }
}