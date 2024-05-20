package clinica.service;

import clinica.dao.IDao;
import clinica.model.Paciente;

import java.util.List;

public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    //Genero su constructor , e invoco todos los metodos del PacienteDao
    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente registrar(Paciente paciente){
        return pacienteIDao.registrar(paciente);
    }

    public Paciente buscarPorID(Integer id){
        return pacienteIDao.buscarPorId(id);
    }
    public List<Paciente> buscarTodos(){
        return pacienteIDao.buscarTodos();
    }

}
