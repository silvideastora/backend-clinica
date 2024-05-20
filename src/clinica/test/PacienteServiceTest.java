package clinica.test;

import clinica.dao.impl.PacienteDaoH2;
import clinica.model.Domicilio;
import clinica.model.Paciente;
import clinica.service.PacienteService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {
    private static Logger LOGGER = Logger.getLogger(PacienteServiceTest.class);
    //Necesitamos nuestro servicio le instancio uno nuevo y le creo un paciente DAOH2
    PacienteService pacienteService = new PacienteService( new PacienteDaoH2());

    @BeforeAll
    static void crearTablas() {
        Connection connection = null;

        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/clinica10;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");


        } catch(Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());

            }
        }
    }
    @Test
    @DisplayName("Testear que un paciente fue guarado")
    void testPacienteGuardado(){
        //Creamos un paciente, el domicilio lo puedes crear
        Paciente paciente = new Paciente("Navajas","Pedro","tarjeta", LocalDate.of(2024,05,14),
                new Domicilio(234,"Ixtapaluca","Estado Mexico","Francisco Mina"));

        //Luego creo otro paciente en la base de datos
        Paciente pacienteDesdeBD = pacienteService.registrar(paciente);

        assertNotNull(pacienteDesdeBD);
    }

    @Test
    @DisplayName("Testear buscar paciente por ID")
    void testpacientePorId(){
        Integer id = 1;
        Paciente pacienteEncontradoPorID = pacienteService.buscarPorID(id);

        assertEquals(id, pacienteEncontradoPorID.getId());
    }

    @Test
    @DisplayName("Testear todos")
    void testParatodos(){
        Paciente paciente = new Paciente("Navajas","Pedro","tarjeta", LocalDate.of(2024,05,14),
                new Domicilio(234,"Ixtapaluca","Estado Mexico","Francisco Mina"));
        Paciente pacienteDesdeBD = pacienteService.registrar(paciente);

        List<Paciente> pacientes = pacienteService.buscarTodos();

        assertEquals(1,pacientes.size());

    }



}