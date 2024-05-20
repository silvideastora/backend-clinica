package clinica.test;

import clinica.dao.impl.PacienteEnMemoria;
import clinica.model.Domicilio;
import clinica.model.Paciente;
import clinica.service.PacienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PacienteEnMemoriaTest {


    private static PacienteService pacienteService = new PacienteService(new PacienteEnMemoria());

    @Test
    @DisplayName("Testear que un paciente fue guarado")
    void testPacienteGuardado(){
        //Creamos un paciente, el domicilio lo puedes crear
        Paciente paciente = new Paciente("Navajas","Pedro","tarjeta", LocalDate.of(2024,05,14),
                new Domicilio(234,"Ixtapaluca","Estado Mexico","Francisco Mina"));

        //Luego creo otro paciente en la base de datos
        Paciente pacienteDesdeMemoria = pacienteService.registrar(paciente);

        assertNotNull(pacienteDesdeMemoria);
    }

    @Test
    @DisplayName("Testear busqueda paciente por id")
    void testPacientePorId(){
        Paciente paciente = new Paciente("Navajas","Pedro","tarjeta", LocalDate.of(2024,05,14),
                new Domicilio(234,"Ixtapaluca","Estado Mexico","Francisco Mina"));
        pacienteService.registrar(paciente);

        Integer id = 1;
        Paciente pacienteEncontrado = pacienteService.buscarPorID(id);

        assertEquals(id, pacienteEncontrado.getId());
    }

    @Test
    @DisplayName("Testear busqueda todos los pacientes")
    void testBusquedaTodos() {
        Paciente paciente = new Paciente("Navajas","Pedro","tarjeta", LocalDate.of(2024,05,14),
                new Domicilio(234,"Ixtapaluca","Estado Mexico","Francisco Mina"));
        pacienteService.registrar(paciente);

        List<Paciente> pacientes = pacienteService.buscarTodos();

        assertEquals(1, pacientes.size());
    }

}