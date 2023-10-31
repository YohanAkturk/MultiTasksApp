package g56514.sortingrace.view;

/**
 *
 * @author yohan
 */
public class DataToDisplay {
    private String time;
    private String sortType;
    private int maxSize;

    public DataToDisplay(String time, String sortType, int maxSize) {
        this.time = time;
        this.sortType = sortType;
        this.maxSize = maxSize;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    
}
