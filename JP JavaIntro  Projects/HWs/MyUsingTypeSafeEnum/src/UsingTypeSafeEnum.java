public class UsingTypeSafeEnum {
    
    // Note that FootballScore is now enum type
    public enum FootballScore {
         TOUCHDOWN( 6, 2 ), FIELDGOAL( 3, 2 ), TWOPOINTS( 2, 2 )
         , SAFETY( 2, 1 ), EXTRAPOINT( 1, 0 );

        
         FootballScore( int value, int extrapoint ) {
            this.score = value;
            this.extrapoint = extrapoint;
        }
        
        private final int score;
        private final int extrapoint;
        
        public int score() {
            return score+extrapoint;
        }
        
    }
    
    public static void main( String[] args ) {
        
        FootballScore[] myScores = {
            FootballScore.TOUCHDOWN,
            FootballScore.EXTRAPOINT,
            FootballScore.FIELDGOAL,
            FootballScore.TOUCHDOWN,
            FootballScore.SAFETY,
            FootballScore.TOUCHDOWN,
            FootballScore.TWOPOINTS
        };
        
        FootballScore[] yourScores = {
            FootballScore.FIELDGOAL,
            FootballScore.TOUCHDOWN,
            FootballScore.FIELDGOAL
        };
        
        
        int mytotal = calcTotal( myScores );
        int yourtotal = calcTotal( yourScores );
        
        System.out.println( " My football team scored " + mytotal );
        System.out.println( " Your football team scored " + yourtotal );
        
        if ( mytotal > yourtotal ) {
            System.out.println( " My Team Won! " );
        } else if ( mytotal < yourtotal ) {
            System.out.println( " Your Team Won! " );
        } else
            System.out.println( "What do you know?  It is a Tie !! " );
    }
    
    public static int calcTotal( FootballScore[] f ) {
        int total = 0;
        for ( int i = 0; i < f.length; ++i ) {
            total += f[i].score();
        }
        return total;
    }
    
}