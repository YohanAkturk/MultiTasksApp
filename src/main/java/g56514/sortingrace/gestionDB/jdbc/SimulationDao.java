package g56514.sortingrace.gestionDB.jdbc;

import g56514.sortingrace.gestionDB.dto.SimulationDto;
import g56514.sortingrace.gestionDB.exception.RepositoryException;
import g56514.sortingrace.gestionDB.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yohan
 */
public class SimulationDao implements Dao<Integer, SimulationDto> {

    private Connection connexion;

    /**
     * Constructor of <code>SimulationDao</code>.
     *
     * @return an instance of SimulationDao.
     * @throws RepositoryException if there is a repository error.
     */
    private SimulationDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    /**
     * Retunrs a new instance of <code>SimulationDao</code>.
     *
     * @return an instance of FavoriDao.
     * @throws RepositoryException if there is a repository error.
     */
    public static SimulationDao getInstance() throws RepositoryException {
        return SimulationDaoHolder.getInstance();
    }

    @Override
    public Integer insert(SimulationDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucun élément donné en paramètre");
        }
        int id = 0;
        String sql = "INSERT INTO SIMULATION(timestamp, sort_type, max_size) values(?, ?, ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getTimestamp());
            pstmt.setString(2, item.getSort_type());
            pstmt.setInt(3, item.getMax_size());
            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            System.out.println(result);
            while (result.next()) {
                id = 1; //1 for success
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return id;
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(SimulationDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SimulationDto> selectAll() throws RepositoryException {
        String sql = "SELECT id, timestamp, sort_type, max_size FROM SIMULATION";
        List<SimulationDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SimulationDto dto = new SimulationDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        return dtos;
    }

    @Override
    public SimulationDto select(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class SimulationDaoHolder {

        private static SimulationDao getInstance() throws RepositoryException {
            return new SimulationDao();
        }
    }

}
