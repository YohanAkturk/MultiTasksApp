package g56514.sortingrace.gestionDB.repository;

import g56514.sortingrace.gestionDB.dto.Dto;
import g56514.sortingrace.gestionDB.dto.SimulationDto;
import g56514.sortingrace.gestionDB.exception.RepositoryException;
import g56514.sortingrace.gestionDB.jdbc.SimulationDao;
import java.util.List;

/**
 *
 * @author yohan
 */
public class SimulationRepository implements Repository<Integer, SimulationDto> {

    private final SimulationDao dao;

    public SimulationRepository() throws RepositoryException{
        dao = SimulationDao.getInstance();
    }
    
    @Override
    public Integer add(SimulationDto item) throws RepositoryException {
        return dao.insert(item);
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SimulationDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public SimulationDto get(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
