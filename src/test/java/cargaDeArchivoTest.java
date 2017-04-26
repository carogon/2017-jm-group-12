import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.DataLoader;
import model.Empresa;
import model.Periodo;
import model.repositories.Repositorios;

public class cargaDeArchivoTest {
	private DataLoader data;
	private Empresa empresa;
	private Periodo periodo;
	
	@Before 
	public void inicio(){
		this.data = new DataLoader();
		this.data.cargarDatosDesdeArchivo("data/ArchivoPrueba.txt");
	}
	@Test
	public void verificarNombreDeLaPrimerEmpresa(){
		empresa = Repositorios.empresasRepo.getEmpresas().get(0);
		assertTrue(empresa.esIgual("Facebook"));
	}
	@Test
	public void verificarPeriodoSegundaEmpresa() {
		empresa=Repositorios.empresasRepo.getEmpresas().get(1);
		assertTrue(empresa.getPeriodos().get(0).esIgual(2016));
	}
	@Test
	public void verificarQueDevuelveLasCuentas() {
		
		empresa=Repositorios.empresasRepo.getEmpresas().get(0);
		periodo=empresa.getPeriodos().get(0);
		System.out.println(periodo.getCuentas());
	}

}