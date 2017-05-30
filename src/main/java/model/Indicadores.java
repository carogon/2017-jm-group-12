package model;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.uqbar.commons.utils.Observable;

@Observable
public class Indicadores {

	private static final String RUTA = "data/Indicadores.json";

	private final List<Indicador> indicadoresPredefinidos = Arrays.asList(
			new Indicador("Ingreso Neto",
					"[INGRESO NETO EN OPERACIONES CONTINUAS] + [INGRESO NETO EN OPERACIONES DISCONTINUAS]"),
			new Indicador("Retorno sobre capital total", "(<INGRESO NETO> - [DIVIDENDOS]) / [CAPITAL TOTAL]"));

	private List<Indicador> indicadores = new LinkedList<>();

	public Indicadores() {
		this.agregarIndicadores(indicadoresPredefinidos);
	}

	public void agregarIndicadores(List<Indicador> indicadores) {
		for (Indicador indicador : indicadores)
			if (!existeIndicador(indicador))
				this.agregarIndicador(indicador);
	}

	public void agregarIndicador(Indicador indicador) {
		this.indicadores.add(indicador);
	}

	/*
	 * public void eliminarIndicadores(List<Indicador> indicadores) {
	 * indicadores.forEach(i -> this.eliminarIndicador(i)); }
	 * 
	 * public void eliminarIndicador(Indicador indicador) {
	 * this.indicadores.remove(indicador); }
	 */

	public Indicador buscarIndicador(Indicador indicador) {
		return indicadores.stream().filter(_indicador -> _indicador.esIgual(indicador)).findFirst().orElse(null);
	}

	public boolean existeIndicador(Indicador indicador) {
		return indicadores.stream().anyMatch(_indicador -> _indicador.esIgual(indicador));
	}

	public void cargar() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.agregarIndicadores(mapper.readValue(new File(RUTA),
					mapper.getTypeFactory().constructCollectionType(LinkedList.class, Indicador.class)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void guardar() {
		try {
			new ObjectMapper().enable(Feature.INDENT_OUTPUT).writeValue(new File(RUTA), this.getIndicadoresDeUsuario());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Indicador> getIndicadoresPredefinidos() {
		return indicadoresPredefinidos;
	}

	public List<Indicador> getIndicadoresDeUsuario() {
		return indicadores.stream().filter(i -> !indicadoresPredefinidos.contains(i))
				.collect(Collectors.toList());
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Indicador get(int i) {
		return indicadores.get(i);
	}

	public int indexOf(Indicador indicador) {
		return indicadores.indexOf(indicador);
	}

	public int size() {
		return indicadores.size();
	}

}