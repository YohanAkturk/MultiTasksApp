package g56514.sortingrace.view;

/**
 *
 * @author yohan
 */
public enum TypeSort {

    /**
     *
     */
    BUBBLE_SORT("Tri à bulles"),

    /**
     *
     */
    FUSION_SORT("Tri fusion");
    
    public final String tri;
    
    private TypeSort(String tri){
        this.tri = tri;
    }
}
