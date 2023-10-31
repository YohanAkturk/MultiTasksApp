package g56514.sortingrace.gestionDB.dto;

/**
 *
 * @author yohan
 */
public class SimulationDto extends Dto<Integer> {

    private String timestamp;
    private String sort_type;
    private int max_size;

    /**
     * Creates a new instance of <code>SimulationDto</code>.
     *
     * @param id the key of the line.
     */
    public SimulationDto(Integer id, String timestamp, String sort_type, int max_size) {
        super(id);
        this.timestamp = timestamp;
        this.sort_type = sort_type;
        this.max_size = max_size;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSort_type() {
        return sort_type;
    }

    public void setSort_type(String sort_type) {
        this.sort_type = sort_type;
    }

    public int getMax_size() {
        return max_size;
    }

    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }

    @Override
    public String toString() {
        return "SimulationDto{" + getKey() + "timestamp=" + timestamp + ", sort_type=" + sort_type + ", max_size=" + max_size + '}';
    }


}
